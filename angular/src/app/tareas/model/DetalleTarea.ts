import { Usuario } from '../../usuarios/model/Usuario';
import { Comentario } from '../../comentarios/model/Comentario';
import { PreviewTarea } from './PreviewTarea';

export interface DetalleTarea extends PreviewTarea {
  descripcion: string;
  fechaCreacion: string;
  porcentajeRealizado: number;
  tiempoEstimado: number;
  autor: Usuario;
  asignados: Usuario[];
  comentarios: Comentario[];
}
