import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeinformationComponent } from './changeinformation.component';

describe('ChangeinformationComponent', () => {
  let component: ChangeinformationComponent;
  let fixture: ComponentFixture<ChangeinformationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangeinformationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeinformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
