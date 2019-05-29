import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {AuthService} from "./services/auth.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MustMatch} from "./util/must-match.validator";
import {AppConfig} from "./app-config";
import {Subscription} from "rxjs";
import {Alert} from "./model/alert";
import {ReCaptcha2Component} from "ngx-captcha";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.styl']
})
export class AppComponent implements OnInit {

  @ViewChild('submenuElem') submenuRef: ElementRef;
  @ViewChild('captchaElem') captchaElem: ReCaptcha2Component;
  siteKey = AppConfig.CAPTCHA_SITE_KEY;
  userForm: FormGroup;
  registerForm: FormGroup;
  resendForm: FormGroup;
  isNavbarCollapsed = true;
  submenu = 1;
  busy: Subscription;
  alerts: Alert[] = [];
  alertsSubmenu: Alert[] = [];

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

    this.busy = this.authService.login(this.userForm.value).subscribe(() => {
        this.reset();
        this.alerts = [Alert.success('You have successfully logged in. Hello!')];
      },
      err => {
        this.alertsSubmenu.push(Alert.warning(err.status == 400 ? "Incorrect credentials!" : "Connection error!"));
      });

    console.log(this.alertsSubmenu);
  }

  onSubmitRegistration() {
    if (this.registerForm.invalid) {
      return;
    }

    this.busy = this.authService.registration(this.registerForm.value).subscribe(() => {
        this.submenuRef.nativeElement.click();
        this.alerts = [Alert.success('You have successfully registered. You must confirm your email address before you can log in.')];
      },
      err => {
        this.resetCaptcha();
        this.captchaElem.reloadCaptcha();
        this.alertsSubmenu.push(Alert.warning(err.error.message));
      }
    );
  }

  onSubmitResend() {
    if (this.resendForm.invalid) {
      return;
    }

    this.busy = this.authService.resend(this.resendForm.value).subscribe(() => {
      this.submenuRef.nativeElement.click();
      this.alerts = [Alert.success('An email with the activation link has been resend.')];
    }, err => {
      this.alertsSubmenu.push(Alert.warning(err.error.message));
    });
  }

  logout() {
    this.authService.logout();
    this.alerts = [Alert.success('You have successfully logged out. See you soon!')];
  }

  reset() {
    this.userForm.reset();
    this.registerForm.reset();
    this.resendForm.reset();
  }

  resetCaptcha() {
    this.registerForm.patchValue({captchaResponse: ''});
  }

  closeAlert(alert: Alert) {
    this.alerts.splice(this.alerts.indexOf(alert), 1);
  }

  closeAlertSubmenu(alert: Alert) {
    this.alertsSubmenu.splice(this.alertsSubmenu.indexOf(alert), 1);
  }

  resetAlerts() {
    this.alerts = [];
  }

  resetAlertsSubmenu() {
    this.alertsSubmenu = [];
  }

}
