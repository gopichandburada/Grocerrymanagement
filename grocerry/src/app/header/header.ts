import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,       // ✅ correct for Angular 21
 imports: [CommonModule, RouterModule],
         // ✅ can include CommonModule, RouterModule if needed
  templateUrl: './header.html',
  styleUrls: ['./header.css'], // ⚠️ must be **styleUrls**, not styleUrl
})
export class HeaderComponent { }
