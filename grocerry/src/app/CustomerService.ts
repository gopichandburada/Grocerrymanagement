import { HttpClient } from "@angular/common/http";
import { Injectable, Optional } from "@angular/core";
import { Customer } from "./Customer";
import { Observable } from "rxjs";

@Injectable({
    providedIn : 'root'
})

export class CustomerService{
    constructor(public httpclient : HttpClient){}

    listCustomer() : Observable<Customer[]>{
        return this.httpclient.get<Customer[]>("http://localhost:8088/customer/ViewAllCustomers");
    }

    createCustomer(c : Customer ) : Observable<object>{
        return this.httpclient.post<any>("http://localhost:8088/customer/AddCustomer", c);
    }

    loginCustomer(c : Customer) : Observable<number>{
        return this.httpclient.post<number>("http://localhost:8088/customer/LoginCustomer", c);
    }
    
    ViewCustomerCart(customerid : number) :  Observable<Customer> {
        return this.httpclient.get<Customer>(`http://localhost:8088/customer/ViewCart?customerid=${customerid}`);
    }

    Payment(customerid : any){
        return this.httpclient.post<any>(`http://localhost:8088/customer/Payment?customerid=${customerid}`,{})
    }

    YourProfile(customerid : any){
        return this.httpclient.get<any>(`http://localhost:8088/customer/YourProfile?customerid=${customerid}`)
    }

    UpdateProfile(c : Customer, customerid : any) : Observable<object>{
        return this.httpclient.put<any>(`http://localhost:8088/customer/Updateprofile?customerid=${customerid}`, c);
    }
    resetPassword (pass : string, cpass : string, loginemail : any) : Observable<number>{
        return this.httpclient.post<number>(`http://localhost:8088/customer/ResetPassword?pass=${pass}&cpass=${cpass}&loginemail=${loginemail}`,{})
    }

}
