import { Component, OnInit } from '@angular/core';
import { Customer } from '../Customer';
import { CustomerService } from '../CustomerService';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  standalone: true, // ✅ standalone component
  imports: [CommonModule,FormsModule], // ✅ needed for *ngIf, *ngFor in template
  templateUrl: './home.html',
  styleUrls: ['./home.css']
})
export class HomeComponent implements OnInit {
  custarr: Customer[] = [];
  filteredCustomers: Customer[] = [];
  selectStatus: string = "";
  searchCustName: string = "";

  constructor(public service: CustomerService) {}

  ngOnInit(): void {
    this.CustomerList();
  }

  CustomerList() {
    this.service.listCustomer().subscribe(data => {
      this.custarr = data;
      this.filteredCustomers = this.custarr.slice(0, 8); // default load
    });
  }

  loadCustomers(selectStatus: string) {
    this.selectStatus = selectStatus;

    if (selectStatus === "Active") {
      this.filteredCustomers = this.custarr.filter(c => c.active === 1).slice(0, 8);
    } else if (selectStatus === "Deactive") {
      this.filteredCustomers = this.custarr.filter(c => c.active === 0).slice(0, 8);
    } else {
      this.filteredCustomers = this.custarr;
    }
  }

  searchName() {
    const searchword = this.searchCustName.toLowerCase();
    this.filteredCustomers = this.custarr.filter(c => c.customername?.toLowerCase().includes(searchword));
  }
}
