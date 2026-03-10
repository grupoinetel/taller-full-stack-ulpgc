import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from '@angular/forms';
import {DetalleTarea} from '../../model/DetalleTarea';
import {CATEGORIA_TAREA_LABELS, CategoriaTarea} from '../../model/CategoriaTarea';
import {ESTADO_TAREA_LABELS, EstadoTarea} from '../../model/EstadoTarea';
import {PRIORIDAD_TAREA_LABELS, PrioridadTarea} from '../../model/PrioridadTarea';
import {Avatar} from '../../../comun/components/avatar/avatar';
import {Usuario} from '../../../usuarios/model/Usuario';
import {FormularioTarea} from '../../model/FormularioTarea';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-formulario-modal-tarea',
  imports: [
    Avatar,
    ReactiveFormsModule,
    NgClass,
  ],
  templateUrl: './formulario-modal-tarea.html',
  styleUrl: './formulario-modal-tarea.scss',
})
export class FormularioModalTarea implements OnChanges {
  @Input() tarea?: DetalleTarea;
  @Input() usuarios: Usuario[] = [];
  @Input() dataLoaded!: boolean;

  @Output() guardarEvent = new EventEmitter<{id?: number; data: FormularioTarea}>();

  estados: EstadoTarea[] = Object.values(EstadoTarea);
  prioridades: PrioridadTarea[] = Object.values(PrioridadTarea);
  categorias: CategoriaTarea[] = Object.values(CategoriaTarea);

  protected readonly CATEGORIA_TAREA_LABELS = CATEGORIA_TAREA_LABELS;
  protected readonly ESTADO_TAREA_LABELS = ESTADO_TAREA_LABELS;
  protected readonly PRIORIDAD_TAREA_LABELS = PRIORIDAD_TAREA_LABELS;

  formularioTarea: any;
  readonly FORMULARIO_POR_DEFECTO: FormularioTarea = {
    asignadosIds: [],
    nuevoAsignadoId: undefined,
    titulo: '',
    descripcion: '',
    imagenUrl: '',
    fechaLimite: '',
    estado: EstadoTarea.PENDIENTE,
    prioridad: PrioridadTarea.MEDIA,
    categoria: undefined,
    porcentajeRealizado: 0,
    tiempoEstimado: 0,
  };
  private idTareaEdicion?: number;

  constructor(private readonly formBuilder: FormBuilder) {
    this.construirFormulario();

  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['tarea']) {
      if (this.tarea) {
        this.inicializarFormularioParaEditar(this.tarea);
      } else {
        this.inicializarFormularioParaCrear();
      }
      return;
    }
  }

  construirFormulario(): void {
    this.formularioTarea = this.formBuilder.group({
      asignadosIds: [this.FORMULARIO_POR_DEFECTO.asignadosIds],
      nuevoAsignadoId: [this.FORMULARIO_POR_DEFECTO.nuevoAsignadoId],
      titulo: [this.FORMULARIO_POR_DEFECTO.titulo, [Validators.required, Validators.maxLength(250)]],
      descripcion: [this.FORMULARIO_POR_DEFECTO.descripcion],
      imagenUrl: [this.FORMULARIO_POR_DEFECTO.imagenUrl],
      fechaLimite: [this.FORMULARIO_POR_DEFECTO.fechaLimite],
      estado: [this.FORMULARIO_POR_DEFECTO.estado],
      prioridad: [this.FORMULARIO_POR_DEFECTO.prioridad],
      categoria: [this.FORMULARIO_POR_DEFECTO.categoria],
      porcentajeRealizado: [this.FORMULARIO_POR_DEFECTO.porcentajeRealizado, [Validators.required, Validators.min(0), Validators.max(100)]],
      tiempoEstimado: [this.FORMULARIO_POR_DEFECTO.tiempoEstimado, [Validators.min(0), Validators.max(99999.99)]],
    });
  }

  inicializarFormularioParaCrear(): void {
    this.idTareaEdicion = undefined;
    this.formularioTarea.reset(this.FORMULARIO_POR_DEFECTO);
  }

  inicializarFormularioParaEditar(tarea: DetalleTarea): void {
    this.idTareaEdicion = tarea.id;
    this.formularioTarea.reset({
      ...tarea,
      asignadosIds: tarea.asignados.map(u => u.id),
      nuevoAsignadoId: undefined,
    });
  }

  guardarFormulario(): void {
    this.formularioTarea.markAllAsTouched();
    if (this.formularioTarea.invalid) {
      //TODO: Aquí se puede añadir también alguna alerta
      window.alert('Existen errores en el formulario. Por favor, corrija los errores antes de guardar.');
      return;
    }

    const valoresFormulario: FormularioTarea = this.formularioTarea.getRawValue();

    const formularioEditado: FormularioTarea = {
      ...valoresFormulario,
      titulo: valoresFormulario.titulo.trim(),
      descripcion: valoresFormulario.descripcion?.trim(),
      imagenUrl: valoresFormulario.imagenUrl?.trim(),
    };

    this.guardarEvent.emit({
      id: this.idTareaEdicion,
      data: formularioEditado,
    });
  }

  agregarAsignado(): void {
    const nuevoAsignadoId = this.formularioTarea.get('nuevoAsignadoId')?.value;
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

  obtenerAsignadosSeleccionados(): number[] {
    return this.formularioTarea.get('asignadosIds')?.value;
  }

  obtenerUsuariosAsignables(): Usuario[] {
    //TODO: Estos valores tienen que venir de una petición que se haya hecho anteriormente para obtener los usuarios
    const asignados = new Set(this.obtenerAsignadosSeleccionados());
    return this.usuarios.filter((usuario) => !asignados.has(usuario.id));
  }

  obtenerUsuarioPorId(usuarioId: number): Usuario | undefined {
    return this.usuarios.find((usuario) => usuario.id === usuarioId);
  }
}
