import { Component, inject } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-landingpage',
  standalone: true, // ✅ standalone component
  imports: [CommonModule, RouterModule], // ✅ needed for routerLink, *ngIf, *ngFor
  templateUrl: './landingpage.html',
  styleUrls: ['./landingpage.css'], // ⚠ corrected plural
})
export class LandingpageComponent {
  // Using Angular 21 inject() instead of constructor injection
  private router = inject(Router);

  goToLogin() {
    this.router.navigate(['/login']);
  }
}
