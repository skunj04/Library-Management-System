import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  private routeSub: Subscription = new Subscription();
  public userModel = new User('','','','','','','',0,false);
  submitted=false;
  constructor(private route: ActivatedRoute, private _userService: UserService) { }
  ngOnInit(): void {
    this.routeSub = this.route.params.subscribe(params => {
      this._userService.getUser(params['id'])
      .subscribe(data => this.userModel = data)
      // console.log(params['id']) 
    });
  }

  editUser(userForm: any){
    // alert('User added');
    // console.log(userForm.value);
    this._userService.updateUser(userForm.value)
    .subscribe({
      next:(res) => {
        console.log(res);
        this.submitted = true;
      },
      error: (e:any) => console.log(e)
    })
  }
}
