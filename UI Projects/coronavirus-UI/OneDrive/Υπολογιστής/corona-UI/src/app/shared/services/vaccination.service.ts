import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { VaccinationLastDateInterface } from '../models/vaccinationLastDate.interface';
import { GenericRestService } from './generic-rest.service';

@Injectable({
  providedIn: 'root'
})

export class VaccinationService extends GenericRestService<VaccinationLastDateInterface> {

  constructor(private http: HttpClient) {
    super(`${environment.baseApiUrl}/vaccination`, http);
   }


   public fetchLastDateVaccinationData(): Observable<VaccinationLastDateInterface> {
    return this.http.get<VaccinationLastDateInterface>(this.baseUrl + '/vaccinationLastDayData');
  }


  public fetchAllVaccinationResultsByDate(searchCriteria): Observable<VaccinationLastDateInterface[]> {
    return this.http.post<VaccinationLastDateInterface[]>(this.baseUrl + '/getAllVaccinationResultsByDate', searchCriteria);
  }


  public fetchAllVaccinationListByRegion(searchCriteria): Observable<VaccinationLastDateInterface[]> {
    return this.http.post<VaccinationLastDateInterface[]>(this.baseUrl + '/getAllVaccinationListByRegion', searchCriteria);
  }
}
