import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import {User} from '../../models/user.model';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  userModel: User = new User('','','male','','','','',9999999999,false);
  submitted=false;
  errMsg = ''

  constructor(private _userService: UserService) { 
  }

  ngOnInit(): void {
  }

  addUser(userForm: any){
    // alert('User added');
    console.log(userForm.value);
    this._userService.create(userForm.value)
    .subscribe({
      next:(res) => {
        console.log(res);
        this.submitted = true;
      },
      error: (e) => {
        console.log(e);
        this.errMsg = 'User Email Already exists'
      }
    })
  }
}
