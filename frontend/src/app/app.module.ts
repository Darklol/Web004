import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './components/main/app.component';
import {RadioButtonModule} from 'primeng/radiobutton';
import {InputTextModule} from 'primeng/inputtext';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ButtonModule} from 'primeng/button';
import {TableModule} from 'primeng/table';
import {AboutComponent} from './components/about/about.component';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {HistoryComponent} from './components/history/history.component';
import {HttpClientModule} from '@angular/common/http';
import {CheckPointComponent} from './components/check-point/check-point.component';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {PointGuard} from './guard/PointGuard';
import {SidebarModule} from 'primeng/sidebar';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

const appRoutes: Routes = [
    {path: 'home', component: HomeComponent, canActivate: [PointGuard]},
    {path: 'login', component: LoginComponent, canActivate: [PointGuard]},
    {path: 'register', component: RegisterComponent, canActivate: [PointGuard]},
    {path: '**', redirectTo: 'login'}
];

@NgModule({
    declarations: [
        AppComponent,
        AboutComponent,
        HomeComponent,
        HistoryComponent,
        CheckPointComponent,
        LoginComponent,
        RegisterComponent
    ],
    imports: [
        RouterModule.forRoot(appRoutes),
        BrowserModule,
        RadioButtonModule,
        InputTextModule,
        FormsModule,
        ButtonModule,
        ReactiveFormsModule,
        TableModule,
        HttpClientModule,
        SidebarModule,
        BrowserAnimationsModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})

export class AppModule {
}
