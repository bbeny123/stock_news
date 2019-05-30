import {Injectable} from '@angular/core';
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

  isAdmin() {
    return this.loggedIn() && JSON.parse(localStorage.getItem('token')).role === 'ADMIN';
  }

  isOwner(id: number) {
    return this.isAdmin() || JSON.parse(localStorage.getItem('token')).userId === id;
  }

  login(user: UserLogin) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Basic ' + btoa("stock:stock")
      })
    };

    const body = new HttpParams()
      .set('username', user.login)
      .set('password', user.password)
      .set('grant_type', 'password')
      .set(' client_id', 'stock');

    return this.http.post<any>(AppConfig.ENDPOINT_OAUTH, body, options).pipe(
      tap(t => localStorage.setItem('token', JSON.stringify({token: t.access_token, role: t.type, userId: t.userId})))
    );
  }

  registration(user: UserRegistration) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<any>(AppConfig.ENDPOINT_REGISTRATION, user, options);
  }

  activate(token: string) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      params: new HttpParams().set('token', token)
    };

    return this.http.get<any>(AppConfig.ENDPOINT_ACTIVATE, options);
  }

  resend(user: UserResend) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };

    return this.http.post<any>(AppConfig.ENDPOINT_RESEND, user, options);
  }

}
