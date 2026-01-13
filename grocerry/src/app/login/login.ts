import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { Customer } from '../Customer';
import { CustomerService } from '../CustomerService';

@Component({
  selector: 'app-login',
  standalone: true, 
  imports: [CommonModule, ReactiveFormsModule, RouterModule], 
  templateUrl: './login.html',
  styleUrls: ['./login.css'],
})
export class LoginComponent {
  private route = inject(Router);
  private custService = inject(CustomerService);

  cust: Customer = new Customer();
  customerid: number = 0;
  isAdmin: number = 0;
  isCustomer: number = 0;

  loginForm = new FormGroup({
    email: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.email],
    }),
    password: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.maxLength(20)],
    }),
  });

  Login() {
  this.cust = this.loginForm.value;
  console.log(this.cust);

  this.custService.loginCustomer(this.cust).subscribe({
    next: (data) => {
      if (data == -1) {
        alert("Account has been Deactivated!!!");
        console.log("Account Deactivated!!");
        this.route.navigate(['/resetpassword', this.cust.email]);
      } else if (data == -2) {
        alert("Login Unsuccessful!!!");
        console.log("Unsuccessful Login");
      } else if (data == 1000) { // 🔐 Admin login hardcoded ID
        alert("Login Successful as Admin!!!");
        console.log("Admin ID:", data);
        this.route.navigate(['/home']); // admin home page
        sessionStorage.setItem('adminid', JSON.stringify(data));
        sessionStorage.setItem("isAdmin", "true");
        sessionStorage.setItem("isCustomer", "false");
      } else {
        alert("Login Successful as Customer!!!");
        this.customerid = data;
        console.log("Customer ID:", data);
        this.route.navigate(['/viewcustomerproduct']); // customer page
        sessionStorage.setItem('customerid', JSON.stringify(this.customerid));
        sessionStorage.setItem("isAdmin", "false");
        sessionStorage.setItem("isCustomer", "true");
      }
    },
  });
}


  goToRegister() {
    this.route.navigate(['/addcustomer']);
  }
}
