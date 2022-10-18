import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SignupComponent} from './auth/signup/signup.component';
import {LoginComponent} from './auth/login/login.component';
import {HomeComponent} from './home/home.component';
import {CreateSoinsComponent} from './soins/create-soins/create-soins.component';
import {CreateTypeSoinsComponent} from './typeSoins/create-type-soins/create-type-soins.component';
import {ListTypeSoinssComponent} from './typeSoins/list-type-soinss/list-type-soinss.component';
import {ViewSoinsComponent} from './soins/view-soins/view-soins.component';
import {UserProfileComponent} from './auth/user-profile/user-profile.component';
import {AuthGuard} from './auth/auth.guard';
import {PatientsComponent} from './auth/patients/patients.component';
import {ListSoinsComponent} from './list-soins/list-soins.component';


const routes: Routes = [
  {path: '', component: HomeComponent},
  { path: 'user-profile/:name', component: UserProfileComponent, canActivate: [AuthGuard] },
  { path: 'view-soins/:id', component: ViewSoinsComponent },
  { path: 'list-typeSoinss', component: ListTypeSoinssComponent },
  { path: 'create-soins', component: CreateSoinsComponent, canActivate: [AuthGuard] },
  { path: 'create-typeSoins', component: CreateTypeSoinsComponent, canActivate: [AuthGuard] },
  {path: 'sign-up', component: SignupComponent},
  {path: 'login', component: LoginComponent},
  {path: 'admin', component: PatientsComponent, canActivate: [AuthGuard]},
  {path: 'listSoins', component: ListSoinsComponent, canActivate: [AuthGuard]}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
