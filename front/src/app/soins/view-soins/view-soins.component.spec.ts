import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewSoinsComponent } from './view-soins.component';

describe('ViewSoinsComponent', () => {
  let component: ViewSoinsComponent;
  let fixture: ComponentFixture<ViewSoinsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewSoinsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewSoinsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
