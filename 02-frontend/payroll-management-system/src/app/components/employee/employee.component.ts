import { Component } from '@angular/core';
import { EmployeeService } from '../../services/employee.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './employee.component.html',
  styleUrl: './employee.component.css'
})
export class EmployeeComponent {
  employee: any = null;

  constructor(
    private employeeService: EmployeeService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    const id = this.activatedRoute.snapshot.paramMap.get('id');

    if (id) {
      return this.employeeService.fetchEmployeeById(id).subscribe({
        next: (data) => {
          this.employee = data;
        },
        error: (error) => {
          console.error('Get employee failed', error);
        }
      });
    }

    return;
  }
}
