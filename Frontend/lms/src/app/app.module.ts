import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { UsersComponent } from './components/users/users.component';
import { BooksComponent } from './components/books/books.component';
import { IssueBooksComponent } from './components/issue-books/issue-books.component';
import { ReturnBooksComponent } from './components/return-books/return-books.component';
import { AddUserComponent } from './components/add-user/add-user.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IssuedBooksComponent } from './components/issued-books/issued-books.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddBookComponent } from './components/add-book/add-book.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';
import { EditBookComponent } from './components/edit-book/edit-book.component';
import { LogoutComponent } from './components/logout/logout.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PageNotFoundComponent,
    DashboardComponent,
    UsersComponent,
    BooksComponent,
    IssueBooksComponent,
    ReturnBooksComponent,
    AddUserComponent,
    IssuedBooksComponent,
    AddBookComponent,
    EditUserComponent,
    EditBookComponent,
    LogoutComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
