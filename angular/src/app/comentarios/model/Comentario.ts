import { Usuario } from '../../usuarios/model/Usuario';

export interface Comentario {
  id: number;
  autor: Usuario;
  contenido: string;
  fecha: string;
}
