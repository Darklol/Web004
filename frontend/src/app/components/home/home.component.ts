import {Component, OnInit} from '@angular/core';
import {User} from '../../models/User';
import {LoginService} from '../../services/login.service';
import {Router} from '@angular/router';
import {PointsService} from '../../services/points.service';
import {Point} from '../../models/Point';
import {NetworkUtil} from '../../utils/NetworkUtil';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html'
})

export class HomeComponent implements OnInit {

    isVisibleSideBar: boolean = false;
    currentUser: User = JSON.parse(localStorage.getItem('currentUser'));
    points: Point[];

    constructor(private loginService: LoginService,
                private router: Router,
                private pointsService: PointsService) {
    }

    onLogOutClick(): void {
        this.loginService.logOut();
        this.router.navigate(['/login']);
    }

    onSendNewPoint(): void {
        this.loadPoints();
    }

    ngOnInit(): void {
        this.loadPoints();
    }

    private loadPoints(): void {
        this.pointsService.getAllPoints().subscribe(
            (points: Point[]) => this.points = points,
            error => NetworkUtil.authFailed()
        );
    }
}
