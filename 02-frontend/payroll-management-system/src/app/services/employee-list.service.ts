import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeListService {
  static readonly BASE_URL = 'http://localhost:8080/api';
  static readonly EMPLOYEES = `${EmployeeListService.BASE_URL}/employees`;

  constructor(private httpClient: HttpClient) { }

  getEmployees(): Observable<any[]> {
    return this.httpClient.get<any[]>(EmployeeListService.EMPLOYEES);
  }
}
