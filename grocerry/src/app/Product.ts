
export class Product{
    productid?: number;
    productname?: string;
    category?: string;
    quantity?: number;
    price?: number;
    rating?:number;
    delprod? : number
    url? : string
    constructor(){
        this.productid = 0;
        this.productname = '';
        this.category = '';
        this.quantity = 0;
        this.price = 0;
        this.rating  = 0;
        this.delprod = 0;
        this.url = ''
    }
}