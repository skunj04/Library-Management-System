import { ComponentFixture, TestBed } from '@angular/core/testing';
import { from } from 'rxjs';
import { UserService } from 'src/app/services/user.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import {HttpClientModule} from '@angular/common/http';
import { IssueBooks } from 'src/app/models/issuebooks.model';
import { IssuedbooksService } from 'src/app/services/issuedbooks.service';
import { IssueBooksComponent } from './issue-books.component';

describe('UsersComponent', () => {
  let component: IssueBooksComponent;
  let fixture: ComponentFixture<IssueBooksComponent>;

  beforeEach(() =>{ TestBed.configureTestingModule({
    imports: [HttpClientTestingModule, HttpClientModule], 
    providers: [UserService]
  })
  fixture = TestBed.createComponent(IssueBooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

 it('should set issue books array with issuebooks',() => {
  const issueBooks : IssueBooks = {
    "Users": [
        {
            "email": "skunj04@gmail.com",
            "name": "Kunj Shah",
            "gender": "male",
            "address": "Gujarat",
            "type": "Admin",
            "password": "skunj04",
            "contact": 8980462225,
            "active": true,
            "dob": "04-02-2001"
        },
        {
            "email": "skunj04@gmail1.com",
            "name": "Kunj Shah",
            "gender": "male",
            "address": "Gujarat",
            "type": "Admin",
            "password": "skunj04",
            "contact": 8980462225,
            "active": true,
            "dob": "04-02-2001"
        }
    ],
    "Books": [
        {
            "name": "The Story of My Experiments with Truth",
            "description": "The Story of My Experiments with Truth is the autobiography of Mahatma Gandhi, covering his life from early childhood through to 1921. It was written in weekly installments and published in his journal Navjivan from 1925 to 1929. Its English translation also appeared in installments in his other journal Young India.",
            "authorName": "Mahatma Gandhi",
            "category": "biography",
            "quantity": 100,
            "isbn": 9789390932313
        },
        {
            "name": "The Story of My Experiments with Truth",
            "description": "The Story of My Experiments with Truth is the autobiography of Mahatma Gandhi, covering his life from early childhood through to 1921. It was written in weekly installments and published in his journal Navjivan from 1925 to 1929. Its English translation also appeared in installments in his other journal Young India.",
            "authorName": "M K Gandhi",
            "category": "biography",
            "quantity": 10,
            "isbn": 123
        },
        {
            "name": "Let Us C",
            "description": "Let Us C by Yashavant Kanetkar addresses several topics that deal with C programming language. The book combines theory with many exercises and worked examples to help beginners.",
            "authorName": "Yashavant Kanetkar",
            "category": "Programming",
            "quantity": 10,
            "isbn": 9788176569408
        }
    ]
}

  spyOn(IssuedbooksService.prototype, 'selectUserBooks').and.callFake(() => {
    return from([issueBooks]);
  });

  component.ngOnInit();
  expect(component.selectUserBooks).toEqual(issueBooks);
}) 
});