import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { getCookie } from 'typescript-cookie';
import { Book } from '../models/book.model';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  uri = "http://localhost:8100/book-service/book";
  httpHeader = new HttpHeaders({
    'Authorization' : 'Bearer ' + getCookie("jwtToken"),
  });
  constructor(private http: HttpClient,private _loginService: LoginService) { }
  getBooks(): Observable<Book[]>{
    return this.http.get<Book[]>(this.uri + "/getAll")
    .pipe(catchError(this.errorHandler));
  }
  errorHandler(error: HttpResponse<any>){
    return throwError(error);
  }

  create(data: any): Observable<any> {
    return this.http.post(this.uri + "/create", data,{headers: this.httpHeader}).pipe(catchError(this.errorHandler));
  }


  getBook(ISBN: number): Observable<Book>{
    return this.http.get<Book>(this.uri + "/get/" + ISBN,{headers: this.httpHeader})
    .pipe(catchError(this.errorHandler));
  }

  updateBook(data: any): Observable<any>{
    return this.http.put(this.uri + "/update",data,{headers: this.httpHeader})
    .pipe(catchError(this.errorHandler));
  }

  deleteBook(isbn: number): Observable<any> {
    return this.http.delete<any>( this.uri + "/delete/" + isbn,{headers: this.httpHeader}).pipe(catchError(this.errorHandler));
  }
}
