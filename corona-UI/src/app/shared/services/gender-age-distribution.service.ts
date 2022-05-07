import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { GenderAgeDistribution } from '../models/gender-age-distribution.interface';
import { GenericRestService } from './generic-rest.service';

@Injectable({
  providedIn: 'root'
})
export class GenderAgeDistributionService extends GenericRestService<GenderAgeDistribution> {

  constructor(private http: HttpClient) {
    super(`${environment.baseApiUrl}/gender-age-distribution`, http);
  }

  public fetchGenderAgeDistributionHistory(): Observable<GenderAgeDistribution> {
    return this.http.get<GenderAgeDistribution>(this.baseUrl + '/all');
  }
}
