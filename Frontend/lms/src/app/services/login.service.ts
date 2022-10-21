import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Login } from '../models/login.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public token="";
  public isLoggedIn = false;

  constructor(private http: HttpClient) { }
  
  authenticate(data: Login): Observable<string>{
    return this.http.post<string>("http://localhost:8100/authenticate",data)
    .pipe(catchError(this.errorHandler));
    
  }
  errorHandler(error: HttpResponse<any>){
    return throwError(error);
  }
  
  addToken(token:string){
    this.token = token;
  }

  setIsLoggedIn(flag: boolean){
    this.isLoggedIn = flag;
  }

}
