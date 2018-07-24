import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessfulUploadComponent } from './successful-upload.component';

describe('SuccessfulUploadComponent', () => {
  let component: SuccessfulUploadComponent;
  let fixture: ComponentFixture<SuccessfulUploadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuccessfulUploadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuccessfulUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
