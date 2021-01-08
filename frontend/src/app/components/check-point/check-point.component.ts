import {
    AfterViewInit,
    Component,
    ElementRef,
    EventEmitter,
    Input,
    OnChanges,
    OnInit,
    Output,
    SimpleChanges,
    ViewChild
} from '@angular/core';
import {FormBuilder, FormGroup, ValidationErrors, Validators} from '@angular/forms';
import {PointsService} from '../../services/points.service';
import {DrawService} from '../../services/draw.service';
import {Point} from '../../models/Point';
import {NetworkUtil} from '../../utils/NetworkUtil';

@Component({
    selector: 'app-check-point',
    templateUrl: './check-point.component.html'
})
export class CheckPointComponent implements OnInit, OnChanges, AfterViewInit {

    @ViewChild('plot', {static: false}) plot: ElementRef;
    @Input() points: Point[];
    @Output() onSendNewPoint: EventEmitter<boolean> = new EventEmitter<boolean>();

    x: string;
    y: string;
    r: string;
    dataForm: FormGroup;
    isRSelected: boolean = true;

    constructor(private formBuilder: FormBuilder,
                private pointsService: PointsService,
                private drawService: DrawService) {
    }

    ngOnInit(): void {
        this.dataForm = this.formBuilder.group({
            xValue: ['', [Validators.required]],
            yValue: ['', [Validators.required, Validators.max(3), Validators.min(-3),
                Validators.pattern(/^(-?\d+)(\.\d+)?$/)]],
            rValue: ['', [Validators.required]]
        });
    }

    hasFormErrors(): boolean {
        return this.dataForm.invalid;
    }

    fieldErrors(field: string): ValidationErrors | null {
        let fieldState = this.dataForm.controls[field];
        return fieldState.dirty && fieldState.errors ? fieldState.errors : null;
    }

    onSubmitClick(): void {
        const pointToSend: Point = new Point(0, parseFloat(this.x), parseFloat(this.y), parseFloat(this.r), false, new Date());
        this.pointsService.addPoint(pointToSend).subscribe((data: Response) => {
            this.onSendNewPoint.emit(true);
        }, error => NetworkUtil.authFailed());
    }

    onRRadioClick(): void {
        this.drawService.clearPlot(this.plot);
        this.drawAllPoints();
    }

    checkRSelected(): boolean {
        this.isRSelected = this.r !== undefined;
        return this.isRSelected;
    }

    onPlotClick(event: MouseEvent): void {
        const offset = this.plot.nativeElement.getBoundingClientRect();
        const x: number = event.pageX - offset.left;
        const y: number = event.pageY - offset.top;

        if (this.checkRSelected()) {
            const rValue: number = this.drawService.getRValue(this.r);
            const xValue: number = this.drawService.fromSvgToRX(x, rValue);
            const yValue: number = this.drawService.fromSvgToRY(y, rValue);

            const pointToSend: Point = new Point(0, xValue, yValue, rValue, false, new Date());
            // error if localStorage data isn't valid
            this.pointsService.addPoint(pointToSend).subscribe((data: Response) => {
                this.onSendNewPoint.emit(true);
            }, error => NetworkUtil.authFailed());
        }
    }

    onClearFormClick(): void {
        this.dataForm.reset();
        // because model saves r and it's bad for graphics
        this.r = undefined;
    }

    ngOnChanges(changes: SimpleChanges): void {
        this.onRRadioClick();
    }

    ngAfterViewInit(): void {
        this.drawAllPoints();
    }

    drawAllPoints(): void {
        this.points.forEach(point =>
            this.drawService.drawPoint(point, this.plot, this.r));
    }
}

