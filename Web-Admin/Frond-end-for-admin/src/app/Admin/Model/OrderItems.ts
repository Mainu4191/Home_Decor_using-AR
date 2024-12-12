import { Orders } from './Orders';


export class OrderItems{





    constructor(
        public id: number,
        public item_id: number,
        public quantity: number,
        public price: number,
        public orders: Orders
    ) { }

    get getId() {
        return this.id;
    }

    set setId(id: number) {
        this.id = id;
    }


  

    get getItemId() {
        return this.item_id;
    }

    set setItemId(item_id: number) {
        this.item_id = item_id;
    }

    get getQuantity() {
        return this.quantity;
    }

    set setQuantity(quantity: number) {
        this.quantity = quantity;
    }

    get getPrice() {
        return this.price;
    }

    set setPrice(price: number) {
        this.price = price;
    }

    get getOrders() {
        return this.orders;
    }

    set setOrders(orders: Orders) {
        this.orders = orders;
    }

    public toString() {
        return "Orders [id=" + this.id + ", item_id=" + this.item_id + ", quantity=" + this.quantity + ", price=" + this.price + ", orders=" + this.orders + "]";
    }

}