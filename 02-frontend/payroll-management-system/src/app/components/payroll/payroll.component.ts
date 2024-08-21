import { Component } from '@angular/core';
import { PayrollService } from '../../services/payroll.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-payroll',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './payroll.component.html',
  styleUrl: './payroll.component.css'
})
export class PayrollComponent {
  payroll: any = null;

  constructor(
    private payrollService: PayrollService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    const fromDate = this.activatedRoute.snapshot.queryParamMap.get('fromDate');
    const toDate = this.activatedRoute.snapshot.queryParamMap.get('toDate');

    if (id && fromDate && toDate) {
      return this.payrollService.generatePayrollById(id, fromDate, toDate).subscribe({
        next: (data) => {
          console.log(data);
          this.payroll = data;
        },
        error: (error) => {
          console.error('Get payroll failed', error);
          
        }
      });
    }

    return;
  }
}
