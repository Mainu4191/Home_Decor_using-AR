import { Component } from '@angular/core';
import { Orders } from '../../Model/Orders';
import { OrdersService } from '../../Services/orders.service';
import { EnvService } from '../../../../env';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrl: './order-detail.component.css'
})
export class OrderDetailComponent {
  orders: any;
  orderItems: any = [];

  env = new EnvService();
  imageUrl: string = this.env.API_URL + '/files/';

  constructor(private ordersService: OrdersService, private route: ActivatedRoute) {
    this.route.params.subscribe(params => {
      this.getOrders(params['id']);
    });
  }

  getOrders(id: any) {
    this.ordersService.getOrderById(id).subscribe({
      next: (response: any) => {
        this.orders = response;
        this.orderItems = this.orders.orderDetails;
        console.log(this.orders);
      },
      error: (err: any) => {
        console.log(err);
      }

    });
  }

  updateStatus(orderCurrent: Orders, status: any) {

    orderCurrent.status = status;





    this.ordersService.updateOrderStatus(orderCurrent).subscribe({
      next: (response: any) => {
        console.log(response);
      },
      error: (err: any) => {
        console.log(err);
      }
    });
  }
}
