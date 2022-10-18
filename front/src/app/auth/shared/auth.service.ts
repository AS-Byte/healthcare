import {EventEmitter, Injectable, Output} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SignupRequestPayload} from '../signup/singup-request.payload';
import {Observable, throwError} from 'rxjs';
import {LoginRequestPayload} from '../login/login-request.payload';
import {LoginResponse} from '../login/login-response.payload';
import {map, tap} from 'rxjs/operators';
import {LocalStorageService} from 'ngx-webstorage';
import {CommentPayload} from '../../comment/comment-payload';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  @Output() loggedIn: EventEmitter<boolean> = new EventEmitter();
  @Output() username: EventEmitter<string> = new EventEmitter();

  refreshTokenPayload = {
    refreshToken: this.getRefreshToken(),
    username: this.getUserName()
  };

  constructor(private httpClient: HttpClient, private localStorage: LocalStorageService) { }

  signup(signupRequestPayload: SignupRequestPayload): Observable<any>{
    return this.httpClient.post('http://localhost:8080/api/auth/signup', signupRequestPayload, {responseType: 'text'});
  }

  getPatients(): Observable<SignupRequestPayload[]> {
    return this.httpClient.get<SignupRequestPayload[]>('http://localhost:8080/api/auth/all');
  }

  public updatePatient(signupRequestPayload: SignupRequestPayload): Observable<SignupRequestPayload>{
    return this.httpClient.put<SignupRequestPayload>('http://localhost:8080/api/auth/update', signupRequestPayload);
  }

  public deletePatient(patientName: string): Observable<void>{
    return this.httpClient.delete<void>('http://localhost:8080/api/auth/delete/' + patientName);
  }

  getPatientByName(PatientName: string) {
    return this.httpClient.get<SignupRequestPayload>('http://localhost:8080/api/auth/findPatient/' + PatientName);
  }

  login(loginRequestPayload: LoginRequestPayload): Observable<boolean>{
    return this.httpClient.post<LoginResponse>('http://localhost:8080/api/auth/login', loginRequestPayload)
      .pipe(map(data => {
      this.localStorage.store('authenticationToken', data.authenticationToken);
      this.localStorage.store('username', data.username);
      this.localStorage.store('expiresAt', data.expiresAt);
      this.localStorage.store('refreshToken', data.refreshToken);

      return true;
    }));
  }

  getJwtToken() {
    return this.localStorage.retrieve('authenticationToken');
  }

  refreshToken() {
    const   refreshTokenPayload = {
      refreshToken: this.getRefreshToken(),
      username: this.getUserName()
    };
    return this.httpClient.post<LoginResponse>('http://localhost:8080/api/auth/refresh/token',
      refreshTokenPayload)
      .pipe(tap(response => {
        this.localStorage.store('authenticationToken',
          response.authenticationToken);
        this.localStorage.store('expiresAt', response.expiresAt);
      }));
  }

  logout() {
    this.httpClient.post('http://localhost:8080/api/auth/logout', this.refreshTokenPayload,
      { responseType: 'text' })
      .subscribe(data => {
        console.log(data);
      }, error => {
        throwError(error);
      });
    this.localStorage.clear('authenticationToken');
    this.localStorage.clear('username');
    this.localStorage.clear('refreshToken');
    this.localStorage.clear('expiresAt');
  }

  getUserName() {
    return this.localStorage.retrieve('username');
  }
  getRefreshToken() {
    return this.localStorage.retrieve('refreshToken');
  }
  isLoggedIn(): boolean {
    return this.getJwtToken() != null;
  }
}
