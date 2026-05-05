package logic;

import java.io.InputStream;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
    private static String REMITENTE = "";
    private static String PASSWORD = "";

    static {
        try (InputStream input = EmailService.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                Properties prop = new Properties();
                prop.load(input);
                REMITENTE = prop.getProperty("EMAIL_ADDRESS");
                PASSWORD = prop.getProperty("EMAIL_PASSWORD");
            } else {
                System.err.println("Advertencia: No se encontró el archivo config.properties en el classpath.");
            }
        } catch (Exception ex) {
            System.err.println("Error al cargar config.properties: " + ex.getMessage());
        }
    }

    public static void enviarConfirmacionTurnoAsync(String destinatario, String nombreCliente, String nombreProfesional, String fecha, String hora) {
        // Ejecutar en un hilo separado para no bloquear la aplicación ni fallar la reserva
        Thread hiloEmail = new Thread(() -> {
            try {
                enviarConfirmacionTurno(destinatario, nombreCliente, nombreProfesional, fecha, hora);
                System.out.println("Email enviado exitosamente a: " + destinatario);
            } catch (Exception e) {
                System.err.println("Error al enviar el email de confirmación a " + destinatario + ": " + e.getMessage());
                // No lanzamos la excepción para no romper la aplicación principal
            }
        });
        hiloEmail.start();
    }

    private static void enviarConfirmacionTurno(String destinatario, String nombreCliente, String nombreProfesional, String fecha, String hora) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        // Timeout properties para no colgar el hilo infinitamente
        props.put("mail.smtp.connectiontimeout", "5000");
        props.put("mail.smtp.timeout", "5000");
        props.put("mail.smtp.writetimeout", "5000");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(REMITENTE, PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(REMITENTE));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        message.setSubject("¡Tu turno ha sido confirmado! - JarTraining");
        
        String contenidoHtml = "<div style='font-family: Arial, sans-serif; padding: 20px; color: #333;'>"
                + "<h2 style='color: #ff6b35;'>¡Hola, " + nombreCliente + "!</h2>"
                + "<p>Te confirmamos que tu turno ha sido reservado con éxito en <b>JarTraining</b>.</p>"
                + "<div style='background-color: #f9f9f9; padding: 15px; border-radius: 5px; border-left: 5px solid #ff6b35; margin: 20px 0;'>"
                + "<p><b>Profesional:</b> " + nombreProfesional + "</p>"
                + "<p><b>Fecha:</b> " + fecha + "</p>"
                + "<p><b>Hora:</b> " + hora + "</p>"
                + "</div>"
                + "<p>Por favor, recuerda asistir con 10 minutos de anticipación.</p>"
                + "<p>¡Te esperamos!</p>"
                + "<hr style='border: none; border-top: 1px solid #ddd;'>"
                + "<small style='color: #777;'>Este es un mensaje automático, por favor no respondas a este correo.</small>"
                + "</div>";

        message.setContent(contenidoHtml, "text/html; charset=utf-8");

        Transport.send(message);
    }
}
