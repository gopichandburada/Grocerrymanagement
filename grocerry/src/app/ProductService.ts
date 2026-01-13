
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Product } from "./Product";

@Injectable({
    providedIn : 'root'
})

export class ProductService{
    constructor(public httpclient : HttpClient){}

    viewProducts() : Observable<Product[]>{
        return this.httpclient.get<Product[]>("http://localhost:8088/product/ViewAllProducts")
    }

    AddProducts(prod : Product) : Observable<object>{
        return this.httpclient.post<any>("http://localhost:8088/product/AddProduct", prod);
    }

    UpdateProduct(productid : any,product :Product) : Observable<object> {
        return this.httpclient.post<any>(`http://localhost:8088/product/Updateproduct?productid=${productid}`, product)
    }
    ViewSpecificProduct(productid : any) : Observable<Product>{
         return this.httpclient.get<Product>(`http://localhost:8088/product/ViewSpecificProduct?productid=${productid}`)
    }

    DeleteProduct(productid : any) : Observable<number>{
        return this.httpclient.post<any>(`http://localhost:8088/product/DeleteProduct?productid=${productid}`,{})
    }
    getCategories(){
        return this.httpclient.get<string[]>("http://localhost:8088/product/categories")
    }
    getByCategory(category : string) : Observable<Product[]>{
        return this.httpclient.get<Product[]>(`http://localhost:8088/product/getCategory?category=${category}`)
    }
    searchByCategory(category : string, keyword : string) : Observable<Product[]>{
        return this.httpclient.get<Product[]>(`http://localhost:8088/product/searchByCategory?category=${category}&keyword=${keyword}`)
    }
    searchByName(keyword:string) : Observable<Product[]>{
        return this.httpclient.get<Product[]>(`http://localhost:8088/product/searchByName?keyword=${keyword}`)
    }
    ChangeDelProd (productid : number){
        return this.httpclient.post<any>(`http://localhost:8088/product/ActivateProduct?productid=${productid}`,{})
    }
}
