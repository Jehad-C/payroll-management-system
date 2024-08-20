import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { HomeService } from '../../services/home.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  id: string = '';
  employees: any[] = [];

  constructor(private homeService: HomeService) { }

  ngOnInit(): void {
    this.homeService.getEmployees().subscribe({
      next: (data) => {
        this.employees = data;
      },
      error: (error) => {
        console.log('Get employees failed', error);
      }
    });
  }

  selectEmployee(id: string) {
    this.id = id;
  }

  viewEmployee(employeeId: string) {
    this.homeService.navigateToEmployee(employeeId);
  }
}
