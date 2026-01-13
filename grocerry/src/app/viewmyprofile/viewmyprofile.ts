import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { Customer } from '../Customer';
import { CustomerService } from '../CustomerService';

@Component({
  selector: 'app-viewmyprofile',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './viewmyprofile.html',
  styleUrls: ['./viewmyprofile.css'], // ⚠ corrected plural
})
export class ViewmyprofileComponent {

  private custservice = inject(CustomerService);
  private route = inject(Router);

  customer: Customer = new Customer();
  customerid1 = sessionStorage.getItem('customerid');

  ngOnInit() {
    this.YourProfile(this.customerid1);
  }

  YourProfile(customerid: any) {
    this.custservice.YourProfile(customerid).subscribe(data => {
      this.customer = data;
      console.log("Your Profile Data:", this.customer);
    });
  }
}
