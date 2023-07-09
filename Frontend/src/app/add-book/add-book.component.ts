import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BookService } from '../BooksService';
import { ImageService } from '../ImageService';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent {
  editForm!:FormGroup;
  successMessage!:string;
  errorMessage!:string;
  picSuccess!:string;
  picError!:string;

  constructor( private bookService: BookService, private formBuilder:FormBuilder, private router: Router, private imgService: ImageService){}
  
  ngOnInit(): void {
        this.editForm=this.formBuilder.group({
          imageUrl:['',[Validators.required]],
          title:['',[Validators.required]],
          author:['',[Validators.required]],
          pages:['',[Validators.required, Validators.min(1)]],
          publishDate:['',[Validators.required,this.validateDate]]
        });
  } 


   validateDate(c: FormControl) {
   let date = new Date(c.value)
   let datetoday = new Date();
    date.setDate(date.getDate()+1);
    return date>=datetoday ? {
      dateError: true
      } : null;
  }
  

  click(){
    this.router.navigate(['/books'])
  }

  fileName!:string;

  uploadPicture(pic:any){
    this.picSuccess='';
  this.picError='';
    let file = pic.files[0];
    
    if (file) {
      this.fileName=file.name;
      const formData: FormData = new FormData();
      formData.append('imgfile',file, file.name);
      this.imgService.uploadImg(formData).subscribe(rep=> this.picSuccess=rep, rep=>{this.picError=rep.error
    });
      
  }
  }


onsubmit(){
  this.picSuccess='';
  this.picError='';
  this.editForm.value.imageUrl=this.fileName;
 this.bookService.addBook(this.editForm.value).subscribe(rep=> this.successMessage=rep, rep=>this.errorMessage=rep.error);
}

}