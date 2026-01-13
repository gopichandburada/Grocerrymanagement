import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { ProductService } from '../ProductService';
import { CartService } from '../CartService';
import { Product } from '../Product';
import { Cart } from '../Cart';
import { CartItem } from '../CartItem';
import { Customer } from '../Customer';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-view-product',
  standalone: true,
  imports: [CommonModule, RouterModule,FormsModule],
  templateUrl: './view-product.html',
  styleUrls: ['./view-product.css'], // ⚠ corrected plural
})
export class ViewProductComponent {

  private productservice = inject(ProductService);
  private cartservice = inject(CartService);
  private route = inject(Router);

  products: Product[] = [];
  cartItem: CartItem = new CartItem();
  cart: Cart | undefined;
  customer: Customer = new Customer();
  product: Product = new Product();
  categories: string[] = [];
  selectCategory: string = '';
  keyword: string = '';
  featuredProducts: Product[] = [];
  customerid: number = JSON.parse(sessionStorage.getItem('customerid') || '0');

  ngOnInit(): void {
    this.viewAllProducts();
    this.loadCategories();
  }

  viewAllProducts() {
    this.productservice.viewProducts().subscribe(data => {
      this.products = data;

      // Pick top 5 products as "Featured"
      this.featuredProducts = [...this.products]
        .filter(p => !p.delprod)
        .slice(0, 5);
    });
  }

  UpdateProduct(productid: any) {
    this.route.navigate(['/updateproduct', productid]);
  }

  DeleteProduct(productid: any) {
    if (confirm("Are you sure you want to delete the product?")) {
      this.productservice.DeleteProduct(productid).subscribe(data => {
        console.log("delete is working");
        window.location.reload();
      });
    }
  }

  loadCategories() {
    this.productservice.getCategories().subscribe(data => {
      this.categories = data;
    });
  }

  loadProducts(category: string) {
    this.selectCategory = category;
    this.productservice.getByCategory(category).subscribe(data => {
      this.products = data;
    });
  }

  search() {
    if (this.keyword.trim().length > 0 && this.selectCategory) {
      this.productservice.searchByCategory(this.selectCategory, this.keyword).subscribe(data => {
        this.products = data;
      });
    } else if (this.selectCategory) {
      this.loadProducts(this.selectCategory);
    } else if (this.keyword) {
      this.loadProductByName(this.keyword);
    }
  }

  loadProductByName(keyword: any) {
    this.productservice.searchByName(this.keyword).subscribe(data => {
      this.products = data;
    });
  }

  getFilteredOProducts(cat: string) {
    return this.products.filter(p => p.category === cat && !p.delprod);
  }
}
