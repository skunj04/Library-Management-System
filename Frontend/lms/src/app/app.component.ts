import { Component, OnInit } from '@angular/core';
import { LoginService } from './services/login.service';
import { getCookie } from 'typescript-cookie';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'lms';
  constructor(private _loginService: LoginService){}
  userLogin= (getCookie("isLoggedIn") == "true");
  ngOnInit(): void {
    // console.log(getCookie("isLoggedIn"));
    // console.log(getCookie("jwtToken"));
  }
}
