import { Component } from '@angular/core';
import { UsersService } from '../../Services/users.service';

@Component({
  selector: 'app-userslist',
  templateUrl: './userslist.component.html',
  styleUrl: './userslist.component.css'
})
export class UserslistComponent {
  users: any = [];

  constructor(
    private usersService: UsersService

  ) {
    this.getAllUsers();
  }


  getAllUsers() {
    this.usersService.getAllUsers().subscribe((res: any) => {
      this.users = res;
    });
  }


  onDeleteUser(user: any) {
    console.log(user);
    this.usersService.deleteUser(user).subscribe(
      {
        next: (res: any) => {
          console.log(res);

          if (res[0].status) {
            alert(res[0].message);
          }
          else {
            alert(res[0].message);
          }


          this.getAllUsers();
        },
        error: (res: any) => {
          console.log(res);
        }
      }
    );
  }

}
