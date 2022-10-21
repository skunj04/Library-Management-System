import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/models/book.model';
import { IssueBooks } from 'src/app/models/issuebooks.model';
import { User } from 'src/app/models/user.model';
import { IssuedbooksService } from 'src/app/services/issuedbooks.service';

@Component({
  selector: 'app-issue-books',
  templateUrl: './issue-books.component.html',
  styleUrls: ['./issue-books.component.css']
})
export class IssueBooksComponent implements OnInit {

  submitted = false;
  selectUserBooks: IssueBooks = new IssueBooks(new Array(), new Array());
  books: Book[]= new Array();
  users: User[]= new Array();
  errMsg = '';
  constructor(private _issueBookService: IssuedbooksService) { }

  ngOnInit(): void {
    this._issueBookService.selectUserBooks()
    .subscribe(data => {this.selectUserBooks = data,
    this.books = data['Books'],
  this.users = data['Users']}
  )}

  issueBook(userForm: any){
    // alert('Book added');
    const data = {"email":userForm.email, "isbn":userForm.isbn};
    this._issueBookService.issueBook(data)
    .subscribe(data => {this.users = data;
    this.submitted = true},
      error=> {this.errMsg = 'Please login!'
      window.location.replace('/login')
    });
  }

}
