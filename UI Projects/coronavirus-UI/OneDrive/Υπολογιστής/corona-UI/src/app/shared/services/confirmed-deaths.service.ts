import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ConfirmedDeaths } from '../models/confirmed-deaths.interface';
import { GenericRestService } from './generic-rest.service';

@Injectable({
  providedIn: 'root'
})
export class ConfirmedDeathsService extends GenericRestService<ConfirmedDeaths> {

  constructor(private http: HttpClient) {
    super(`${environment.baseApiUrl}/confirmed-deaths`, http);
   }


   public fetchConfirmedDeaths(): Observable<ConfirmedDeaths> {
    return this.http.get<ConfirmedDeaths>(this.baseUrl + '/lastDayConfirmedDeaths');
  }
}
