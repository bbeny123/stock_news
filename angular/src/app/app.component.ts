import {Component, OnInit} from '@angular/core';
import {AuthService} from "./services/auth.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MustMatch} from "./util/must-match.validator";
import {AppConfig} from "./app-config";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.styl']
})
export class AppComponent implements OnInit {

  siteKey = AppConfig.CAPTCHA_SITE_KEY;
  userForm: FormGroup;
  registerForm: FormGroup;
  resendForm: FormGroup;
  isNavbarCollapsed = true;
  authTooltip = 1;
  busy: Subscription;

  constructor(private authService: AuthService, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.userForm = this.formBuilder.group({
      login: ['', [Validators.required, Validators.maxLength(30)]],
      password: ['', [Validators.required, Validators.maxLength(255)]]
    });

    this.registerForm = this.formBuilder.group({
      login: ['', [Validators.required, Validators.maxLength(30)]],
      email: ['', [Validators.required, Validators.email, Validators.maxLength(60)]],
      password: ['', [Validators.required, Validators.maxLength(255)]],
      confirmPassword: ['', [Validators.required, Validators.maxLength(255)]],
      name: ['', [Validators.maxLength(120)]],
      captchaResponse: ['', [Validators.required]]
    }, {
      validator: MustMatch('password', 'confirmPassword')
    });

    this.resendForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email, Validators.maxLength(60)]]
    });
  }

  onSubmitLogin() {
    if (this.userForm.invalid) {
      return;
    }

    this.busy = this.authService.login(this.userForm.value).subscribe(data => {
        this.reset();
      },
      err => {
        alert(err.status == 400 ? "Incorrect credentials!" : "Connection error!");
      });
  }

  onSubmitRegistration() {
    if (this.registerForm.invalid) {
      return;
    }

    this.busy = this.authService.registration(this.registerForm.value).subscribe(data => {
        this.authTooltip = 1;
      },
      err => {
        alert(err.error.message);
      }
    );
  }

  onSubmitResend() {
    if (this.resendForm.invalid) {
      return;
    }

    this.busy = this.authService.resend(this.resendForm.value).subscribe(data => {
      this.authTooltip = 1;
    }, err => {
      alert(err.error.message);
    });
  }

  reset() {
    this.userForm.reset();
    this.registerForm.reset();
    this.resendForm.reset();
  }

  resetCaptcha() {
    this.registerForm.patchValue({captchaResponse: ''});
  }

}
