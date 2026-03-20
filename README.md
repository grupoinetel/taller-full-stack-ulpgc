# ✅ Gestor de tareas
Aplicación web para la gestión de tareas estilo Kanban (tipo Trello), desarrollada con Angular en el frontend y Spring Boot en el backend, siguiendo principios de arquitectura hexagonal.

## 🚀 Tecnologías utilizadas
- Angular (v21)
- TypeScript
- Bootstrap / Bootstrap Icons
- Java 21
- Spring
- Maven
- Docker

## 🏗️ Arquitectura
El backend sigue el patrón de arquitectura hexagonal (**Ports & Adapters**):
- **Dominio**: lógica de negocio pura
- **Aplicación**: casos de uso
- **Infraestructura**: base de datos, controladores REST
- **Puertos y adaptadores**:
  - Entrada → controladores REST
  - Salida → repositorios

Esto permite desacoplar la lógica de negocio de los detalles técnicos.

## 📦 Funcionalidades
- Crear, editar, ver el detalle y eliminar tareas
- Cambiar estado de una tarea
- Ver el listado de tareas agrupadas por estado
- Crear, ver y eliminar comentarios de una tarea

## 🖥️ Instalación y ejecución
Para poner en marcha este proyecto, es necesario tener las siguientes herramientas instaladas en nuestro equipo.
- Git
- Docker/Docker Desktop ([Instalación de Docker](https://docs.docker.com/get-started/get-docker/))
- Node.js versión 20.19.0 o superior ([Instalación de Node.js](https://nodejs.org/es/download))
- Angular CLI versión 21 ([Instalación de Angular](https://angular.dev/installation))
- Java 21 ([Instalación de Java](http://oracle.com/es/java/technologies/downloads/#java21))

Para compilar el proyecto, nos situamos en el directorio /api y ejecutamos:
```bash
mvn clean install
```
Después de haber terminado la compilación, ejecutamos el siguiente comando para que se creen los contenedores de Docker con la base de datos y el servidor
```bash
docker compose up --build
```
En el directorio /angular, tenemos que instalar todas las dependencias e iniciar el servidor
```bash
npm install
ng serve
```

La aplicación estará en:
```bash
http://localhost:4200
```


