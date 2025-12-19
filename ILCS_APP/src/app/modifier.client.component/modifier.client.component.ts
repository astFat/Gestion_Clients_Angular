import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientServices } from '../services/client-services';

@Component({
  selector: 'app-modifier-client',
  imports: [CommonModule, FormsModule],
  templateUrl: './modifier.client.component.html',
  styleUrl: './modifier.client.component.css'
})
export class ModifierClientComponent implements OnInit {
  client: any = { id: null, nom: '', age: null };
  clientId: number = 0;

  constructor(
    private clientsService: ClientServices,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.clientId = this.route.snapshot.params['id'];
    this.chargerClient();
  }

  chargerClient() {
    this.clientsService.getClientById(this.clientId).subscribe({
      next: resp => {
        this.client = resp;
      },
      error: err => {
        console.log(err);
        this.router.navigate(['/clients']);
      }
    });
  }

  modifierClient() {
    this.clientsService.modifierClient(this.client).subscribe({
      next: resp => {
        this.router.navigate(['/clients']);
      },
      error: err => {
        console.log(err);
      }
    });
  }
}
