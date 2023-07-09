import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BooksComponent } from './books/books.component';
import { AddBookComponent } from './add-book/add-book.component';
import { EditComponent } from './edit/edit.component';

const routes: Routes = [
  { path: 'books', component:BooksComponent},
  { path: 'addBook', component: AddBookComponent},
  { path: 'editBook/:bookId', component: EditComponent},
  { path: '**', redirectTo: 'books', pathMatch:'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
