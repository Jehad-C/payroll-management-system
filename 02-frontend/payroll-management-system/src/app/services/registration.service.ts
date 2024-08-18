import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  static readonly BASE_URL = 'http://localhost:8080/api';
  static readonly REGISTRATION_URL = `${RegistrationService.BASE_URL}/registration`;

  constructor(private httpClient: HttpClient) { }

  registration(credentials: {username: string, password: string}): Observable<any> {
    return this.httpClient.post(RegistrationService.REGISTRATION_URL, credentials);
  }
}
