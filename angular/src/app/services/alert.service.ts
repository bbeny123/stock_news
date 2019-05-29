import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AlertService {

  public alert: Alert;
  public alertsSubmenu: Alert;

  constructor() { }

  public success(msg: string) {
    this.alert = {type: 'success', message: msg};
  }

  public successSubmenu(msg: string) {
    this.alertsSubmenu = {type: 'success', message: msg};
  }

  public warning(msg: string) {
    this.alert = {type: 'danger', message: msg};
  }

  public warningSubmenu(msg: string) {
    this.alertsSubmenu = {type: 'danger', message: msg};
  }

  closeAlert() {
    this.alert = null;
  }

  closeAlertSubmenu() {
    this.alertsSubmenu = null;
  }

}

export interface Alert {

  type: string;
  message: string;

}
