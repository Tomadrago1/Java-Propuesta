# 🏋️‍♂️ JAR Training — Platform Containerized App

¡Bienvenido a **JAR Training**! Esta es una plataforma web híbrida de alto rendimiento diseñada para la gestión de entrenamientos deportivos, planificación nutricional y recomendación personalizada de recetas saludables para atletas y profesionales de la salud.

El proyecto está completamente containerizado en **Docker**, unificando un frontend web dinámico y un potente backend modular en **Java (Jakarta EE)**.

---

## 🚀 Arquitectura y Tecnologías Clave

* **Frontend Web:** JSPs dinámicos, HTML5, CSS3 moderno y responsive.
* **Servidor de Aplicación:** **Tomcat 10.1** (Jakarta EE 9+, con migración automática a `jakarta.servlet.*`).
* **Base de Datos:** **MySQL 8.0** configurado de manera robusta y persistente.
* **Seguridad:** Autenticación robusta basada en hashing seguro **SHA-256**.
* **Containerización:** Entorno multi-contenedor sincronizado con **Docker Compose**.

---

## 🛠️ Requisitos Previos

Solo necesitás tener instalado:
* [Docker Desktop](https://www.docker.com/products/docker-desktop/) (con Docker Compose activo)
* Git

---

## ⚡ Inicio Rápido (Despliegue con Docker)

Levantar la aplicación completa toma menos de un minuto y se realiza con un único comando:

1. **Cloná el repositorio y accedé a la carpeta:**
   ```bash
   git clone https://github.com/Tomadrago1/Java-Propuesta.git
   cd Java-Propuesta
   ```

2. **Compilá y levantá los servicios:**
   ```bash
   docker compose up -d --build
   ```

3. **¡Listo! Accedé desde tu navegador web:**
   🌐 **[http://localhost:8080](http://localhost:8080)**

---

## 🔑 Credenciales de Acceso (Datos Semilla)

El entorno se inicializa automáticamente con usuarios de prueba con contraseñas encriptadas de forma segura bajo algoritmo **SHA-256**. Elegí el rol con el que desees iniciar sesión:

| Rol / Vista | Nombre de Usuario | Contraseña | Redirección |
| :--- | :--- | :--- | :--- |
| **Administrador de Sistema** | `admin` | `admin123` | `vistaAdmin.jsp` |
| **Nutricionista (Profesional)** | `mgonzalez` | `pass123` | `vistaProfesional.jsp` |
| **Entrenador (Profesional)** | `clopez` | `pass123` | `vistaProfesional.jsp` |
| **Cliente / Atleta** | `jperez` | `pass123` | `vistaUsuario.jsp` |

---

## ⚙️ Características Técnicas del Entorno

### 📦 Docker Compose (`docker-compose.yml`)
* **Servicio `db` (MySQL 8.0):**
  * Configurado con `--lower_case_table_names=1` para asegurar la **compatibilidad multiplataforma** de las consultas SQL entre Windows y Linux (Docker).
  * Volumen persistente `db_data` para no perder la información al reiniciar los contenedores.
  * Salud del servicio (`healthcheck`) sincronizada para que la aplicación web espere a que la base de datos esté lista antes de arrancar.
* **Servicio `web` (Tomcat 10.1):**
  * Despliega la aplicación unificada compilando dinámicamente el frontend clásico de la rama `main` y las lógicas avanzadas de recetas recomendadas.

### 🐳 Compilación Multietapa (`Dockerfile`)
1. **Etapa 1 (Compilación):** Usa un JDK de `eclipse-temurin:11` para realizar un merge de las ramas, correr una migración "al vuelo" de `javax.servlet` a `jakarta.servlet` mediante scripts automáticos, y compilar todo el backend libre de dependencias obsoletas.
2. **Etapa 2 (Ejecución):** Sirve la aplicación de forma óptima en un servidor limpio Tomcat 10.1.

---

## 🧼 Comandos Útiles de Mantenimiento

* **Ver los logs en tiempo real (útil para depurar logins u otras acciones):**
  ```bash
  docker compose logs -f web
  ```

* **Reiniciar por completo la Base de Datos (limpiar volúmenes y volver a ejecutar `init.sql`):**
  ```bash
  docker compose down -v
  docker compose up -d --build
  ```

* **Verificar el estado de los contenedores:**
  ```bash
  docker compose ps
  ```
