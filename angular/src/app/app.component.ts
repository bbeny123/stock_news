import {Component, OnInit} from '@angular/core';
import {AuthService} from "./services/auth.service";
import {User} from "./model/user";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.styl']
})
export class AppComponent implements OnInit {

  userForm: FormGroup;
  isNavbarCollapsed = true;

  constructor(private authService: AuthService, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.userForm = this.formBuilder.group({
      login: ['', [Validators.required, Validators.maxLength(30)]],
      password: ['', [Validators.required, Validators.maxLength(255)]]
    });
  }

  onSubmit() {
    if (this.userForm.invalid) {
      return;
    }

    this.authService.login(this.userForm.value).subscribe(data => {}, err => {
      alert(err.status == 400 ? "Incorrect credentials!" : "Connection error!");
    });
  }

}
