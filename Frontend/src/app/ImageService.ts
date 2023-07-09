import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable()
export class ImageService{

    constructor(private http: HttpClient){}
    url2:string='http://localhost:8000/image';
    url:string='http://localhost:8000/image/add';

uploadImg(file:any):Observable<string>{
    return this.http.post(this.url, file, { responseType: 'text' });
}

getImg(name:string){
    return this.http.get(this.url2+"/"+name);
}

}