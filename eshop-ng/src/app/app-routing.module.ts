import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { EmptyLayout } from './layouts/empty-layout/empty.layout';
import { MainLayout } from './layouts/main-layout/main.layout';


const routes: Routes = [
  {
    path: '', redirectTo: '/home', pathMatch: 'full'
  },
  {
    path: 'home', component: MainLayout,
    children:
      [
        { path: '', component: HomeComponent }
      ]
  },
  { path: 'uye', component: EmptyLayout }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
