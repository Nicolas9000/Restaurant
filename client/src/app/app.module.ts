import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthModule } from './auth/auth.module';
import { CookieService } from 'ngx-cookie-service';
import { FormsModule } from '@angular/forms';
import { RestaurantModule } from './restaurant/restaurant.module';

@NgModule({
    declarations: [AppComponent],
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        AuthModule,
        RestaurantModule,
        AppRoutingModule,
    ],
    providers: [CookieService],
    bootstrap: [AppComponent],
})
export class AppModule {}
