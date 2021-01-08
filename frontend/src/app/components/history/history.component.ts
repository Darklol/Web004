import {Component, ElementRef, Input, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {PointsService} from '../../services/points.service';
import {Point} from '../../models/Point';
import {Table} from 'primeng/table';

@Component({
    selector: 'app-history',
    templateUrl: './history.component.html'
})
export class HistoryComponent {

    @ViewChild('dataTable', {static: false})
    dataTable: Table;

    @Input() points: Point[];
    columns: any[] = [
        {field: 'x', header: 'X'},
        {field: 'y', header: 'Y'},
        {field: 'r', header: 'R'},
        {field: 'inArea', header: 'Result'},
        {field: 'queryTime', header: 'Current time'}
    ];
}
