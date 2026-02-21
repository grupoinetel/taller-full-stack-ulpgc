import {Component, OnInit, ViewChild} from '@angular/core';
import {Usuario} from '../../../usuarios/model/Usuario';
import {DetalleTarea} from '../../model/DetalleTarea';
import {ESTADO_TAREA_COLORES, ESTADO_TAREA_LABELS, EstadoTarea} from '../../model/EstadoTarea';
import {PRIORIDAD_TAREA_COLORES, PrioridadTarea} from '../../model/PrioridadTarea';
import {ModalTarea} from '../../components/modal-tarea/modal-tarea';

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
      numero: 123456,
      titulo: 'Diseñar estructura inicial del proyecto',
      imagenUrl:
        'https://victorroblesweb.es/wp-content/uploads/2018/11/angular-bg.png',
      fechaLimite: '2026-03-30',
      estado: EstadoTarea.EN_PROGRESO,
      prioridad: PrioridadTarea.ALTA,
      descripcion:
        'Definir la arquitectura base del proyecto Angular y estructura de carpetas.',
      fechaCreacion: '2026-03-20',
      porcentajeRealizado: 60,
      autor: this.USUARIOS_MOCK[0],
      tiempoEstimado: 8,
      asignados: [this.USUARIOS_MOCK[0], this.USUARIOS_MOCK[1]],
      comentarios: [
        {
          id: 1,
          autor: this.USUARIOS_MOCK[1],
          mensaje: 'Revisar si usamos feature modules.',
          fecha: '2026-03-22',
        },
      ],
    },
    {
      id: 2,
      numero: 123457,
      titulo: 'Implementar tablero Kanban',
      fechaLimite: '2026-04-02',
      estado: EstadoTarea.PENDIENTE,
      prioridad: PrioridadTarea.MEDIA,
      descripcion:
        'Crear columnas dinámicas según el estado de la tarea.',
      fechaCreacion: '2026-03-25',
      porcentajeRealizado: 0,
      autor: this.USUARIOS_MOCK[0],
      tiempoEstimado: 10,
      asignados: [this.USUARIOS_MOCK[2]],
      comentarios: [],
    },
    {
      id: 3,
      numero: 123458,
      titulo: 'Corregir bug en validación de formulario',
      imagenUrl:
        'https://as1.ftcdn.net/jpg/02/31/41/06/1000_F_231410607_dceTXjhTj1yaWNraiFod1drBhOAmCAFg.jpg',
      fechaLimite: '2026-03-28',
      estado: EstadoTarea.EN_PRUEBAS,
      prioridad: PrioridadTarea.URGENTE,
      descripcion: 'El formulario permite enviar tareas sin título.',
      fechaCreacion: '2026-03-24',
      porcentajeRealizado: 90,
      autor: this.USUARIOS_MOCK[0],
      tiempoEstimado: 3,
      asignados: [this.USUARIOS_MOCK[3]],
      comentarios: [
        {
          id: 2,
          autor: this.USUARIOS_MOCK[0],
          mensaje: 'Creo que falta una validación required.',
          fecha: '2026-03-25',
        },
      ],
    },
    {
      id: 4,
      numero: 123459,
      titulo: 'Documentar API REST',
      imagenUrl:
        'https://www.astera.com/wp-content/uploads/2020/01/rest.png',
      fechaLimite: '2026-04-05',
      estado: EstadoTarea.PENDIENTE,
      prioridad: PrioridadTarea.BAJA,
      descripcion: 'Crear documentación básica de los endpoints.',
      fechaCreacion: '2026-03-21',
      porcentajeRealizado: 10,
      tiempoEstimado: 5,
      autor: this.USUARIOS_MOCK[0],
      asignados: [this.USUARIOS_MOCK[4]],
      comentarios: [],
    },
    {
      id: 5,
      numero: 123460,
      titulo: 'Optimizar consultas a base de datos',
      imagenUrl:
        'https://bs-uploads.toptal.io/blackfish-uploads/components/open_graph_image/10698762/og_image/optimized/0712-Bad_Practices_in_Database_Design_-_Are_You_Making_These_Mistakes_Dan_Social-754bc73011e057dc76e55a44a954e0c3.png',
      fechaLimite: '2026-04-01',
      estado: EstadoTarea.EN_PROGRESO,
      prioridad: PrioridadTarea.ALTA,
      descripcion: 'Revisar índices y tiempos de respuesta.',
      fechaCreacion: '2026-03-22',
      porcentajeRealizado: 40,
      tiempoEstimado: 12,
      autor: this.USUARIOS_MOCK[0],
      asignados: [this.USUARIOS_MOCK[1]],
      comentarios: [],
    },
    {
      id: 6,
      numero: 123461,
      titulo: 'Preparar presentación del taller',
      imagenUrl:
        'https://www.duarte.com/wp-content/uploads/2024/05/How-to-move-your-presentation-audience-with-this-powerful-story-technique-header-800x445.jpg',
      fechaLimite: '2026-04-10',
      estado: EstadoTarea.HECHO,
      prioridad: PrioridadTarea.MEDIA,
      descripcion: 'Slides con ejemplos prácticos de Angular.',
      fechaCreacion: '2026-03-15',
      porcentajeRealizado: 100,
      tiempoEstimado: 6,
      autor: this.USUARIOS_MOCK[1],
      asignados: [this.USUARIOS_MOCK[0]],
      comentarios: [],
    },
    {
      id: 7,
      numero: 123462,
      titulo: 'Añadir barra de progreso',
      fechaLimite: '2026-04-03',
      estado: EstadoTarea.EN_PROGRESO,
      prioridad: PrioridadTarea.MEDIA,
      descripcion: 'Mostrar porcentaje realizado con Bootstrap.',
      fechaCreacion: '2026-03-23',
      porcentajeRealizado: 50,
      tiempoEstimado: 4,
      autor: this.USUARIOS_MOCK[2],
      asignados: [this.USUARIOS_MOCK[2], this.USUARIOS_MOCK[4]],
      comentarios: [],
    },
    {
      id: 8,
      numero: 234567,
      titulo: 'Configurar Docker para Oracle',
      imagenUrl:
        'https://www.qindel.com/wp-content/uploads/2025/04/docker.jpg',
      fechaLimite: '2026-04-08',
      estado: EstadoTarea.PENDIENTE,
      prioridad: PrioridadTarea.ALTA,
      descripcion:
        'Preparar docker-compose con scripts de inicialización.',
      fechaCreacion: '2026-03-26',
      porcentajeRealizado: 0,
      tiempoEstimado: 9,
      autor: this.USUARIOS_MOCK[2],
      asignados: [this.USUARIOS_MOCK[1]],
      comentarios: [],
    },
    {
      id: 9,
      numero: 123333,
      titulo: 'Revisión de código final',
      fechaLimite: '2026-04-12',
      estado: EstadoTarea.EN_PRUEBAS,
      prioridad: PrioridadTarea.MEDIA,
      descripcion: 'Code review general antes de producción.',
      fechaCreacion: '2026-03-27',
      porcentajeRealizado: 75,
      tiempoEstimado: 7,
      autor: this.USUARIOS_MOCK[0],
      asignados: [this.USUARIOS_MOCK[3]],
      comentarios: [],
    },
    {
      id: 10,
      numero: 567890,
      titulo: 'Implementar vista detalle',
      fechaLimite: '2026-04-04',
      estado: EstadoTarea.PENDIENTE,
      prioridad: PrioridadTarea.ALTA,
      descripcion: 'Crear componente DetalleTarea con todos los campos.',
      fechaCreacion: '2026-03-26',
      porcentajeRealizado: 20,
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
    console.log('Se pulsa el botón de crear tarea');
  }

  protected abrirModalTarea(idTarea: number): void {

  }

  protected readonly ESTADO_TAREA_LABELS = ESTADO_TAREA_LABELS;
  protected readonly PRIORIDAD_TAREA_COLORES = PRIORIDAD_TAREA_COLORES;
  protected readonly ESTADO_TAREA_COLORES = ESTADO_TAREA_COLORES;
}
