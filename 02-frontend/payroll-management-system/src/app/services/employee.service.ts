import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  static readonly BASE_URL = 'http://localhost:8080/api';
  static readonly EMPLOYEE_URL = `${EmployeeService.BASE_URL}/employee/id/{id}`;

  constructor(private httpClient: HttpClient) { }

  fetchEmployeeById(employeeId: string): Observable<any> {
    const url = EmployeeService.EMPLOYEE_URL.replace('{id}', employeeId);
    return this.httpClient.get(url);
  }
}
