import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NouveauClientComponent } from './nouveau.client.component';

describe('NouveauClientComponent', () => {
  let component: NouveauClientComponent;
  let fixture: ComponentFixture<NouveauClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NouveauClientComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NouveauClientComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
