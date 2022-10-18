import { Component, OnInit } from '@angular/core';
import {SignupRequestPayload} from '../signup/singup-request.payload';
import {AuthService} from '../shared/auth.service';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';

@Component({
  selector: 'app-patients',
  templateUrl: './patients.component.html',
  styleUrls: ['./patients.component.css']
})
export class PatientsComponent implements OnInit {

  public patients: SignupRequestPayload[];

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.getPatients();
  }

  public getPatients(): void{
    this.authService.getPatients().subscribe(
      (response: SignupRequestPayload[]) => {
        this.patients = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeletePatient(patientName: string): void{
    this.authService.deletePatient(patientName).subscribe(
      (response: void) => {
      this.getPatients();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
}

  public onOpenModal(patient: SignupRequestPayload, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'block';
    button.setAttribute('data-target', 'modal');
    if (mode === 'edit'){
      button.setAttribute('data-target', '#exampleModal');
    }
    container.appendChild(button);
    button.click();
  }

  public serchPatients(key: string): void{
    console.log(key);
    const results: SignupRequestPayload[] = [];
    for (const patient of this.patients){
      if(patient.patientname.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || patient.email.toLowerCase().indexOf(key.toLowerCase()) !== -1){
        results.push(patient);
      }
    }
    this.patients = results;
    if (results.length === 0 || !key){
      this.getPatients();
    }
  }



}
