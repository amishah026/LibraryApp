import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../BooksService';
import Book from '../Book';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit{
  book!:Book;
  id!:number;
  errorMessage!:string;
  successMessage!:string;

  editForm!:FormGroup;
  
  constructor(private route: ActivatedRoute, private bookService: BookService, private formBuilder:FormBuilder, private router: Router){}
  
  ngOnInit(): void {
    this.route.params.subscribe(param=>this.id=param['bookId']);
    this.bookService.getBook(this.id).subscribe(rep=>
      {this.book=rep
        this.editForm=this.formBuilder.group({
          bookId:[this.id,[Validators.required]],
          imageUrl:[this.book.imageUrl,[]],
          title:[this.book.title,[Validators.required]],
          author:[this.book.author,[Validators.required]],
          pages:[this.book.pages,[Validators.required, Validators.min(1)]],
          publishDate:[this.book.publishDate,[Validators.required,this.validateDate]]
        });
      }
      , rep=>{
    this.errorMessage=rep.error.errorMessage});
  
  } 


   validateDate(c: FormControl) {
   let date = new Date(c.value)
   let datetoday = new Date();
    date.setDate(date.getDate()+1);
    return date>=datetoday ? {
      dateError: true
      } : null;
  }
  
  onsubmit(){
    this.bookService.updateBook(this.id,this.editForm.value).subscribe(rep=>this.successMessage=rep, rep=>{this.errorMessage=rep.error})

  }

  click(){
    this.router.navigate(['/books'])
  }

}
