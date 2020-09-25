import { BrowserModule } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppNavbarElement } from './elements/app-navbar/app-navbar.element';
import { AppSidebarElement } from './elements/app-sidebar/app-sidebar.element';
import { AppFooterElement } from './elements/app-footer/app-footer.element';
import { from } from 'rxjs';

@NgModule({
  declarations: [
    AppComponent,
    AppNavbarElement,
    AppSidebarElement,
    AppFooterElement

  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA,
    NO_ERRORS_SCHEMA
  ]
})
export class AppModule { }
