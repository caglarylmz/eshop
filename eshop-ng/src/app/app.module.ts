import { BrowserModule } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { from } from 'rxjs';
import { MainLayout } from './layouts/main-layout/main.layout';
import { EmptyLayout } from './layouts/empty-layout/empty.layout';
import { AppFooterElement } from './layouts/elements/app-footer/app-footer.element';
import { AppNavbarElement } from './layouts/elements/app-navbar/app-navbar.element';
import { AppSidebarElement } from './layouts/elements/app-sidebar/app-sidebar.element';
import { HeaderAdvertElement } from './app-elements/app-header-advert/header-advert.element';
import { RestAPIService } from './services/rest-api.service';
import { CategoryService } from './services/category.service';
import { HttpClientModule } from '@angular/common/http';

import { FormsModule } from '@angular/forms';
import { authInterceptorProviders } from './helpers/auth.inteceptor';


@NgModule({
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule

  ],
  declarations: [
    AppComponent,
    MainLayout,
    EmptyLayout,
    AppNavbarElement,
    AppSidebarElement,
    AppFooterElement,
    HeaderAdvertElement
  ],
  providers: [
    authInterceptorProviders
  ],
  bootstrap: [AppComponent],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA,
    NO_ERRORS_SCHEMA
  ],

})
export class AppModule { }
