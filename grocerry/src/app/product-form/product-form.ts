import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { ProductService } from '../ProductService';
import { Product } from '../Product';

import * as XLSX from 'xlsx';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './product-form.html',
  styleUrls: ['./product-form.css'],
})
export class ProductFormComponent {

  private productservice = inject(ProductService);
  private route = inject(Router);

  categories: string[] = ['Fruits','Frozen Food','Chocolate', 'Biscuits', 'Vegetables', 'Snack', 'Sweets','Beverage','Essentials', 'Oils and Sauces'];

  product: Product = new Product();
  prodarr: Product[] = [];
  file: File | null = null;

  ProductForm = new FormGroup({
    productid: new FormControl(0, { nonNullable: true }),
    productname: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(3), Validators.pattern(/^[A-Za-z ]+$/)]
    }),
    category: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(3), Validators.pattern(/^[A-Za-z]+$/)]
    }),
    quantity: new FormControl(0, {
      nonNullable: true,
      validators: [Validators.required, Validators.min(1), Validators.pattern(/^[0-9]+$/), Validators.max(100)]
    }),
    price: new FormControl(0, {
      nonNullable: true,
      validators: [Validators.required, Validators.min(1), Validators.pattern(/^[0-9]+$/), Validators.max(100000)]
    }),
    url: new FormControl('', {
      nonNullable: true,
      validators: [Validators.required]
    })
  });

  // --- Excel Parsing ---
  onFileChange(event: any) {
    this.file = event.target.files[0];
    if (!this.file) return;

    const reader = new FileReader();
    reader.onload = (e: any) => {
      const data = new Uint8Array(e.target.result);
      const workbook = XLSX.read(data, { type: 'array' });
      const sheetName = workbook.SheetNames[0];
      const worksheet = workbook.Sheets[sheetName];
      this.prodarr = XLSX.utils.sheet_to_json(worksheet);
      console.log('Excel parsed:', this.prodarr);
    };
    reader.readAsArrayBuffer(this.file);
  }

  uploadBulk() {
    if (!this.prodarr.length) {
      alert('No products parsed from Excel!');
      return;
    }

    this.prodarr.forEach(prod => {
      const payload: any = {
        productname: prod['productname'],
        category: prod['category'],
        quantity: prod['quantity'],
        price: prod['price'],
        url: prod['url']
      };
      this.productservice.AddProducts(payload).subscribe();
    });

    alert('Bulk upload started.');
  }

  createProduct() {
    this.product = this.ProductForm.value;
    this.productservice.AddProducts(this.product).subscribe(data => {
      alert("Product successfully added!!");
      console.log("PRODUCT ADDED!!!");
      this.route.navigate(['/productHome']);
    });
  }
}
