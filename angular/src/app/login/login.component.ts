import { Component, OnInit, Input } from '@angular/core';
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @Input() restartVisible: boolean = false;

  constructor(private authService : AuthService) { }

  ngOnInit() {

  }

  onSubmit() {
    // this.authService.login(this.user).subscribe(data => {}, err => {
    //     alert(err.status == 400 ? "Incorrect credentials!" : "Connection error!");
    // });
  }

  logout() { 
    // this.authService.logout();
  }

  reload() { 
    window.location.reload()
  }
}
