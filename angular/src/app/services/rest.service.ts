import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RESTService {

  constructor(private http: HttpClient) {
  }

  login(q: string): Observable<any> {
    const params = new HttpParams()
      .set('username', 'admin')
      .set('password', 'admin')
      .set('grant_type', 'password')
      .set('client_id', 'stock');

    const options = {
      headers: new HttpHeaders({
        'Authorization': 'Basic ' + btoa("stock:stock")
      })
    };

    return this.http.post<String>('http://localhost:8080/oauth/token', params, options);
  }

}
