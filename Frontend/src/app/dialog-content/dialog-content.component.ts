import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { BookService } from '../BooksService';
import { AddBookComponent } from '../add-book/add-book.component';
import { BooksComponent } from '../books/books.component';

@Component({
  selector: 'app-dialog-content',
  templateUrl: './dialog-content.component.html',
  styleUrls: ['./dialog-content.component.css']
})
export class DialogContentComponent {

  constructor(private dialogRef: MatDialogRef<DialogContentComponent>, 
    private bookService: BookService, @Inject(MAT_DIALOG_DATA) public data: any){}

  onCancel(){
    this.dialogRef.close()
  }

  onDelete(){
   
    this.bookService.delete(this.data.bookId).subscribe(rep=> 
     {this.dialogRef.close('refresh')}
    ,rep=>{})

  }
}
