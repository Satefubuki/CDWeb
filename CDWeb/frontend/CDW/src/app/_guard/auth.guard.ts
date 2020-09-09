import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {UserService} from './../services/user.service';
import {CookieService} from 'ngx-cookie-service';
import { AuthService } from './../services/auth.service';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {


    constructor(
        private router: Router,
        private userService: UserService,
        private cookieService: CookieService,
        private authService: AuthService
    ) {
    }

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        const currentUser = this.cookieService.get('role');
        if (currentUser) {
            // check if route is restricted by role
            if (route.data.roles && route.data.roles.indexOf(currentUser) === -1) {
                // role not authorised so redirect to home page
                this.router.navigate(['/unauthorized']);
                return false;
            }
            // authorised so return true
            return true;
        }
        // not logged in so redirect to login page with the return url{queryParams: {returnUrl: state.url}}
        if (!this.authService.isLoggedIn) {
            this.router.navigate(['/login']);
          }
        return false;
    }
}