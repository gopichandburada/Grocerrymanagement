import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators, AbstractControl, ValidationErrors, ValidatorFn, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Customer } from '../Customer';
import { CustomerService } from '../CustomerService';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-create-cust',
  standalone: true, 
  imports: [CommonModule, ReactiveFormsModule, RouterModule,FormsModule], 
  templateUrl: './create-cust.html',
  styleUrls: ['./create-cust.css'],
})
export class CreateCustComponent {
  msg: string = '';
  cust: Customer = new Customer();

  // Validator to check password and confirmPassword match
  passwordMatchValidator: ValidatorFn = (group: AbstractControl): ValidationErrors | null => {
    const password = group.get('password')?.value;
    const confirmPassword = group.get('confirmPassword')?.value;
    return password && confirmPassword && password !== confirmPassword
      ? { passwordMismatch: true }
      : null;
  };

  myForm = new FormGroup(
    {
      customerid: new FormControl(0, { nonNullable: true }),
      customername: new FormControl('', {
        nonNullable: true,
        validators: [
          Validators.required,
          Validators.pattern(/^[A-Za-z ]+$/),
          Validators.minLength(3),
          Validators.maxLength(30),
        ],
      }),
      email: new FormControl('', { nonNullable: true, validators: [Validators.required, Validators.email] }),
      password: new FormControl('', {
        nonNullable: true,
        validators: [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(20),
          Validators.pattern(/^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&])[A-Za-z0-9!@#$%^&*]+$/),
        ],
      }),
      confirmPassword: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
      address: new FormControl('', { nonNullable: true, validators: [Validators.required, Validators.maxLength(50)] }),
      mobile: new FormControl(0, { nonNullable: true, validators: [Validators.required, Validators.pattern(/^[0-9]{10}$/)] }),
    },
    { validators: this.passwordMatchValidator }
  );

  constructor(public custService: CustomerService, public route: Router) {}

  createCust() {
    if (this.myForm.invalid) return;

    let formValue = { ...this.myForm.value };
    delete formValue.confirmPassword;

    this.cust = formValue;
    console.log(this.cust);

    this.custService.createCustomer(this.cust).subscribe({
      next: (data) => {
        this.msg = 'Customer added successfully';
        alert('Registration Successful!!!');
        this.route.navigate(['/login']);
      },
      error: (err) => {
        if (err.status === 409) {
          this.msg = 'Email already exists. Please use a different one.';
          this.myForm.reset();
        } else {
          this.msg = 'Customer not added. Try again later.';
          alert(this.msg);
        }
      },
    });
  }

  goToLog() {
    this.route.navigate(['/login']);
  }
}
