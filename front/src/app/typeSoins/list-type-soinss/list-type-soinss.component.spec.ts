import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListTypeSoinssComponent } from './list-type-soinss.component';

describe('ListTypeSoinssComponent', () => {
  let component: ListTypeSoinssComponent;
  let fixture: ComponentFixture<ListTypeSoinssComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListTypeSoinssComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListTypeSoinssComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
