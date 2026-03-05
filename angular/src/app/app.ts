import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {TableroTareasComponent} from './tareas/pages/tablero-tareas/tablero-tareas';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, TableroTareasComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('angular');
}
