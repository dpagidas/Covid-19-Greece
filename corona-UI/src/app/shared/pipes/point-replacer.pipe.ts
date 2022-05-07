import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'pointReplacer'
})

export class PointReplacerPipe implements PipeTransform {
    transform(value: string, args: any[]): string {
        if(value) {
            console.log(value);
          return value.replace(/,/g, '.');
        }
        return '';
    }
}