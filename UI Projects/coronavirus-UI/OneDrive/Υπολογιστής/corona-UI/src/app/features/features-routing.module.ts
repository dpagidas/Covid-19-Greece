import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CovidNewsComponent } from './covid-news/covid-news.component';
import { VaccinationsComponent } from './vaccinations/vaccinations.component';

const routes: Routes = [
  {
    path: 'pages',
    canActivate: [],
    children: [
      { path: 'home', component: HomeComponent},
      { path: 'covid-19-news', component: CovidNewsComponent},
      { path: 'vaccinations' , component: VaccinationsComponent}
    ]
  },
  { path: '**', redirectTo: 'pages', pathMatch: 'full' }
  ];





@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FeaturesRoutingModule {
}
