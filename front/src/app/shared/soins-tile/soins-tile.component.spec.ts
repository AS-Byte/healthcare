import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SoinsTileComponent } from './soins-tile.component';

describe('SoinsTileComponent', () => {
  let component: SoinsTileComponent;
  let fixture: ComponentFixture<SoinsTileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SoinsTileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SoinsTileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
