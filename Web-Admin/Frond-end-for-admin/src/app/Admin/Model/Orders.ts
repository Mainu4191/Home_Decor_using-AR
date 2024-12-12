import { OrderItems } from './OrderItems';

export class Orders{


    constructor(
        public order_id: number,
        public user_id: number,
        public order_date: string,
        public status: string,
        public total: number,
        public orderItems: OrderItems[],
        public user_name: string
    ) { }

    get getOrderId() {
        return this.order_id;
    }

    set setOrderId(order_id: number) {
        this.order_id = order_id;
    }

    get getUserId() {
        return this.user_id;
    }

    set setUserId(user_id: number) {
        this.user_id = user_id;
    }

    get getOrderDate() {
        return this.order_date;
    }

    set setOrderDate(order_date: string) {
        this.order_date = order_date;
    }

    get getStatus() {
        return this.status;
    }

    set setStatus(status: string) {
        this.status = status;
    }

    get getTotal() {
        return this.total;
    }

    set setTotal(total: number) {
        this.total = total;
    }

    get getOrderItems() {
        return this.orderItems;
    }

    set setOrderItems(orderItems: OrderItems[]) {
        this.orderItems = orderItems;
    }


    


}