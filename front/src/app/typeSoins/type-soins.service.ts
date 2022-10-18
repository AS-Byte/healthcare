import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TypeSoinsModel} from './typeSoins-model';

@Injectable({
  providedIn: 'root'
})
export class TypeSoinsService {

  constructor(private http: HttpClient) { }

  getAllSubreddits(): Observable<Array<TypeSoinsModel>> {
    return this.http.get<Array<TypeSoinsModel>>('http://localhost:8080/api/typesoins');
  }

  createTypeSoins(typeSoinsModel: TypeSoinsModel): Observable<TypeSoinsModel> {
    return this.http.post<TypeSoinsModel>('http://localhost:8080/api/typesoins',
      typeSoinsModel);
  }

}
