import { Component } from '@angular/core';
import {RESTService} from "./services/rest.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.styl']
})
export class AppComponent {

  title: String;

  constructor(private restService: RESTService) {
    this.restService.login('a').subscribe(data => this.title = data.access_token);
  }


}
