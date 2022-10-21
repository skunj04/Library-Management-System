import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { User } from '../models/user.model';
import { LoginService } from './login.service';
import { getCookie } from 'typescript-cookie';

@Injectable({
  providedIn: 'root'
})
export class UserService {
 
  uri = "http://localhost:8100/user-service/user";
  httpHeader = new HttpHeaders({
    'Authorization' : 'Bearer ' + getCookie("jwtToken"),
  });
  constructor(private http: HttpClient,private _loginService: LoginService) { }
  
  getUsers(): Observable<User[]>{
 
    return this.http.get<User[]>(this.uri + "/getAll",{headers: this.httpHeader})
    .pipe(catchError(this.errorHandler));
  }
  errorHandler(error: HttpResponse<any>){
    return throwError(error);
  }

  create(data: any): Observable<any> {
    return this.http.post(this.uri + "/create", data,{headers: this.httpHeader}).pipe(catchError(this.errorHandler));

  }

  getUser(email: string): Observable<User>{
    return this.http.get<User>(this.uri + "/get/" + email,{headers: this.httpHeader}).pipe(catchError(this.errorHandler));

  }

  updateUser(data: any): Observable<any>{
    return this.http.put(this.uri + "/update",data,{headers: this.httpHeader}).pipe(catchError(this.errorHandler));
    ;
  }

  deleteUser(userEmail: string): Observable<any> {
    return this.http.delete<any>( this.uri + "/delete/" + userEmail,{headers: this.httpHeader}).pipe(catchError(this.errorHandler));
  }
}