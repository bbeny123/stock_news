import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {AppConfig} from "../app-config";
import {tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {}

  login(login, password: string) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Basic ' + btoa("stock:stock")
      }),
    };

    const params = new HttpParams()
      .set('username', login)
      .set('password', password)
      .set('grant_type', 'password')
      .set(' client_id', 'stock');

    return this.http.post<any>(AppConfig.ENDPOINT_OAUTH, params, options).pipe(
      tap(t => localStorage.setItem('token', t.access_token))
    );
  }

  checkToken() {
    const options = {
      headers: new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded'})
    };

    const params = new HttpParams()
      .set('token', localStorage.getItem('token'));

    return this.http.post(AppConfig.ENDPOINT_OAUTH_CHECK, params, options);
  }

}
