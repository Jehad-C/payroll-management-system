import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  static readonly BASE_URL = 'http://localhost:8080/api';
  static readonly LOGIN_URL = `${LoginService.BASE_URL}/login`;

  constructor(private httpClient: HttpClient) { }

  login(credentials: {username: string, password: string}): Observable<any> {
    return this.httpClient.post(LoginService.LOGIN_URL, credentials);
  }
}
