import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CasesListsByRegionInterface } from '../models/casesListByRegion.interface';
import { GenericRestService } from './generic-rest.service';

@Injectable({
  providedIn: 'root'
})
export class RegionCasesHistoryService extends GenericRestService<any> {

  constructor(private http: HttpClient) {
    super(`${environment.baseApiUrl}/region-cases-history`, http);
   }

   public fetchAllCasesListByRegion(searchCriteria): Observable<CasesListsByRegionInterface[]> {
    return this.http.post<CasesListsByRegionInterface[]>(this.baseUrl + '/getAllCasesListByRegion', searchCriteria);
  }
}
