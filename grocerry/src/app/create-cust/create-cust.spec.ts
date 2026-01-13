import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCustComponent } from './create-cust';

describe('CreateCust', () => {
  let component: CreateCustComponent;
  let fixture: ComponentFixture<CreateCustComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CreateCustComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateCustComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});   
