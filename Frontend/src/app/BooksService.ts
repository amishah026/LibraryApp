
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import Book from './Book';
import { Observable, map } from 'rxjs';

@Injectable()
export class BookService{

    constructor(private http: HttpClient){}

    url:string= "http://localhost:8000/library/books";
    url2:string= "http://localhost:8000/library/book";
    url3:string= "http://localhost:8000/library/addbook"

    getBooks():Observable<Book[]>{
        return this.http.get<Book[]>(this.url);
    }

    getBook(id:number):Observable<Book>{
        return this.http.get<Book>(this.url2+"/"+ id);
    }



    updateBook(id:number, book:Book):Observable<string>{
        return this.http.put(this.url2+"/"+id, book,{ responseType: 'text' });
    }

    addBook(book:Book):Observable<string>{
        return this.http.post(this.url3,book,{ responseType: 'text' });
    }

    delete(id:number):Observable<string>{
        return this.http.delete(this.url2+"/"+id,{responseType: 'text'});
    }
    
}