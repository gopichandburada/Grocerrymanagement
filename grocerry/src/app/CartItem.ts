
import { Cart } from "./Cart";
import { Product } from "./Product";

export class CartItem{
    itemid?: number;
    product?: Product;
    cart?: Cart;
    itemquantity?: number
    constructor(){
        this.itemid = 0;
        this.cart = undefined;
        this.product = undefined;
        this.itemquantity = 0;
    }
}