import { Component, OnInit } from '@angular/core';
import { ClientServices } from '../services/client-services';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-clients.component',
  imports: [FormsModule, CommonModule],
  templateUrl: './clients.component.html',
  styleUrl: './clients.component.css',
})
export class ClientsComponent implements OnInit {
  clients: any;
  keyword: string = "";

  constructor(
    private clientsService: ClientServices,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getAllClients();
  }

  getAllClients(): any {
    return this.clientsService.listeClients(this.keyword).subscribe({
      next: resp => {
        this.clients = resp;
      },
      error: err => {
        console.log(err);
      }
    });
  }

  deleteClient(c: any) {
    let v = confirm("Êtes-vous sûr?");
    if (v) {
      this.clientsService.supprimerClient(c).subscribe({
        next: value => {
          this.getAllClients();
        },
        error: err => {
          console.log(err);
        }
      });
    }
  }

  chercherClients() {
    this.getAllClients();
  }

  nouveauClient() {
    this.router.navigate(['/clients/nouveau']);
  }

  modifierClient(client: any) {
    this.router.navigate(['/clients/modifier', client.id]);
  }

  protected readonly Array = Array;
}
