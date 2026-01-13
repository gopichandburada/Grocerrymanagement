import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Cart } from "./Cart";
import { Observable } from "rxjs";

@Injectable({
    providedIn : 'root'
})

export class CartService{

    constructor(public httpclient : HttpClient){}


    AddToCartCustomer(customerid : number, productid : number, itemquantity : number) : Observable<object>{
    
        return this.httpclient.post<any>(`http://localhost:8088/cart/AddItems?customerid=${customerid}&productid=${productid}&itemquantity=${itemquantity}`, {});
    }

    IncrementTheItem(productid : number, customerid : any) : Observable<object>{
        return this.httpclient.post<any>(`http://localhost:8088/cart/IncrementItem?productid=${productid}&customerid=${customerid}`,{})
    }
    DecrementTheItem(productid : number, customerid : any) : Observable<object>{
        return this.httpclient.put<any>(`http://localhost:8088/cart/DecrementItem?productid=${productid}&customerid=${customerid}`,{})
    }
    
    ViewCartHistory(customerid : any) : Observable<object>{
        return this.httpclient.get<any>(`http://localhost:8088/cart/ViewHistory?customerid=${customerid}`)
    }
}
