import { Component } from '@angular/core';
import { OrdersService } from '../../Services/orders.service';
import { Orders } from '../../Model/Orders';
@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrl: './order.component.css'
})
export class OrderComponent {

  orders: Orders[] = [];

  constructor(
    private ordersService: OrdersService

  ) {
    this.getAllOrders();
  }

  getAllOrders() {
    this.ordersService.getAllOrders().subscribe(
      {
        next: (response: any) => {
          console.log(response);
          this.orders = response;
          
        },
        error: (error: any) => {
          console.log(error);
        },
        complete: () => {
          console.log('Completed');
        }
      }
    );
  }

}
