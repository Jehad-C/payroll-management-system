import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { EmployeeComponent } from './components/employee/employee.component';
import { HomeComponent } from './components/home/home.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'registration', component: RegistrationComponent },
    { path: 'employees', component: EmployeeListComponent },
    { path: 'employee/id/:id', component: EmployeeComponent },
    { path: 'home', component: HomeComponent }
];
