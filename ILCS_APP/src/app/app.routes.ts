import { Routes } from '@angular/router';
import {App} from './app';
import {ClientsComponent} from './clients.component/clients.component';
import {ContactsComponent} from './contacts.component/contacts.component';

export const routes: Routes = [
  { path: 'clients', component: ClientsComponent},
  { path: 'contacts', component: ContactsComponent},
  ];
