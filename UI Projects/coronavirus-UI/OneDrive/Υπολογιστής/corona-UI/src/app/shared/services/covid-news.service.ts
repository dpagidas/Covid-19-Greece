import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Covid19News } from '../models/covid-19.interface';
import { GenericRestService } from './generic-rest.service';

@Injectable({
  providedIn: 'root'
})
export class CovidNewsService extends GenericRestService<Covid19News> {

  constructor(private http: HttpClient) {
    super(`${environment.baseApiUrl}/covid19News`, http);
  }
}
