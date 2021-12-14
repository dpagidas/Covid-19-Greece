import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Page} from '../models/paging/page.interface';
import {environment} from '../../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class GenericRestService<T> {


  // tslint:disable-next-line:variable-name
  private _baseUrl: string;

  public get baseUrl(): string {
    return this._baseUrl;
  }

  constructor(url: string, private httpService: HttpClient) {
    // TODO when we unify the backend will be like that but for now
    // if (url.endsWith('/')) {
    //   throw Error('Malformed url: ' + url + '\nEnds with slash');
    // }
    // if (url.startsWith('/')) {
    //   throw Error('Malformed url: ' + url + '\nStarts with slash');
    // }
    // this._baseUrl = environment.ApiUrl + '/' + url;
    // will be like this
    if (url.endsWith('/')) {
        throw Error('Malformed url: ' + url + '\nEnds with slash');
      }
    if (url.startsWith('/')) {
      throw Error('Malformed url: ' + url + '\nStarts with slash');
    }
    this._baseUrl = url;
    // this remains unchanged
    this._baseUrl = url;
  }


  public getAll(): Observable<T[]> {
    return this.httpService.get<T[]>(this.baseUrl + '/all');
  }

  // public getAllPaged(page: number, offset: number): Observable<Page<T>> {
  //   if (!environment.production && offset !== 10) {

  //     console.log('DEV MESSAGE: The deal is 10 results per page. Your offset became 10.I only show up in non-prod environment :)');
  //   }
  //   return this.httpService.get<Page<T>>(this.baseUrl + '/all', {
  //     params: {
  //       page: page.toString(),
  //       offset: '10'
  //     }
  //   });
  // }


  public searchPagedWithoutCriteria(page: number, offset: number,  urlExtension: string): Observable<Page<T>> {
    // TODO make this better
    if (urlExtension) {
      if (urlExtension.endsWith('/')) {
        throw Error('Malformed url: ' + urlExtension + '\nEnds with slash');

      }
      if (urlExtension.startsWith('/')) {
        throw Error('Malformed url: ' + urlExtension + '\nStarts with slash');
      }
      urlExtension = '/' + urlExtension;
    } else {
      urlExtension = '';
    }

    return this.httpService.post<Page<T>>(this.baseUrl + urlExtension, {} , {
      params: {
        page: page.toString(),
        offset: offset.toString()
      }
    });
  }

  // public getById(id: string | number): Observable<T> {
  //   return this.httpService.get<T>(this.baseUrl + '/' + id.toString());
  // }

  // public update(data: T): Observable<T> {
  //   return this.httpService.put<T>(this.baseUrl, data);
  // }

  // public create(data: T): Observable<T> {
  //   return this.httpService.post<T>(this.baseUrl, data);
  // }

  // public delete(id: string | number): Observable<T> {
  //   return this.httpService.delete<T>(this.baseUrl + '/' + id.toString());
  // }

  // public deleteById(data: T): Observable<T> {
  //   return this.httpService.delete<T>(this.baseUrl, data);
  // }


  public searchByCriteriaPaged(criteria: any,page: number, pageSize: number,  urlExtension: string): Observable<Page<T>> {
    // TODO make this better
    if (!environment.production && pageSize !== 10) {

      console.log('DEV MESSAGE: The deal is 10 results per page. Your offset became 10.I only show up in non-prod environment :)');
    }
    if (urlExtension) {
      if (urlExtension.endsWith('/')) {
        throw Error('Malformed url: ' + urlExtension + '\nEnds with slash');

      }
      if (urlExtension.startsWith('/')) {
        throw Error('Malformed url: ' + urlExtension + '\nStarts with slash');
      }
      urlExtension = '/' + urlExtension;
    } else {
      urlExtension = '';
    }

    return this.httpService.post<Page<T>>(this.baseUrl + urlExtension, criteria, {
      params: {
        page: page.toString(),
        pageSize: '10'
      }
    });
  }
}
