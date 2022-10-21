import { Component, OnInit } from '@angular/core';
import { CountService } from '../../services/count.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  userCount = 0;
  bookCount=0;
  issuedBookCount=0;
  constructor(private _countService: CountService) { }
  ngOnInit(): void {
    this._countService.getUserCount()
      .subscribe(data => this.userCount = data);  
    this._countService.getBookCount()
    .subscribe(data => this.bookCount = data); 
    this._countService.getIssuedBookCount()
    .subscribe(data => this.issuedBookCount = data);      
  }

}
