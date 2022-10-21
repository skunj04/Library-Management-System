import { ComponentFixture, TestBed } from '@angular/core/testing';
import { from } from 'rxjs';
import { Book } from 'src/app/models/book.model';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import {HttpClientModule} from '@angular/common/http';

import { BooksComponent } from './books.component';
import { BookService } from 'src/app/services/book.service';

describe('BooksComponent', () => {
  let component: BooksComponent;
  let bookService: BookService;
  let fixture: ComponentFixture<BooksComponent>;
  let mockPostService:any;
  let books : Book[]
    beforeEach(() =>{     
        books = [
        {
            isbn: 1234567890123,
            name: 'Experiments',
            description: 'Expermients book',
            authorName: 'M K Gandhi',
            category: 'biography',
            quantity: 10
        },
        {
            isbn: 1234567890124,
            name: 'Experiments',
            description: 'Expermients book',
            authorName: 'M K Gandhi',
            category: 'biography',
            quantity: 10
        },
        {
            isbn: 1234567890125,
            name: 'Experiments',
            description: 'Expermients book',
            authorName: 'M K Gandhi',
            category: 'biography',
            quantity: 10
        }
    ];
    const mockPostService = jasmine.createSpyObj(['getBooks','deleteBook']);
    component = new BooksComponent(mockPostService);

     TestBed.configureTestingModule({
        imports: [HttpClientTestingModule, HttpClientModule], 
        providers: [BookService]
    })
    fixture = TestBed.createComponent(BooksComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    
    it('should set books array with books',() => {
    spyOn(BookService.prototype, 'getBooks').and.callFake(() => {
        return from([books]);
    });

    component.ngOnInit();
    expect(component.books).toEqual(books);
    }) 

});