import { Injectable } from '@angular/core';
import { CookieService as NgCookie } from 'ngx-cookie-service';

@Injectable({
    providedIn: 'root',
})
export class CookieService {
    constructor(private ngCookie: NgCookie) {}

    setCookie(token: string): void {
        this.ngCookie.set('token', token);
    }

    getCookie(): string {
        return this.ngCookie.get('token');
    }
}
