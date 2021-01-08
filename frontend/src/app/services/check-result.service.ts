import {Injectable} from '@angular/core';

@Injectable({providedIn: 'root'})
export class CheckResultService {

    getResult(x, y, r): boolean {
        return this.isInCircle(x, y, r) || this.isInTriangle(x, y, r) || this.isInRectangle(x, y, r);
    }

    private isInCircle(x, y, r): boolean {
        return x <= 0 && y <= 0 && r * r / 4 >= x * x + y * y;
    }

    private isInTriangle(x, y, r): boolean {
        return x <= 0 && y >= 0 && y <= x + r / 2;
    }

    private isInRectangle(x, y, r): boolean {
        return x >= 0 && y >= 0 && y <= r / 2 && x < r;
    }
}

