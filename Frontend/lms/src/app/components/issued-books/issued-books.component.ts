import { Component, OnInit } from '@angular/core';
import { IssuedbooksService } from 'src/app/services/issuedbooks.service';

@Component({
  selector: 'app-issued-books',
  templateUrl: './issued-books.component.html',
  styleUrls: ['./issued-books.component.css']
})
export class IssuedBooksComponent implements OnInit {

  public issuedBooks = new Array();
  constructor(private _issuedBookService: IssuedbooksService) { }
  errMsg = '';

  ngOnInit(): void {
    this._issuedBookService.getIssuedBooks()
    .subscribe(data => this.issuedBooks = data,
      error=> {this.errMsg = 'Please login!'
      window.location.replace('/login')
    })
  }

  returnBook(id: string, email:string, isbn:number){
    this._issuedBookService.returnBook(id, email, isbn)
    .subscribe()
    window.location.replace("/issuebooks")
  }
}
