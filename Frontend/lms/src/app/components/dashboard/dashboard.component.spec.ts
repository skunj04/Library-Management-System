import { ComponentFixture, TestBed } from '@angular/core/testing';
import { from } from 'rxjs';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import {HttpClientModule} from '@angular/common/http';

import { DashboardComponent } from './dashboard.component';
import { BookService } from 'src/app/services/book.service';
import { CountService } from 'src/app/services/count.service';

describe('DashBoard Components', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;

  let userCount = 4;
  let bookCount=5;
  let issuedBookCount=2;
    beforeEach(() =>{     
    
     TestBed.configureTestingModule({
        imports: [HttpClientTestingModule, HttpClientModule], 
        providers: [BookService]
    })
    fixture = TestBed.createComponent(DashboardComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    
    it('should set value of user count',() => {
    spyOn(CountService.prototype, 'getUserCount').and.callFake(() => {
        return from([userCount]);
    });

    component.ngOnInit();
    expect(component.userCount).toEqual(4);
    }) 

    it('should set value of book count',() => {
        spyOn(CountService.prototype, 'getBookCount').and.callFake(() => {
            return from([bookCount]);
        });
    
    component.ngOnInit();
    expect(component.bookCount).toEqual(5);
    })

    it('should set value of Issued Book count',() => {
        spyOn(CountService.prototype, 'getIssuedBookCount').and.callFake(() => {
            return from([issuedBookCount]);
        });
    
    component.ngOnInit();
    expect(component.issuedBookCount).toEqual(2);
    })
    

});