import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PayrollService {
  static readonly BASE_URL = 'http://localhost:8080/api';
  static readonly PAYROLL_BY_ID_URL = `${PayrollService.BASE_URL}/payroll/id/{id}`;

  constructor(private httpClient: HttpClient) { }

  generatePayrollById(employeeId: string, fromDate: string, toDate: string): Observable<any> {
    const url = PayrollService.PAYROLL_BY_ID_URL.replace('{id}', employeeId);
    const params = new HttpParams()
      .set('fromDate', fromDate)
      .set('toDate', toDate);

    const startEndDate = {
      startDate: fromDate,
      endDate: toDate
    };

      return this.httpClient.post(url, startEndDate);
  }
}
