import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'numeroTarea',
})
export class NumeroTareaPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return '#' + value;
  }

}
