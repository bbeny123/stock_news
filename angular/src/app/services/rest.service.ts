import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {tap} from "rxjs/operators";
import {AppConfig} from "../app-config";

@Injectable({
  providedIn: 'root'
})
export class RESTService {

  constructor(private http: HttpClient) {}

}
