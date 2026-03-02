import { EstadoTarea } from './EstadoTarea';
import { PrioridadTarea } from './PrioridadTarea';

export interface PreviewTarea {
  id: number;
  numero: number;
  titulo: string;
  imagenUrl?: string;
  fechaLimite: string | null;
  estado: EstadoTarea;
  prioridad: PrioridadTarea;
}
