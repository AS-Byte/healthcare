import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { SignupComponent } from './auth/signup/signup.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { LoginComponent } from './auth/login/login.component';
import {NgxWebstorageModule} from 'ngx-webstorage';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ToastrModule} from 'ngx-toastr';
import { HomeComponent } from './home/home.component';
import {TokenInterceptor} from './token-interceptor';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import { SoinsTileComponent } from './shared/soins-tile/soins-tile.component';
import { SideBarComponent } from './shared/side-bar/side-bar.component';
import { TypeSoinsSideBarComponent } from './shared/type-soins-side-bar/type-soins-side-bar.component';
import { VoteButtonComponent } from './shared/vote-button/vote-button.component';
import { CreateTypeSoinsComponent } from './typeSoins/create-type-soins/create-type-soins.component';
import { CreateSoinsComponent } from './soins/create-soins/create-soins.component';
import { ListTypeSoinssComponent } from './typeSoins/list-type-soinss/list-type-soinss.component';
import {EditorModule} from '@tinymce/tinymce-angular';
import { ViewSoinsComponent } from './soins/view-soins/view-soins.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { UserProfileComponent } from './auth/user-profile/user-profile.component';
import { PatientsComponent } from './auth/patients/patients.component';
import { ListSoinsComponent } from './list-soins/list-soins.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    SoinsTileComponent,
    SideBarComponent,
    TypeSoinsSideBarComponent,
    VoteButtonComponent,
    CreateTypeSoinsComponent,
    CreateSoinsComponent,
    ListTypeSoinssComponent,
    ViewSoinsComponent,
    UserProfileComponent,
    PatientsComponent,
    ListSoinsComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ReactiveFormsModule,
        HttpClientModule,
        NgxWebstorageModule.forRoot(),
        BrowserAnimationsModule,
        ToastrModule.forRoot(),
        FontAwesomeModule,
        EditorModule,
        NgbModule,
        FormsModule
    ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
