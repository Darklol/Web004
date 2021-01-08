import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {NetworkUtil} from '../utils/NetworkUtil';

@Injectable({providedIn: 'root'})
export class PointGuard implements CanActivate {

    constructor(private router: Router) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
        const isAuth: boolean = NetworkUtil.checkUserData();

        if (!isAuth && state.url.match(/\/(home)$/gi)) {
            this.router.navigate(['/login']);
            return false;
        }

        if (isAuth && state.url.match(/\/(login|register)$/gi)) {
            this.router.navigate(['/home']);
            return false;
        }

        return true;
    }
}
