import { ComponentFixture, TestBed } from '@angular/core/testing';
import { from } from 'rxjs';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import {HttpClientModule} from '@angular/common/http';
import { IssuedBooksComponent } from './issued-books.component';
import { IssuedBooks } from 'src/app/models/issuedbooks.model';
import { IssuedbooksService } from 'src/app/services/issuedbooks.service';


describe('IssueBooks Components', () => {
  let component: IssuedBooksComponent;
  let fixture: ComponentFixture<IssuedBooksComponent>;

  beforeEach(() =>{ TestBed.configureTestingModule({
    imports: [HttpClientTestingModule, HttpClientModule], 
    providers: [IssuedbooksService]
  })
  fixture = TestBed.createComponent(IssuedBooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

 it('should set IssuedBooks array with issuedBooks',() => {
  const issuedBooks : IssuedBooks[] = [
    {
        id:'1',
        email: 'skunj04@gmail.com',
        name: 'Kunj',
        bookname: 'Let Us C',
        isbn: 1234567890123,
        isReturned: false,
        returnDate: new Date()
    },
    {
        id:'2',
        email: 'skunj04@gmail1.com',
        name: 'Kunj',
        bookname: 'Let Us C',
        isbn: 1234567890123,
        isReturned: false,
        returnDate: new Date()
    },
    {
        id:'3',
        email: 'skunj04@gmail.com',
        name: 'Kunj',
        bookname: 'Exeriments',
        isbn: 1234567890098,
        isReturned: false,
        returnDate: new Date()
    }
  ];

  spyOn(IssuedbooksService.prototype, 'getIssuedBooks').and.callFake(() => {
    return from([issuedBooks]);
  });

  component.ngOnInit();
  expect(component.issuedBooks).toEqual(issuedBooks);
}) 
});