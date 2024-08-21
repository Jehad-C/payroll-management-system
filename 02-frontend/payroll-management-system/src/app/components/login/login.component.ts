import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private loginService: LoginService) { }

  onSubmit(loginForm: NgForm) {
    const credentials = {
      username: this.username,
      password: this.password
    }

    this.loginService.login(credentials).subscribe({
      next: (data) => {
        console.log('Login successfully', data);
      },
      error: (error) => {
        console.log('Login failed', error);
      }
    });
  }
}
