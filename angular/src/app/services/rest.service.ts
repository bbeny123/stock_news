import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {AppConfig} from "../app-config";
import {NewsList} from "../model/news-list";

@Injectable({
  providedIn: 'root'
})
export class RESTService {

  constructor(private http: HttpClient) {}

  getNewsList(page: number = 1) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      params: new HttpParams().set('page', (page - 1) + "")
    };

    return this.http.get<NewsList>(AppConfig.ENDPOINT_NEWS, options);
  }

}
