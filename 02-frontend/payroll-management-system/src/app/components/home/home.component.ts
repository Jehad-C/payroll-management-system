import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { HomeService } from '../../services/home.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  id: string = '';
  employees: any[] = [];
  fromDate: string = '';
  toDate: string = '';

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

  onSelectEmployee(id: string) {
    this.id = id;
  }

  onGeneratePayroll() {
    this.homeService.navigateToPayroll(this.id, this.fromDate, this.toDate);
  }

  onViewEmployee() {
    this.homeService.navigateToEmployee(this.id);
  }
}
