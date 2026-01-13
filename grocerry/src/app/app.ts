import { Component, inject } from '@angular/core';
import { Router, NavigationEnd, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header';

@Component({
  selector: 'app-root',
  standalone: true, // standalone component
  imports: [CommonModule, RouterModule,HeaderComponent ], // import modules used in template
  templateUrl: './app.html',
  styleUrls: ['./app.css'], // note plural 'styleUrls'
})
export class App {
  title = 'demoangular';

  // Using inject instead of constructor injection (optional in v21)
  private router = inject(Router);

  isAdmin: boolean = false;
  isCustomer: boolean = false;

  constructor() {
    // Subscribe to router events
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.checkSession();
      }
    });
  }

  ngOnInit() {
    this.checkSession();
  }

  checkSession() {
    this.isAdmin = JSON.parse(sessionStorage.getItem('isAdmin') || 'false');
    this.isCustomer = JSON.parse(sessionStorage.getItem('isCustomer') || 'false');
  }

  logout() {
    if (confirm('Are you sure you want to Logout?')) {
      sessionStorage.clear();
      this.checkSession();
      this.router.navigate(['/login']);
    } else {
      console.log('Logout Cancelled');
    }
  }
}
