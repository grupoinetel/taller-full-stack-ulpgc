import { Usuario } from '../../usuarios/model/Usuario';
import { Comentario } from '../../comentarios/model/Comentario';
import { PreviewTarea } from './PreviewTarea';
import {CategoriaTarea} from './CategoriaTarea';

export interface DetalleTarea extends PreviewTarea {
  descripcion: string | null;
  fechaCreacion: string;
  autor: Usuario;
  categoria: CategoriaTarea | null;
  tiempoEstimado: number | null;
  porcentajeRealizado: number;
  asignados: Usuario[];
  comentarios: Comentario[];
}
