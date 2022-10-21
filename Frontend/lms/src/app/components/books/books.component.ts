import { Component, OnInit } from '@angular/core';
import { BookService } from 'src/app/services/book.service';
import { getCookie } from 'typescript-cookie';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {
  errMsg = '';
  public books = new Array();
  isUserLoggedIn = getCookie("isLoggedIn");
  constructor(private _bookService: BookService) { }

  ngOnInit(): void {
    this._bookService.getBooks()
    .subscribe(data => this.books = data,
      error=> {this.errMsg = 'Please login!',
      window.location.replace('/login')}
      );
  }

  deleteBook(isbn : number): void{
    this._bookService.deleteBook(isbn)
    .subscribe(data => this.deleteBook = data);
    window.location.replace("/books");
  }

}
