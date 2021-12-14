import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TodayTests } from '../models/today-tests.interface';
import { GenericRestService } from './generic-rest.service';

@Injectable({
  providedIn: 'root'
})
export class TodayTestsService extends GenericRestService<TodayTests> {

  constructor(private http: HttpClient) {
    super(`${environment.baseApiUrl}/total-tests`, http);
   }


   public fetchLastTodayTests(): Observable<TodayTests> {
    return this.http.get<TodayTests>(this.baseUrl + '/lastDayTotalTests');
  }
}
