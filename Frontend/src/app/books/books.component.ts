import { Component, Injectable, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { BookService } from '../BooksService';
import Book from '../Book';
import { Router } from '@angular/router';
import { ImageService } from '../ImageService';
import { MatDialog } from '@angular/material/dialog';
import { DialogContentComponent } from '../dialog-content/dialog-content.component';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})


export class BooksComponent implements OnInit {
    books: Book[] = [];
    processedBooks: Book[] = [];
    errorMessage!:string;

    constructor(
      private bookService: BookService,
      private router: Router,
      private imgService: ImageService,
      private dialog: MatDialog
    ) {}
      
    ngOnInit(): void {
      this.bookService.getBooks().subscribe(
        (response: Book[]) => {
          this.books = response;
          this.processBooks();
        },
        (rep) => {
          this.processedBooks=[];
          this.errorMessage = rep.error.errorMessage;
        }
      );
    }
  
    async processBooks() {
      this.processedBooks = [];
  
      for (const bk of this.books) {
        await this.img(bk.imageUrl);
        const processedBook: Book = {
          author: bk.author,
          bookId: bk.bookId,
          imageUrl: this.geturl(),
          pages: bk.pages,
          publishDate: bk.publishDate,
          title: bk.title,
        };
  
        this.processedBooks.push(processedBook);
      }
    }
  
    retrieveResponse!: any;
    base64Data!: any;
    retrievedImage!: any;
  
    async img(name: string) {
      try {
        const response = await this.imgService.getImg(name).toPromise();
        this.retrieveResponse = response;
        this.base64Data = this.retrieveResponse.imageData;
        this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
      } catch (error) {
        console.error('Error fetching image', error);
      }
    }
  
    geturl(): string {
      return this.retrievedImage;
    }
  
  edit(book:Book):void{
    
    this.router.navigate(['/editBook', book.bookId])
  }

  delete(book:Book):void{
    const dialogRef = this.dialog.open(DialogContentComponent,{data: {'bookId': book.bookId}});
    
    dialogRef.afterClosed().subscribe(rep=>{
      if(rep ==='refresh'){
        this.ngOnInit();
      }
    })
  }

}
