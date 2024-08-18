import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { EmployeeListService } from '../../services/employee-list.service';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css'
})
export class EmployeeListComponent {
  employees: any[] = [];

  constructor(private employeeListService: EmployeeListService) {}

  ngOnInit() {
    this.employeeListService.getEmployees().subscribe(
      response => {
        this.employees = response;
      },
      error => {
        console.error('Get employees failed', error);
        
      }
    );
  }

  selectEmployee(employeeId: number) {
    console.log(employeeId);
  }
}
