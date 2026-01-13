import { Cart } from "./Cart";

export class Customer{
    customerid?: number;
    customername?: string;
    email?: string;
    password?: string;
    address?: string;
    mobile?: number;
    cart ?: Cart[];
    totalhistory?: Cart[];
    logincheck?: Date
    active?:number
    constructor(){
        this.customerid = 0;
        this.customername = '';
        this.email = '';
        this.password = '';
        this.address = '';
        this.mobile = 0;
        this.cart = [];
        this.totalhistory = [];
        this.logincheck = new Date
        this.active = 1;
    }

}