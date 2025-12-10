import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ClientServices {
  clients: any;
  constructor(private http: HttpClient) {
  }

  listeClients(keyword: string = "") {
    return this.http.get("http://localhost:9006/clients", {params: {keyword: keyword}});
  }

  supprimerClient(c: any) {
    return this.http.delete("http://localhost:9006/clients/" + c.id);
  }

  chercherClient(c: any) {
    return this.http.get("http://localhost:9006/clients/" + c.chercherId);
  }

  ajouterClient(client: any) {
    return this.http.post("http://localhost:9006/clients", client);
  }

  modifierClient(client: any) {
    return this.http.put(`http://localhost:9006/clients/${client.id}`, client);
  }
}
