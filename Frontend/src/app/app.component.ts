import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'LibraryApp';
  imageUrl: string = 'assets/logo.png';

  styleOne: object={textDecoration: 'underline',
    color: 'white'}
    styleTwo!:object;

    checkOne:boolean=true;
    checkTwo:boolean=true;

    click(checknum:string){
      if (checknum ==='one'){
        this.checkOne=!this.checkOne
      }else if (checknum==='two'){
        this.checkTwo=!this.checkTwo
    }
  }

}
