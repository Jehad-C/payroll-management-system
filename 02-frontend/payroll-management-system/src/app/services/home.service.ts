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

  navigateToPayroll(employeeId: string, fromDate: string, toDate: string) {
    const isoFromDate = new Date(fromDate).toISOString();
    const isoToDate = new Date(toDate).toISOString();
    const queryParams = {
      fromDate: isoFromDate,
      toDate: isoToDate,
    };

    this.router.navigate(['/payroll/id', employeeId], { queryParams });
  }

  navigateToEmployee(employeeId: string) {
    this.router.navigate(['/employee/id', employeeId]);
  }
}
