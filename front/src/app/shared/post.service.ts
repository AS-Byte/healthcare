import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SoinsModel} from './soins-model';
import {CreateSoinsPayload} from '../soins/create-soins/create-soins.payload';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  getAllPosts(): Observable<Array<SoinsModel>> {
    return this.http.get<Array<SoinsModel>>('http://localhost:8080/api/soins/');
  }

  createPost(postPayload: CreateSoinsPayload): Observable<SoinsModel> {
    return this.http.post<SoinsModel>('http://localhost:8080/api/soins/', postPayload);
  }
  getPost(id: number): Observable<SoinsModel> {
    return this.http.get<SoinsModel>('http://localhost:8080/api/soins/' + id);
  }
  getAllPostsByUser(name: string): Observable<SoinsModel[]> {
    return this.http.get<SoinsModel[]>('http://localhost:8080/api/soins/by-patient/' + name);
  }
}
