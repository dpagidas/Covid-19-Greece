import { FeaturesModule } from './features/features.module';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ToastModule } from 'primeng/toast';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConfirmationService, MessageService, SharedModule } from 'primeng/api';
import { CoreModule } from './core/core.module';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    CoreModule,
    FeaturesModule,
    SharedModule,
    HttpClientModule,
    ToastModule,
  ],
  providers: [ConfirmationService, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
