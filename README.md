# Examen Tecnico

Aplicación CRUD desarrollada con **Spring Boot**, **JPA**, **Thymeleaf**, **Tailwind CSS** y **JasperReports** para la gestión de artículos y generación de reportes en PDF.

---

## Funcionalidades

- CRUD de artículos
- Validación de campos
- Diseño responsivo con Tailwind CSS
- Generación de reporte PDF con JasperReports
- Conexión a base de datos MySQL
- Arquitectura MVC

---

## Tecnologías

- Java 17+
- Spring Boot
- Spring Data JPA
- Thymeleaf
- Tailwind CSS
- JasperReports
- MySQL
- Maven

---

## Reporte PDF

El sistema permite generar un reporte en PDF que incluye:
- ID
- Nombre (máx. 50 caracteres)
- Descripción (máx. 250 caracteres)
- Precio

---

## Configuración

1. Crear base de datos:
   sql
CREATE DATABASE articulos;

2. Configurar application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/articulos
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD

3. Ejecutar en la terminal:
   mvn spring-boot:run

**ACCESOS**

- Aplicación: http://localhost:8081
- Reporte PDF: http://localhost:8081/articulos/reporte
