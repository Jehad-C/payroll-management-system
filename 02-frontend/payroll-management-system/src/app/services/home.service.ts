import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  static readonly BASE_URL = 'http://localhost:8080/api';
  static readonly EMPLOYEES_URL = `${HomeService.BASE_URL}/employees`;

  constructor(
    private httpClient: HttpClient,
    private router: Router
  ) { }

  getEmployees(): Observable<any[]> {
    return this.httpClient.get<any[]>(HomeService.EMPLOYEES_URL);
  }

  navigateToEmployee(employeeId: string) {
    this.router.navigate(['/employee/id', employeeId]);
  }
}
