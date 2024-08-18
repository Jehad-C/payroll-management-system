import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { RegistrationService } from '../../services/registration.service';

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent {
  username: string = '';
  password: string = '';

  constructor(private registrationService: RegistrationService) { }

  onSubmit(registrationForm: NgForm) {
    const credentials = {
      username: this.username,
      password: this.password
    }

    this.registrationService.registration(credentials).subscribe(
      response => {
        console.log('Registration successfully', response);
      },
      error => {
        console.log('Registration failed', error);
      }
    );
  }
}
