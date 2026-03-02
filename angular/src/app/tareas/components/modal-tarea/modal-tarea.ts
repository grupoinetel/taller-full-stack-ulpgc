import {AfterViewInit, Component, ElementRef, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {NgClass} from '@angular/common';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {DetalleTarea} from '../../model/DetalleTarea';
import {CATEGORIA_TAREA_COLORES, CATEGORIA_TAREA_LABELS, CategoriaTarea} from '../../model/CategoriaTarea';
import {ESTADO_TAREA_COLORES, ESTADO_TAREA_LABELS, EstadoTarea} from '../../model/EstadoTarea';
import {PRIORIDAD_TAREA_COLORES, PRIORIDAD_TAREA_LABELS, PrioridadTarea} from '../../model/PrioridadTarea';
import {Avatar} from '../../../comun/components/avatar/avatar';
import {Usuario} from '../../../usuarios/model/Usuario';

declare var bootstrap: any;

export interface FormularioTarea {
  asignadosIds: number[];
  titulo: string;
  descripcion: string | null;
  imagenUrl: string | null;
  fechaLimite: string | null;
  estado: EstadoTarea;
  prioridad: PrioridadTarea;
  categoria: CategoriaTarea | null;
  porcentajeRealizado: number;
  tiempoEstimado: number | null;
}

@Component({
  selector: 'app-modal-tarea',
  imports: [
    Avatar,
    NgClass,
    ReactiveFormsModule
  ],
  templateUrl: './modal-tarea.html',
  styleUrl: './modal-tarea.scss',
})
export class ModalTarea implements AfterViewInit {
  @ViewChild('modalTarea', { static: true })
  modalTarea?: ElementRef<HTMLElement>;
  @Input() usuarios: Usuario[] = [];
  @Output() guardarEvent = new EventEmitter<{id?: number; data: FormularioTarea}>();
  @Output('solicitarEdicion') editarEvent = new EventEmitter<DetalleTarea>();
  @Output() eliminarEvent = new EventEmitter<number>();
  @Output() agregarComentarioEvent = new EventEmitter<{tareaId: number; mensaje: string}>();

  tarea?: DetalleTarea;
  modo: 'detalle' | 'formulario' = 'detalle';

  estados: EstadoTarea[] = Object.values(EstadoTarea);
  prioridades: PrioridadTarea[] = Object.values(PrioridadTarea);
  categorias: CategoriaTarea[] = Object.values(CategoriaTarea);

  formularioTarea: any;
  formularioComentario: any;
  private bsModal?: any;
  protected idTareaEdicion?: number;

  protected readonly CATEGORIA_TAREA_LABELS = CATEGORIA_TAREA_LABELS;
  protected readonly CATEGORIA_TAREA_COLORES = CATEGORIA_TAREA_COLORES;
  protected readonly ESTADO_TAREA_LABELS = ESTADO_TAREA_LABELS;
  protected readonly ESTADO_TAREA_COLORES = ESTADO_TAREA_COLORES;
  protected readonly PRIORIDAD_TAREA_LABELS = PRIORIDAD_TAREA_LABELS;
  protected readonly PRIORIDAD_TAREA_COLORES = PRIORIDAD_TAREA_COLORES;

  constructor(private readonly formBuilder: FormBuilder) {
    this.formularioTarea = this.formBuilder.group({
      asignadosIds: [[] as number[]],
      nuevoAsignadoId: [null as number | null],
      titulo: ['', [Validators.required, Validators.maxLength(250)]],
      descripcion: [''],
      imagenUrl: [''],
      fechaLimite: [''],
      estado: [EstadoTarea.PENDIENTE, [Validators.required]],
      prioridad: [PrioridadTarea.MEDIA, [Validators.required]],
      categoria: [null as CategoriaTarea | null],
      porcentajeRealizado: [0, [Validators.required, Validators.min(0), Validators.max(100)]],
      tiempoEstimado: [null as number | null, [Validators.min(0), Validators.max(99999.99)]],
    });

    this.formularioComentario = this.formBuilder.group({
      mensaje: ['', [Validators.required, Validators.maxLength(1000)]],
    });
  }

  ngAfterViewInit() {
    this.bsModal = new bootstrap.Modal(this.modalTarea?.nativeElement);
  }

  abrirDetalle(tarea: DetalleTarea): void {
    this.modo = 'detalle';
    this.tarea = tarea;
    this.formularioComentario.reset({
      mensaje: '',
    });
    this.formularioComentario.markAsPristine();
    this.formularioComentario.markAsUntouched();
    this.bsModal?.show();
  }

  abrirCrear(estadoInicial: EstadoTarea = EstadoTarea.PENDIENTE): void {
    this.modo = 'formulario';
    this.tarea = undefined;
    this.idTareaEdicion = undefined;
    this.formularioTarea.reset({
      asignadosIds: [],
      nuevoAsignadoId: null,
      titulo: '',
      descripcion: '',
      imagenUrl: '',
      fechaLimite: '',
      estado: estadoInicial,
      prioridad: PrioridadTarea.MEDIA,
      categoria: null,
      porcentajeRealizado: 0,
      tiempoEstimado: null,
    });
    this.formularioTarea.markAsPristine();
    this.formularioTarea.markAsUntouched();
    this.bsModal?.show();
  }

  abrirEditar(tarea: DetalleTarea): void {
    this.modo = 'formulario';
    this.tarea = tarea;
    this.idTareaEdicion = tarea.id;
    this.formularioTarea.reset({
      asignadosIds: tarea.asignados.map((usuario) => usuario.id),
      nuevoAsignadoId: null,
      titulo: tarea.titulo,
      descripcion: tarea.descripcion ?? '',
      imagenUrl: tarea.imagenUrl ?? '',
      fechaLimite: tarea.fechaLimite ?? '',
      estado: tarea.estado,
      prioridad: tarea.prioridad,
      categoria: tarea.categoria,
      porcentajeRealizado: tarea.porcentajeRealizado,
      tiempoEstimado: tarea.tiempoEstimado ?? null,
    });
    this.formularioTarea.markAsPristine();
    this.formularioTarea.markAsUntouched();
    this.formularioComentario.reset({ mensaje: '' });
    this.formularioComentario.markAsPristine();
    this.formularioComentario.markAsUntouched();
    this.bsModal?.show();
  }

  editar(): void {
    if (this.tarea) {
      this.editarEvent.emit(this.tarea);
    }
  }

  eliminar(): void {
    if (!this.tarea) {
      return;
    }

    const confirmar = window.confirm(`¿Seguro que quieres eliminar la tarea #${this.tarea.numero}?`);
    if (!confirmar) {
      return;
    }

    this.eliminarEvent.emit(this.tarea.id);
    this.bsModal?.hide();
  }

  guardarFormulario(): void {
    this.formularioTarea.markAllAsTouched();
    if (this.formularioTarea.invalid) {
      return;
    }

    const formRaw = this.formularioTarea.getRawValue();
    const descripcion = (formRaw.descripcion ?? '').trim();
    const imagenUrl = (formRaw.imagenUrl ?? '').trim();
    const asignadosIds = Array.isArray(formRaw.asignadosIds)
      ? formRaw.asignadosIds
        .map((id: string | number) => Number(id))
        .filter((id: number, index: number, ids: number[]) => Number.isInteger(id) && id > 0 && ids.indexOf(id) === index)
      : [];
    const tiempoEstimado = formRaw.tiempoEstimado === null || formRaw.tiempoEstimado === ''
      ? null
      : Number(formRaw.tiempoEstimado);

    const payload: FormularioTarea = {
      asignadosIds,
      titulo: formRaw.titulo.trim(),
      descripcion: descripcion || null,
      imagenUrl: imagenUrl || null,
      fechaLimite: formRaw.fechaLimite || null,
      estado: formRaw.estado,
      prioridad: formRaw.prioridad,
      categoria: formRaw.categoria || null,
      porcentajeRealizado: Number(formRaw.porcentajeRealizado),
      tiempoEstimado,
    };

    this.guardarEvent.emit({
      id: this.idTareaEdicion,
      data: payload,
    });
    this.bsModal?.hide();
  }

  agregarAsignado(): void {
    const nuevoAsignadoId = Number(this.formularioTarea.get('nuevoAsignadoId')?.value);
    if (!Number.isInteger(nuevoAsignadoId) || nuevoAsignadoId <= 0) {
      return;
    }

    const asignadosActuales = this.obtenerAsignadosSeleccionados();
    if (asignadosActuales.includes(nuevoAsignadoId)) {
      this.formularioTarea.patchValue({ nuevoAsignadoId: null });
      return;
    }

    this.formularioTarea.patchValue({
      asignadosIds: [...asignadosActuales, nuevoAsignadoId],
      nuevoAsignadoId: null,
    });
  }

  quitarAsignado(usuarioId: number): void {
    this.formularioTarea.patchValue({
      asignadosIds: this.obtenerAsignadosSeleccionados().filter((id) => id !== usuarioId),
    });
  }

  enviarComentario(): void {
    if (!this.tarea) {
      return;
    }

    this.formularioComentario.markAllAsTouched();
    if (this.formularioComentario.invalid) {
      return;
    }

    const formRaw = this.formularioComentario.getRawValue();
    this.agregarComentarioEvent.emit({
      tareaId: this.tarea.id,
      mensaje: formRaw.mensaje.trim(),
    });

    this.formularioComentario.patchValue({ mensaje: '' });
    this.formularioComentario.get('mensaje')?.markAsPristine();
    this.formularioComentario.get('mensaje')?.markAsUntouched();
  }

  obtenerAsignadosSeleccionados(): number[] {
    const rawAsignados = this.formularioTarea.get('asignadosIds')?.value;
    if (!Array.isArray(rawAsignados)) {
      return [];
    }

    return rawAsignados
      .map((id: string | number) => Number(id))
      .filter((id: number, index: number, ids: number[]) => Number.isInteger(id) && id > 0 && ids.indexOf(id) === index);
  }

  obtenerUsuariosAsignables(): Usuario[] {
    const asignados = new Set(this.obtenerAsignadosSeleccionados());
    return this.usuarios.filter((usuario) => !asignados.has(usuario.id));
  }

  obtenerUsuarioPorId(usuarioId: number): Usuario | undefined {
    return this.usuarios.find((usuario) => usuario.id === usuarioId);
  }

  protected formatearFechaLimite(fechaLimite: string | null): string {
    if (!fechaLimite) {
      return 'Sin fecha límite';
    }

    const fecha = new Date(fechaLimite);
    if (Number.isNaN(fecha.getTime())) {
      return fechaLimite;
    }

    return new Intl.DateTimeFormat('es-ES', {
      dateStyle: 'medium',
      timeStyle: 'short',
    }).format(fecha);
  }
}
