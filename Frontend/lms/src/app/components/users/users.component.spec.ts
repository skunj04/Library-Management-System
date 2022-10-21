import { ComponentFixture, TestBed } from '@angular/core/testing';
import { from } from 'rxjs';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import {HttpClientModule} from '@angular/common/http';

import { UsersComponent } from './users.component';

describe('UsersComponent', () => {
  let component: UsersComponent;
  let userService: UserService;
  let fixture: ComponentFixture<UsersComponent>;

  beforeEach(() =>{ TestBed.configureTestingModule({
    imports: [HttpClientTestingModule, HttpClientModule], 
    providers: [UserService]
  })
  fixture = TestBed.createComponent(UsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

 it('should set users array with users',() => {
  const users : User[] = [
    {
      email: 'skunj04@gmail.com',
      name: 'Kunj',
      gender : 'male',
      dob: '04-02-2001',
      address: 'Gujarat',
      type: 'Admin',
      password: '',
      contact: 8980462225,
      active: true
    },
    {
      email: 'skunj04@gmail1.com',
      name: 'Kunj',
      gender : 'male',
      dob: '04-02-2001',
      address: 'Gujarat',
      type: 'Admin',
      password: '',
      contact: 8980462225,
      active: true
    },
    {
      email: 'gpa.166170307096@gmail.com',
      name: 'Kunj',
      gender : 'male',
      dob: '04-02-2001',
      address: 'Gujarat',
      type: 'Admin',
      password: '',
      contact: 8980462225,
      active: false
    }
  ];

  spyOn(UserService.prototype, 'getUsers').and.callFake(() => {
    return from([users]);
  });

  component.ngOnInit();
  // console.log(component.users)
  expect(component.users).toEqual(users);
}) 
});