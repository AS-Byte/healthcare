import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TypeSoinsSideBarComponent } from './type-soins-side-bar.component';

describe('TypeSoinsSideBarComponent', () => {
  let component: TypeSoinsSideBarComponent;
  let fixture: ComponentFixture<TypeSoinsSideBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TypeSoinsSideBarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TypeSoinsSideBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
