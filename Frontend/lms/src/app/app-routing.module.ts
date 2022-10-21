import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddUserComponent } from './components/add-user/add-user.component';
import { BooksComponent } from './components/books/books.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { IssueBooksComponent } from './components/issue-books/issue-books.component';
import { LoginComponent } from './components/login/login.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ReturnBooksComponent } from './components/return-books/return-books.component';
import { UsersComponent } from './components/users/users.component';
import { IssuedBooksComponent } from './components/issued-books/issued-books.component';
import { AddBookComponent } from './components/add-book/add-book.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';
import { EditBookComponent } from './components/edit-book/edit-book.component';
import { LogoutComponent } from './components/logout/logout.component';

const routes: Routes = [
  {path : "", redirectTo: '/dashboard', pathMatch: 'full'},
  {path:"login", component:LoginComponent},
  {path:"logout", component:LogoutComponent},
  {path:"dashboard", component: DashboardComponent},
  {path:"users", component: UsersComponent},
  {path:"books", component: BooksComponent},
  {path:"issuebooks", component: IssuedBooksComponent},
  {path:"addIssueBook", component: IssueBooksComponent},
  {path:"returnbooks", component: ReturnBooksComponent},
  {path:"addUser", component: AddUserComponent},
  {path:"addBook", component: AddBookComponent},
  {path: 'users/:id', component: EditUserComponent},
  {path: 'books/:ISBN', component: EditBookComponent},
  {path : "**", component: PageNotFoundComponent} //Always be last in list
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


export const routingComponents = [LoginComponent,DashboardComponent,PageNotFoundComponent,UsersComponent,BooksComponent, IssueBooksComponent,ReturnBooksComponent, AddUserComponent]