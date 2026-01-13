import { CartItem } from "./CartItem";
import { Customer } from "./Customer";

export class Cart{
    cartid?:number
    customer?: Customer
    items?: CartItem[]
    flag?: number
    constructor(){
        this.cartid = 0;
        this.customer = undefined;
        this.items = [];
        this.flag = 0;
    }
}
