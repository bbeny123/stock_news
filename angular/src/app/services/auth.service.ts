import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {AppConfig} from "../app-config";
import {tap} from "rxjs/operators";
import {UserLogin} from "../model/userLogin";
import {Router} from "@angular/router";
import {UserRegistration} from "../model/userRegistration";
import {UserResend} from "../model/userResend";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private router: Router) {}

  logout() {
    localStorage.removeItem("token");
    this.router.navigate(['/']);
  }

  loggedIn() {
    return localStorage.getItem('token') !== null;
  }

  login(user: UserLogin) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Basic ' + btoa("stock:stock")
      }),
    };

    const body = new HttpParams()
      .set('username', user.login)
      .set('password', user.password)
      .set('grant_type', 'password')
      .set(' client_id', 'stock');

    return this.http.post<any>(AppConfig.ENDPOINT_OAUTH, body, options).pipe(
      tap(t => localStorage.setItem('token', t.access_token))
    );
  }

  registration(user: UserRegistration) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<any>(AppConfig.ENDPOINT_REGISTRATION, user, options);
  }

  resend(user: UserResend) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
    };

    return this.http.post<any>(AppConfig.ENDPOINT_RESEND, user, options);
  }

}
