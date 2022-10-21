import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { removeCookie, setCookie } from 'typescript-cookie';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private _loginService: LoginService) { }

  ngOnInit(): void {
    setCookie("isLoggedIn",false);
    removeCookie("jwtToken");
    window.location.replace('/login');
  }

}
