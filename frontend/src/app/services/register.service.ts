import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../models/User';
import {Observable} from 'rxjs';
import {NetworkUtil} from '../utils/NetworkUtil';

@Injectable({
    providedIn: 'root'
})
export class RegisterService {

    constructor(private httpClient: HttpClient) {
    }

    register(user: User): Observable<any> {
        return this.httpClient.post(NetworkUtil.REGISTER_URL, JSON.stringify(user),
            {headers: new HttpHeaders().set("Content-Type", "application/json")});
    }
}
