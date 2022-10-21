import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { getCookie } from 'typescript-cookie';
import { IssueBooks } from '../models/issuebooks.model';
import { IssuedBooks } from '../models/issuedbooks.model';

@Injectable({
  providedIn: 'root'
})
export class IssuedbooksService {

  uri = "http://localhost:8100/issue-book-service/issue";
  httpHeader = new HttpHeaders({
    'Authorization' : 'Bearer ' + getCookie("jwtToken"),
  });
  constructor(private http: HttpClient) { }

  getIssuedBooks(): Observable<IssuedBooks[]>{
    return this.http.get<IssuedBooks[]>(this.uri + "/getAll",{headers: this.httpHeader}).pipe(catchError(this.errorHandler));
  }
  errorHandler(error: HttpResponse<any>){
    return throwError(error);
  }
  

  selectUserBooks(): Observable<IssueBooks>{
    return this.http.get<IssueBooks>(this.uri + '/select',{headers: this.httpHeader}).pipe(catchError(this.errorHandler));
  }

  issueBook(data: any): Observable<any>{
    return this.http.post<any>(this.uri + '/issue',data,{headers: this.httpHeader}).pipe(catchError(this.errorHandler));
  }

  returnBook(id: string, email: string, isbn: number){
    const data = {"id":id,"email" : email,"isbn" : isbn}
    return this.http.post<any>(this.uri + '/return', data,{headers: this.httpHeader}).pipe(catchError(this.errorHandler));
  }
}
