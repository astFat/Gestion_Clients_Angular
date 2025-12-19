import { Routes } from '@angular/router';
import {App} from './app';
import {ClientsComponent} from './clients.component/clients.component';
import {ContactsComponent} from './contacts.component/contacts.component';
import {ModifierClientComponent} from './modifier.client.component/modifier.client.component';
import {NouveauClientComponent} from './nouveau.client.component/nouveau.client.component';

export const routes: Routes = [
  { path: 'clients', component: ClientsComponent},
  { path: 'contacts', component: ContactsComponent},
  { path: 'clients/nouveau', component: NouveauClientComponent },
  { path: 'clients/modifier/:id', component: ModifierClientComponent }
  ];
