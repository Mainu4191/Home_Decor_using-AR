import { Component } from '@angular/core';
import { DashboardService } from '../../Services/dashboard.service';
import { DashboardModel } from '../../Model/DashboardModel';
@Component({
  selector: 'app-dasboard-page',
  templateUrl: './dasboard-page.component.html',
  styleUrl: './dasboard-page.component.css'
})
export class DasboardPageComponent {

  constructor(private dashboardService: DashboardService) { }

  ngOnInit() {
    this.dashboardService.getDashboardData().subscribe((data) => {
      console.log(data);
      this.Data = data;
    });
  }

  Data: DashboardModel = new DashboardModel(0, 0, 0, 0, 0, 0, 0, 0);
}
