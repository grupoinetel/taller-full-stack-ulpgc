export enum PrioridadTarea {
  BAJA = 'BAJA',
  MEDIA = 'MEDIA',
  ALTA = 'ALTA',
  URGENTE = 'URGENTE',
}

export const PRIORIDAD_TAREA_LABELS: Record<PrioridadTarea, string> = {
  BAJA: 'Baja',
  MEDIA: 'Media',
  ALTA: 'Alta',
  URGENTE: 'Urgente',
};

export const PRIORIDAD_TAREA_COLORES: Record<PrioridadTarea, string> = {
  BAJA: 'success',
  MEDIA: 'primary',
  ALTA: 'warning',
  URGENTE: 'danger',
};
