import { Component, OnInit } from '@angular/core';
import { Login } from 'src/app/models/login.model';
import { LoginService } from 'src/app/services/login.service';
import { setCookie } from 'typescript-cookie';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  jwttoken= "";
  submitted = false;
  loginModel = new Login('','');
  errMsg= '';
  constructor(private _loginService: LoginService) { }

  ngOnInit(): void {
  }


  login(userForm: any){
    this._loginService.authenticate(userForm.value)
    .subscribe(
      (res) => {
        console.log(res);
        this.jwttoken=res;
        this.submitted = true;
        console.log(JSON.stringify(this.jwttoken).substring(8,JSON.stringify(this.jwttoken).length - 2));
        setCookie("jwtToken",JSON.stringify(this.jwttoken).substring(8,JSON.stringify(this.jwttoken).length - 2));
        setCookie("isLoggedIn",true)
        window.location.replace('/');
      },
      error=> this.errMsg = 'Bad Credentials! Please try again'
    )
  }
}
