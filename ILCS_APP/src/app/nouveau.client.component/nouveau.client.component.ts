import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ClientServices } from '../services/client-services';

@Component({
  selector: 'app-nouveau-client',
  imports: [CommonModule, FormsModule],
  templateUrl: './nouveau.client.component.html',
  styleUrl: './nouveau.client.component.css'
})
export class NouveauClientComponent {
  newClient: any = { nom: '', age: null };

  constructor(
    private clientsService: ClientServices,
    private router: Router
  ) {}

  enregistrerClient() {
    this.clientsService.ajouterClient(this.newClient).subscribe({
      next: resp => {
        this.router.navigate(['/clients']);
      },
      error: err => {
        console.log(err);
      }
    });
  }
}
