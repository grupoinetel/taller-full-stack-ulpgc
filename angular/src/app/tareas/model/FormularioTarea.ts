import {CategoriaTarea} from './CategoriaTarea';
import {PrioridadTarea} from './PrioridadTarea';
import {EstadoTarea} from './EstadoTarea';

export interface FormularioTarea {
  asignadosIds: number[];
  nuevoAsignadoId?: number;
  titulo: string;
  descripcion?: string;
  imagenUrl?: string;
  fechaLimite?: string;
  estado: EstadoTarea;
  prioridad: PrioridadTarea;
  categoria?: CategoriaTarea;
  porcentajeRealizado: number;
  tiempoEstimado: number;
}
