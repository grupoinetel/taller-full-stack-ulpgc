import {Component, OnInit, ViewChild} from '@angular/core';
import {Usuario} from '../../../usuarios/model/Usuario';
import {DetalleTarea} from '../../model/DetalleTarea';
import {ESTADO_TAREA_COLORES, ESTADO_TAREA_LABELS, EstadoTarea} from '../../model/EstadoTarea';
import {PRIORIDAD_TAREA_COLORES, PrioridadTarea} from '../../model/PrioridadTarea';
import {ModalTarea} from '../../components/modal-tarea/modal-tarea';
import {CategoriaTarea} from '../../model/CategoriaTarea';

@Component({
  selector: 'app-tablero-tareas',
  imports: [
    ModalTarea
  ],
  templateUrl: './tablero-tareas.html',
  styleUrl: './tablero-tareas.scss',
})
export class TableroTareasComponent implements OnInit {
  @ViewChild('modalTarea')
  modalTarea?: ModalTarea;

  estados: EstadoTarea[] = Object.values(EstadoTarea);
  protected readonly ESTADO_TAREA_LABELS = ESTADO_TAREA_LABELS;
  protected readonly PRIORIDAD_TAREA_COLORES = PRIORIDAD_TAREA_COLORES;
  protected readonly ESTADO_TAREA_COLORES = ESTADO_TAREA_COLORES;

  readonly USUARIOS_MOCK: Usuario[] = [
    { id: 1, nombre: 'Nahima Ortega', avatarUrl: null },
    { id: 2, nombre: 'Luis Galindo', avatarUrl: null },
    { id: 3, nombre: 'Juan García', avatarUrl: "https://api.dicebear.com/9.x/dylan/svg?seed=Luis" },
    { id: 4, nombre: 'María Fernández', avatarUrl: "https://api.dicebear.com/9.x/dylan/svg?seed=Eden" },
    { id: 5, nombre: 'María Rodríguez', avatarUrl: "https://api.dicebear.com/9.x/dylan/svg?seed=Christian" },
    { id: 6, nombre: 'Antonio López', avatarUrl: "https://api.dicebear.com/9.x/dylan/svg?seed=Eden" },
    { id: 7, nombre: 'David Ramírez', avatarUrl: "https://api.dicebear.com/9.x/dylan/svg?seed=Mason" },
    { id: 8, nombre: 'Paula Pérez', avatarUrl: "https://api.dicebear.com/9.x/dylan/svg?seed=Aidan" },
    { id: 9, nombre: 'Laura Sánchez', avatarUrl: "https://api.dicebear.com/9.x/dylan/svg?seed=Adrian" },
    { id: 10, nombre: 'Ana Gómez', avatarUrl: "https://api.dicebear.com/9.x/dylan/svg?seed=Vivian" },
  ];

  readonly TAREAS_MOCK: DetalleTarea[] = [
    {
      id: 1,
      numero: 123001,
      titulo: 'Diseñar la estructura inicial de Angular del proyecto',
      imagenUrl: 'https://cdn.openwebinars.net/media/fbads-angular.jpg',
      fechaLimite: '2026-02-01 10:00',
      estado: EstadoTarea.HECHO,
      prioridad: PrioridadTarea.URGENTE,
      categoria: CategoriaTarea.DISENYO,
      descripcion:
        'Definir la arquitectura base del proyecto Angular y estructura de carpetas. ' +
        'Instalación de la versión 21 de Angular y todas sus dependencias.',
      fechaCreacion: '2026-01-20 08:31',
      porcentajeRealizado: 100,
      autor: this.USUARIOS_MOCK[0],
      tiempoEstimado: 5,
      asignados: [this.USUARIOS_MOCK[0], this.USUARIOS_MOCK[1]],
      comentarios: [
        {
          id: 1,
          autor: this.USUARIOS_MOCK[1],
          mensaje: 'Se ha instalado correctamente la versión de Node.js acorde a la versión de Angular.',
          fecha: '2026-01-22 10:55',
        },
        {
          id: 2,
          autor: this.USUARIOS_MOCK[0],
          mensaje: 'Visitar la página https://angular.dev/overview para saber todas las novedades de la v.21',
          fecha: '2026-01-22 11:32',
        },
      ],
    },
    {
      id: 2,
      numero: 123002,
      titulo: 'Implementar vista del tablero Kanban',
      fechaLimite: '2026-02-02 10:00',
      estado: EstadoTarea.EN_PRUEBAS,
      imagenUrl: 'https://media.istockphoto.com/id/2215565128/vector/kanban-method-of-task-project-planning-and-management-hands-sticking-notes-on-whiteboard.jpg?s=612x612&w=0&k=20&c=Cp1kLF4AiMz4frFJuHcHT0NkU5r_3Pzky5KRWI6TgkY=',
      prioridad: PrioridadTarea.ALTA,
      categoria: CategoriaTarea.DESARROLLO,
      descripcion:
        'Crear columnas según el estado de la tarea. Cada columna indica el estado actual en el que se encuentra la tarea.',
      fechaCreacion: '2026-01-20 10:54',
      porcentajeRealizado: 90,
      autor: this.USUARIOS_MOCK[0],
      tiempoEstimado: 3,
      asignados: [this.USUARIOS_MOCK[2], this.USUARIOS_MOCK[3], this.USUARIOS_MOCK[4]],
      comentarios: [{
        id: 1,
        autor: this.USUARIOS_MOCK[4],
        mensaje: 'Se tienen que utilizar clases de Bootstrap para alinear las columnas y sus elementos.',
        fecha: '2026-01-22 9:01',
      }],
    },
    {
      id: 3,
      numero: 123003,
      titulo: 'Corregir bug en validación de formulario',
      imagenUrl: 'https://www.jotform.com/blog/wp-content/uploads/2026/02/7-best-Tally-form-alternatives-for-YEAR-_1770289640-330x220.png',
      fechaLimite: '2026-01-20 11:00',
      estado: EstadoTarea.EN_PROGRESO,
      prioridad: PrioridadTarea.ALTA,
      categoria: CategoriaTarea.SOPORTE,
      descripcion: 'El formulario permite enviar tareas sin título.',
      fechaCreacion: '2026-01-28 07:28',
      porcentajeRealizado: 30,
      autor: this.USUARIOS_MOCK[7],
      tiempoEstimado: 3,
      asignados: [this.USUARIOS_MOCK[1], this.USUARIOS_MOCK[8]],
      comentarios: [
        {
          id: 1,
          autor: this.USUARIOS_MOCK[0],
          mensaje: 'Creo que falta una validación de requerido. Revisar que se encuentre la validación también en la base de datos.',
          fecha: '2026-01-28 13:42',
        },
      ],
    },
    {
      id: 4,
      numero: 123004,
      titulo: 'Documentación API REST',
      imagenUrl: 'https://www.astera.com/wp-content/uploads/2020/01/rest.png',
      fechaLimite: '2026-03-01 08:00',
      estado: EstadoTarea.PENDIENTE,
      prioridad: PrioridadTarea.BAJA,
      categoria: CategoriaTarea.DOCUMENTACION,
      descripcion: 'Crear documentación básica de los endpoints. Documentar también los casos de uso.',
      fechaCreacion: '2026-02-28 07:39',
      porcentajeRealizado: 0,
      tiempoEstimado: 5,
      autor: this.USUARIOS_MOCK[7],
      asignados: [this.USUARIOS_MOCK[5], this.USUARIOS_MOCK[6]],
      comentarios: [],
    },
    {
      id: 5,
      numero: 123005,
      titulo: 'Optimizar consultas a base de datos',
      imagenUrl:
        'https://external-preview.redd.it/making-a-postgres-query-1000-times-faster-v0-vDpNwQZe7rJRfPiEXuu6XAgNGaSwWxfmaDcN0V0H-mI.jpg?width=640&crop=smart&auto=webp&s=c596258c6e376d1ee4348cc3b461daa7e5a23eaf',
      fechaLimite: '2026-03-10 08:00',
      estado: EstadoTarea.EN_PROGRESO,
      prioridad: PrioridadTarea.ALTA,
      categoria: CategoriaTarea.MEJORAS,
      descripcion: 'Revisar índices y tiempos de respuesta.',
      fechaCreacion: '2026-01-30 14:25',
      porcentajeRealizado: 40,
      tiempoEstimado: 12,
      autor: this.USUARIOS_MOCK[0],
      asignados: [this.USUARIOS_MOCK[1], this.USUARIOS_MOCK[8]],
      comentarios: [{
        id: 1,
        autor: this.USUARIOS_MOCK[8],
        mensaje: 'Se han logrado optimizar los tiempos en un 5% aproximadamente.',
        fecha: '2026-02-01 12:54',
      }],
    },
    {
      id: 6,
      numero: 123006,
      titulo: 'Preparar presentación del taller',
      imagenUrl:
        'https://images.ctfassets.net/joi3nje8wm6a/27ZdOI2eRXjGY2qdyNuTo/119f045a5543a6ca4f27e0287bd95bf2/3_Business_presentation__public_speaking_skills.jpg',
      fechaLimite: '2026-03-15 08:00',
      estado: EstadoTarea.PENDIENTE,
      prioridad: PrioridadTarea.MEDIA,
      categoria: CategoriaTarea.GESTION,
      descripcion: 'Preparar la presentación con ejemplos prácticos de Angular y Spring.',
      fechaCreacion: '2026-03-01 05:48',
      porcentajeRealizado: 5,
      tiempoEstimado: 6,
      autor: this.USUARIOS_MOCK[1],
      asignados: [this.USUARIOS_MOCK[0]],
      comentarios: [{
        id: 1,
        autor: this.USUARIOS_MOCK[0],
        mensaje: 'Añadir también ejemplos de cómo mejora el desarrollo la arquitectura hexagonal.',
        fecha: '2026-03-01 10:03',
      }],
    },
    {
      id: 7,
      numero: 123007,
      titulo: 'Añadir barra de progreso para mostrar el porcentaje completado',
      fechaLimite: '2026-01-30 10:00',
      estado: EstadoTarea.EN_PRUEBAS,
      prioridad: PrioridadTarea.MEDIA,
      categoria: CategoriaTarea.DESARROLLO,
      descripcion: 'Mostrar porcentaje realizado con Bootstrap.',
      fechaCreacion: '2026-01-21 15:21',
      porcentajeRealizado: 85,
      tiempoEstimado: 4,
      autor: this.USUARIOS_MOCK[2],
      asignados: [this.USUARIOS_MOCK[2], this.USUARIOS_MOCK[4]],
      comentarios: [],
    },
    {
      id: 8,
      numero: 123008,
      titulo: 'Configurar Docker para Oracle',
      imagenUrl:
        'https://www.qindel.com/wp-content/uploads/2025/04/docker.jpg',
      fechaLimite: '2026-02-01 10:00',
      estado: EstadoTarea.HECHO,
      prioridad: PrioridadTarea.URGENTE,
      categoria: CategoriaTarea.ANALISIS,
      descripcion:
        'Preparar docker-compose con scripts de inicialización.',
      fechaCreacion: '2026-01-19 08:30',
      porcentajeRealizado: 100,
      tiempoEstimado: 9,
      autor: this.USUARIOS_MOCK[2],
      asignados: [this.USUARIOS_MOCK[1], this.USUARIOS_MOCK[2], this.USUARIOS_MOCK[5]],
      comentarios: [{
        id: 1,
        autor: this.USUARIOS_MOCK[5],
        mensaje: 'Configurar el fichero YAML para incluir la base de datos y el servidor del back-end.',
        fecha: '2026-02-01 13:07',
      }],
    },
    {
      id: 9,
      numero: 123009,
      titulo: 'Revisión de código final',
      fechaLimite: '2026-03-10 08:00',
      estado: EstadoTarea.PENDIENTE,
      prioridad: PrioridadTarea.BAJA,
      categoria: CategoriaTarea.PRUEBAS,
      descripcion: 'Revisión general del código para proponer posibles mejoras futuras.',
      fechaCreacion: '2026-02-27 11:17',
      porcentajeRealizado: 10,
      tiempoEstimado: 7,
      autor: this.USUARIOS_MOCK[0],
      asignados: [this.USUARIOS_MOCK[0], this.USUARIOS_MOCK[3], this.USUARIOS_MOCK[6], this.USUARIOS_MOCK[7], this.USUARIOS_MOCK[9]],
      comentarios: [{
        id: 1,
        autor: this.USUARIOS_MOCK[0],
        mensaje: 'Revisar modularización del código y proponer simplificaciones. Propuestas de nuevas funcionalidades.',
        fecha: '2026-02-27 13:07',
      }],
    },
    {
      id: 10,
      numero: 123010,
      titulo: 'Implementar vista del detalle de la tarea',
      fechaLimite: '2026-01-25 08:00',
      estado: EstadoTarea.EN_PRUEBAS,
      prioridad: PrioridadTarea.ALTA,
      categoria: CategoriaTarea.DISENYO,
      descripcion: 'Crear componente DetalleTarea con todos los campos. Implementar la interfaz.',
      fechaCreacion: '2026-01-16 09:45',
      porcentajeRealizado: 100,
      tiempoEstimado: 8,
      autor: this.USUARIOS_MOCK[0],
      asignados: [this.USUARIOS_MOCK[0], this.USUARIOS_MOCK[2]],
      comentarios: [],
    },
  ];

  tareasAgrupadasPorEstado: Record<EstadoTarea, DetalleTarea[]> = {
    PENDIENTE: [],
    EN_PROGRESO: [],
    EN_PRUEBAS: [],
    HECHO: [],
  };

  ngOnInit() {
    this.agruparTareasPorEstado();
  }

  protected agruparTareasPorEstado(): void {
    this.TAREAS_MOCK.forEach((tarea) => {
      this.tareasAgrupadasPorEstado[tarea.estado].push(tarea);
    });
  }

  protected crearTarea(): void {
    //TODO: Mostrar el modal de crear tarea
  }

  protected abrirModalTarea(idTarea: number): void {
    //TODO: Hacer la llamada a la API para obtener la tarea por ID.
    const tarea = this.TAREAS_MOCK.find((t) => t.id === idTarea);
    if (tarea) {
      this.modalTarea?.toggle(tarea);
    }
  }
}
