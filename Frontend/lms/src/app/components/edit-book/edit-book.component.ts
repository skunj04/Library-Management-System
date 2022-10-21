import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Book } from 'src/app/models/book.model';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.css']
})
export class EditBookComponent implements OnInit {

  private routeSub: Subscription = new Subscription();
  public bookModel = new Book(0,'','','','',0);
  submitted=false;
  constructor(private route: ActivatedRoute, private _bookService: BookService) { }
  ngOnInit(): void {
    this.routeSub = this.route.params.subscribe(params => {
      this._bookService.getBook(Number(params['ISBN']))
      .subscribe(data => this.bookModel = data)
      // console.log(params['id']) 
    });
  }

  editBook(userForm: any){
    // alert('User added');
    // console.log(userForm.value);
    this._bookService.updateBook(userForm.value)
    .subscribe({
      next:(res) => {
        console.log(res);
        this.submitted = true;
      },
      error: (e:any) => console.log(e)
   })
  }

}
