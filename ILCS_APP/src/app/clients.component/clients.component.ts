import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ClientServices} from '../services/client-services';
import {Router} from '@angular/router';
import {FormsModule} from '@angular/forms';
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
  showForm: boolean = false;
  newClient: any = { id: null, nom: '', age: null };
  constructor(private clientsService: ClientServices, private router: Router) {
  }
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
    let v = confirm("Etes vous sure?");
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
    this.showForm = true;
    this.newClient = { id: null, nom: '', age: null };
  }
  modifierClient(client: any) {
    this.showForm = true;
    this.newClient = { ...client };
  }
  enregistrerClient() {
    if (!this.newClient.nom || !this.newClient.age) {
      alert("Veuillez remplir tous les champs");
      return;
    }
    let saveOperation;
    if (this.newClient.id === null) {
      saveOperation = this.clientsService.ajouterClient(this.newClient);
    }
    else {
      saveOperation = this.clientsService.modifierClient(this.newClient);
    }
    saveOperation.subscribe({
      next: resp => {
        this.getAllClients();
        this.showForm = false;
        this.newClient = { id: null, nom: '', age: null };
      },
      error: err => {
        console.log(err);
      }
    });
  }
  protected readonly Array = Array;
}
