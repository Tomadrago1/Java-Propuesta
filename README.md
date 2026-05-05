# 🏋️‍♂️ JAR Training — Platform Containerized & Local App

¡Bienvenido a **JAR Training**! Esta es una plataforma web híbrida de alto rendimiento diseñada para la gestión de entrenamientos deportivos, planificación nutricional y recomendación personalizada de recetas saludables para atletas y profesionales de la salud.

El proyecto soporta tanto ejecución tradicional local en un entorno de desarrollo integrado (**Eclipse IDE**) como despliegue ágil totalmente containerizado en **Docker**.

---

## 🛠️ Requisitos del Proyecto

### 💻 Opción 1: Desarrollo Local Tradicional (IDE)
Si vas a correr o desarrollar la aplicación directamente desde tu entorno local, los requerimientos son:
* **Java:** versión **21**
* **Servidor de Aplicación:** **Apache Tomcat 11.0** (compatible con Jakarta EE 10, usando `jakarta.servlet.*`)
* **IDE:** **Eclipse IDE for Enterprise Java and Web Developers**
* **Estructura Web:** **Dynamic Web Module 6.1**

### 🐳 Opción 2: Despliegue Containerizado (Docker)
Si vas a correr la aplicación de forma inmediata sin necesidad de configurar compiladores ni servidores locales:
* **Docker Desktop** (con Docker Compose activo)

---

## 📂 Importar y Ejecutar en Eclipse IDE

Si elegís el desarrollo local tradicional utilizando **Eclipse**, seguí estos pasos:

1. **Importar el proyecto en tu Workspace:**
   * Abrí **Eclipse IDE**.
   * Dirigite a `File` > `Import...`
   * Seleccioná `General` > `Existing Projects into Workspace` y elegí la carpeta raíz del proyecto.
2. **Configurar el servidor Apache Tomcat 11.0:**
   * En la pestaña **Servers** de Eclipse, agregá un nuevo servidor seleccionando **Apache Tomcat v11.0**.
   * Vinculá la ruta de tu instalación local de Tomcat 11.
3. **Desplegar el proyecto:**
   * Hacé clic derecho sobre el servidor Tomcat configurado y seleccioná **Add and Remove...**
   * Agregá el proyecto de **JAR Training** a la columna derecha de aplicaciones activas.
4. **Ejecutar la aplicación:**
   * Hacé clic derecho sobre el proyecto o el servidor y seleccioná **Run on Server**.
   * Accedé desde tu navegador local.

---

## ⚡ Inicio Rápido con Docker (Alternativa)

Para levantar la aplicación unificada con su base de datos de manera automática mediante contenedores:

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
* **Servicio `web` (Tomcat 10.1 / 11 compatible):**
  * Despliega la aplicación unificada compilando dinámicamente el frontend clásico de la rama `main` y las lógicas avanzadas de recetas recomendadas.

---

## 🧼 Comandos Útiles de Mantenimiento

* **Ver los logs en tiempo real:**
  ```bash
  docker compose logs -f web
  ```

* **Reiniciar por completo la Base de Datos (limpiar volúmenes y volver a ejecutar `init.sql`):**
  ```bash
  docker compose down -v
  docker compose up -d --build
  ```
