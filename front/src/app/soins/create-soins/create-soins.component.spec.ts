import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSoinsComponent } from './create-soins.component';

describe('CreateSoinsComponent', () => {
  let component: CreateSoinsComponent;
  let fixture: ComponentFixture<CreateSoinsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateSoinsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateSoinsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
