import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { IntensiveCare } from '../models/intensive-care.intreface';
import { GenericRestService } from './generic-rest.service';

@Injectable({
  providedIn: 'root'
})
export class IntensiveCareService extends GenericRestService<IntensiveCare> {

  constructor(private http: HttpClient) {
    super(`${environment.baseApiUrl}/intensive-care`, http);
   }


   public fetchTodayIntensiveCare(): Observable<IntensiveCare> {
    return this.http.get<IntensiveCare>(this.baseUrl + '/lastDayIntensiveCare');
  }
}
