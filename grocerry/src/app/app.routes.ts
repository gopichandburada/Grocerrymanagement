import { Routes } from '@angular/router';
import { CreateCustComponent } from './create-cust/create-cust';
import { HomeComponent } from './home/home';
import { HeaderComponent } from './header/header';
import { LoginComponent } from './login/login';
import { ProductFormComponent } from './product-form/product-form';
import { ViewProductComponent } from './view-product/view-product';
import { LandingpageComponent } from './landingpage/landingpage';


export const routes: Routes = [
  { path: '', component: LandingpageComponent },
  { path: 'addcustomer', component: CreateCustComponent },
  { path: 'home', component: HomeComponent },
  { path: 'header', component: HeaderComponent },
  { path: 'login', component: LoginComponent },
  { path: 'addproducts', component: ProductFormComponent },
  { path: 'productHome', component: ViewProductComponent },

];
