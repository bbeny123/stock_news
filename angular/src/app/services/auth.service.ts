import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {AppConfig} from "../app-config";
import {tap} from "rxjs/operators";
import {User} from "../model/user";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private router: Router) {}

  login(user: User) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Basic ' + btoa("stock:stock")
      }),
    };

    const params = new HttpParams()
      .set('username', user.login)
      .set('password', user.password)
      .set('grant_type', 'password')
      .set(' client_id', 'stock');

    return this.http.post<any>(AppConfig.ENDPOINT_OAUTH, params, options).pipe(
      tap(t => localStorage.setItem('token', t.access_token))
    );
  }

  logout() {
    localStorage.removeItem("token");
    this.router.navigate(['/']);
  }

  loggedIn() {
    return localStorage.getItem('token') !== null;
  }

}
