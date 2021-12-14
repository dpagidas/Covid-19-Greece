import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from 'primeng/api';
import { PrimengSharedModule } from './../shared/primeng-shared/primeng-shared.module';

import { HeaderComponent } from './header/header.component';
import { MainPageComponent } from './main-page/main-page.component';
import { TopbarComponent } from './topbar/topbar.component';
import { VerticalMenuComponent } from './vertical-menu/vertical-menu.component';
import { AppMenuitemComponent } from './vertical-menu/app.menuitem.component';
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [HeaderComponent, MainPageComponent, TopbarComponent, VerticalMenuComponent, AppMenuitemComponent, FooterComponent],
  imports: [
    CommonModule,
    FormsModule,
    PrimengSharedModule,
    SharedModule
  ],
  exports: [
    HeaderComponent,
    MainPageComponent,
    TopbarComponent,
    VerticalMenuComponent,
    AppMenuitemComponent
  ]
})
export class CoreModule { }
