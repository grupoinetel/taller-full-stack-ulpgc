-- Seed de datos de ejemplo para PostgreSQL
-- Se ejecuta automáticamente al crear el contenedor por primera vez.

BEGIN;

INSERT INTO USUARIOS (ID, NOMBRE, AVATAR_URL)
OVERRIDING SYSTEM VALUE
VALUES
  (1, 'Nahima Ortega', NULL),
  (2, 'Luis Galindo', NULL),
  (3, 'Juan García', 'https://api.dicebear.com/9.x/dylan/svg?seed=Luis'),
  (4, 'María Fernández', 'https://api.dicebear.com/9.x/dylan/svg?seed=Eden'),
  (5, 'María Rodríguez', 'https://api.dicebear.com/9.x/dylan/svg?seed=Christian'),
  (6, 'Antonio López', 'https://api.dicebear.com/9.x/dylan/svg?seed=Eden'),
  (7, 'David Ramírez', 'https://api.dicebear.com/9.x/dylan/svg?seed=Mason'),
  (8, 'Paula Pérez', 'https://api.dicebear.com/9.x/dylan/svg?seed=Aidan'),
  (9, 'Laura Sánchez', 'https://api.dicebear.com/9.x/dylan/svg?seed=Adrian'),
  (10, 'Ana Gómez', 'https://api.dicebear.com/9.x/dylan/svg?seed=Vivian');

INSERT INTO TAREAS (
  ID,
  NUMERO,
  TITULO,
  IMAGEN_URL,
  FECHA_LIMITE,
  ESTADO,
  PRIORIDAD,
  CATEGORIA,
  DESCRIPCION,
  FECHA_CREACION,
  PORCENTAJE_REALIZADO,
  TIEMPO_ESTIMADO,
  AUTOR_ID
)
OVERRIDING SYSTEM VALUE
VALUES
  (
    1,
    123001,
    'Diseñar la estructura inicial de Angular del proyecto',
    'https://cdn.openwebinars.net/media/fbads-angular.jpg',
    '2026-02-01 10:00:00+00',
    'HECHO',
    'URGENTE',
    'DISENYO',
    'Definir la arquitectura base del proyecto Angular y estructura de carpetas. Instalación de la versión 21 de Angular y todas sus dependencias.',
    '2026-01-20 08:31:00+00',
    100,
    5,
    1
  ),
  (
    2,
    123002,
    'Implementar vista del tablero Kanban',
    'https://media.istockphoto.com/id/2215565128/vector/kanban-method-of-task-project-planning-and-management-hands-sticking-notes-on-whiteboard.jpg?s=612x612&w=0&k=20&c=Cp1kLF4AiMz4frFJuHcHT0NkU5r_3Pzky5KRWI6TgkY=',
    '2026-02-02 10:00:00+00',
    'EN_PRUEBAS',
    'ALTA',
    'DESARROLLO',
    'Crear columnas según el estado de la tarea. Cada columna indica el estado actual en el que se encuentra la tarea.',
    '2026-01-20 10:54:00+00',
    90,
    3,
    1
  ),
  (
    3,
    123003,
    'Corregir bug en validación de formulario',
    'https://www.jotform.com/blog/wp-content/uploads/2026/02/7-best-Tally-form-alternatives-for-YEAR-_1770289640-330x220.png',
    '2026-01-20 11:00:00+00',
    'EN_PROGRESO',
    'ALTA',
    'SOPORTE',
    'El formulario permite enviar tareas sin título.',
    '2026-01-28 07:28:00+00',
    30,
    3,
    8
  ),
  (
    4,
    123004,
    'Documentación API REST',
    'https://www.astera.com/wp-content/uploads/2020/01/rest.png',
    '2026-03-01 08:00:00+00',
    'PENDIENTE',
    'BAJA',
    'DOCUMENTACION',
    'Crear documentación básica de los endpoints. Documentar también los casos de uso.',
    '2026-02-28 07:39:00+00',
    0,
    5,
    8
  ),
  (
    5,
    123005,
    'Optimizar consultas a base de datos',
    'https://external-preview.redd.it/making-a-postgres-query-1000-times-faster-v0-vDpNwQZe7rJRfPiEXuu6XAgNGaSwWxfmaDcN0V0H-mI.jpg?width=640&crop=smart&auto=webp&s=c596258c6e376d1ee4348cc3b461daa7e5a23eaf',
    '2026-03-10 08:00:00+00',
    'EN_PROGRESO',
    'ALTA',
    'MEJORAS',
    'Revisar índices y tiempos de respuesta.',
    '2026-01-30 14:25:00+00',
    40,
    12,
    1
  ),
  (
    6,
    123006,
    'Preparar presentación del taller',
    'https://images.ctfassets.net/joi3nje8wm6a/27ZdOI2eRXjGY2qdyNuTo/119f045a5543a6ca4f27e0287bd95bf2/3_Business_presentation__public_speaking_skills.jpg',
    '2026-03-15 08:00:00+00',
    'PENDIENTE',
    'MEDIA',
    'GESTION',
    'Preparar la presentación con ejemplos prácticos de Angular y Spring.',
    '2026-03-01 05:48:00+00',
    5,
    6,
    2
  ),
  (
    7,
    123007,
    'Añadir barra de progreso para mostrar el porcentaje completado',
    NULL,
    '2026-01-30 10:00:00+00',
    'EN_PRUEBAS',
    'MEDIA',
    'DESARROLLO',
    'Mostrar porcentaje realizado con Bootstrap.',
    '2026-01-21 15:21:00+00',
    85,
    4,
    3
  ),
  (
    8,
    123008,
    'Configurar Docker para Oracle',
    'https://www.qindel.com/wp-content/uploads/2025/04/docker.jpg',
    '2026-02-01 10:00:00+00',
    'HECHO',
    'URGENTE',
    'ANALISIS',
    'Preparar docker-compose con scripts de inicialización.',
    '2026-01-19 08:30:00+00',
    100,
    9,
    3
  ),
  (
    9,
    123009,
    'Revisión de código final',
    NULL,
    '2026-03-10 08:00:00+00',
    'PENDIENTE',
    'BAJA',
    'PRUEBAS',
    'Revisión general del código para proponer posibles mejoras futuras.',
    '2026-02-27 11:17:00+00',
    10,
    7,
    1
  ),
  (
    10,
    123010,
    'Implementar vista del detalle de la tarea',
    NULL,
    '2026-01-25 08:00:00+00',
    'EN_PRUEBAS',
    'ALTA',
    'DISENYO',
    'Crear componente DetalleTarea con todos los campos. Implementar la interfaz.',
    '2026-01-16 09:45:00+00',
    100,
    8,
    1
  );

INSERT INTO TAREAS_ASIGNADOS (TAREA_ID, USUARIO_ID)
VALUES
  (1, 1), (1, 2),
  (2, 3), (2, 4), (2, 5),
  (3, 2), (3, 9),
  (4, 6), (4, 7),
  (5, 2), (5, 9),
  (6, 1),
  (7, 3), (7, 5),
  (8, 2), (8, 3), (8, 6),
  (9, 1), (9, 4), (9, 7), (9, 8), (9, 10),
  (10, 1), (10, 3);

INSERT INTO COMENTARIOS (ID, TAREA_ID, AUTOR_ID, MENSAJE, FECHA)
OVERRIDING SYSTEM VALUE
VALUES
  (
    1,
    1,
    2,
    'Se ha instalado correctamente la versión de Node.js acorde a la versión de Angular.',
    '2026-01-22 10:55:00+00'
  ),
  (
    2,
    1,
    1,
    'Visitar la página https://angular.dev/overview para saber todas las novedades de la v.21',
    '2026-01-22 11:32:00+00'
  ),
  (
    3,
    2,
    5,
    'Se tienen que utilizar clases de Bootstrap para alinear las columnas y sus elementos.',
    '2026-01-22 09:01:00+00'
  ),
  (
    4,
    3,
    1,
    'Creo que falta una validación de requerido. Revisar que se encuentre la validación también en la base de datos.',
    '2026-01-28 13:42:00+00'
  ),
  (
    5,
    5,
    9,
    'Se han logrado optimizar los tiempos en un 5% aproximadamente.',
    '2026-02-01 12:54:00+00'
  ),
  (
    6,
    6,
    1,
    'Añadir también ejemplos de cómo mejora el desarrollo la arquitectura hexagonal.',
    '2026-03-01 10:03:00+00'
  ),
  (
    7,
    8,
    6,
    'Configurar el fichero YAML para incluir la base de datos y el servidor del back-end.',
    '2026-02-01 13:07:00+00'
  ),
  (
    8,
    9,
    1,
    'Revisar modularización del código y proponer simplificaciones. Propuestas de nuevas funcionalidades.',
    '2026-02-27 13:07:00+00'
  );

SELECT setval(pg_get_serial_sequence('usuarios', 'id'), COALESCE((SELECT MAX(ID) FROM USUARIOS), 1), true);
SELECT setval(pg_get_serial_sequence('tareas', 'id'), COALESCE((SELECT MAX(ID) FROM TAREAS), 1), true);
SELECT setval(pg_get_serial_sequence('comentarios', 'id'), COALESCE((SELECT MAX(ID) FROM COMENTARIOS), 1), true);

COMMIT;
