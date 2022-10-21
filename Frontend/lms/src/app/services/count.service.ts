import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CountService {

  constructor(private http: HttpClient) { }

  getUserCount(): Observable<number>{
    return this.http.get<number>("http://localhost:8100/user-service/user/count");
  }
  getBookCount(): Observable<number>{
    return this.http.get<number>("http://localhost:8100/book-service/book/count");
  }
  getIssuedBookCount(): Observable<number>{
    return this.http.get<number>("http://localhost:8100/issue-book-service/issue/count");
  }
}
