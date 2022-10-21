import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/models/book.model';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

  bookModel = new Book(1234567890123,'','','','',0)
  submitted=false
  errMsg = ''
  constructor(private _bookService: BookService) { }

  ngOnInit(): void {
  }

  addBook(userForm: any){
    // alert('Book added');
    this._bookService.create(userForm.value)
    .subscribe({
      next:(res) => {
        console.log(res);
        this.submitted = true;
      },
      error: (e) => {
        console.log(e);
        this.errMsg = 'Book Already exists'
      }
    })
  }
}
