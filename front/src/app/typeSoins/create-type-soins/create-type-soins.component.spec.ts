import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateTypeSoinsComponent } from './create-type-soins.component';

describe('CreateTypeSoinsComponent', () => {
  let component: CreateTypeSoinsComponent;
  let fixture: ComponentFixture<CreateTypeSoinsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateTypeSoinsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateTypeSoinsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
