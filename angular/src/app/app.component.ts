import { Component } from '@angular/core';
import {AuthService} from "./services/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.styl']
})
export class AppComponent {

  title: String;
  isNavbarCollapsed = true;

  constructor(private authService: AuthService) {
    console.log('a');

    authService.login('admin', 'admin').subscribe(data => {}, err => {
      alert(err.status == 400 ? "Incorrect credentials!" : "Connection error!");
    });

    this.title = 'asd';
    // authService.checkToken().subscribe(d => console.log(d));
  }


}
