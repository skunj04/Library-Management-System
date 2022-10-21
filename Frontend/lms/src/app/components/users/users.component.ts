import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  public users = new Array();
  public deletedUser: User | undefined;
  errMsg = '';
  constructor(private _userService: UserService) { }
  ngOnInit(): void {
    this._userService.getUsers()
    .subscribe(data => this.users = data,
      error=> {this.errMsg = 'Please login!'
      window.location.replace('/login')
    });
  }


  
  deleteUser(userEmail : string): void{
    this._userService.deleteUser(userEmail)
    .subscribe(data => this.deleteUser = data);
    window.location.replace("/users");
  }
}
