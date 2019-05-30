import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AuthService} from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthAdminGuard implements CanActivate {

  constructor(private router: Router, private authService: AuthService) {}

  canActivate(): boolean {
    if (this.authService.isAdmin()) {
      return true;
    }
    this.router.navigate(['/']);
    return false;
  }

}
