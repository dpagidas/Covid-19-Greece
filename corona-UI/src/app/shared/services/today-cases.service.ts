import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CasesListByDate } from '../models/cases-list-by-date.interface';
import { TodayCases } from '../models/today-cases.interface';
import { GenericRestService } from './generic-rest.service';

@Injectable({
  providedIn: 'root'
})
export class TodayCasesService extends GenericRestService<TodayCases> {

  constructor(private http: HttpClient) {
    super(`${environment.baseApiUrl}/confirmed-cases`, http);
   }


   public fetchLastDateConfirmedCases(): Observable<TodayCases> {
    return this.http.get<TodayCases>(this.baseUrl + '/lastDateConfirmedCases');
  }


  public fetchAllCasesListByDate(searchCriteria): Observable<CasesListByDate[]> {
    return this.http.post<CasesListByDate[]>(this.baseUrl + '/getListCasesDataByDate', searchCriteria);
  }
}
