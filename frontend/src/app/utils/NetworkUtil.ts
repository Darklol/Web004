import {User} from '../models/User';

export class NetworkUtil {

    static REGISTER_URL: string = 'http://localhost:1498/register';
    static USERS_URL: string = 'http://localhost:1498/api/users/';
    static LOGIN_URL: string = 'http://localhost:1498/login';

    static clearStorage(): void {
        localStorage.clear();
    }

    static clearUserData(): void {
        localStorage.removeItem('currentUser');
        localStorage.removeItem('basic64Credentials');
    }

    static checkUserData(): boolean {
        return localStorage.getItem('currentUser') !== null && localStorage.getItem('basic64Credentials') !== null;
    }

    static authFailed(): void {
        this.clearUserData();
        window.location.href = '/login';
    }

    static authSuccess(userWithId: User, userWithOpenPass: User): void {
        localStorage.setItem('currentUser', JSON.stringify(userWithId));
        localStorage.setItem('basic64Credentials', btoa(userWithOpenPass.login + ':' + userWithOpenPass.hashPass));
        window.location.href = '/login';
    }
}
