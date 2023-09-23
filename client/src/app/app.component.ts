import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth/auth.service';
import { ApiResponse } from './interfaces/ApiResponse';


@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
    title = 'client';

    constructor(private authService: AuthService) {}

    test: ApiResponse<null> | undefined;

    ngOnInit(): void {
        this.authService.test().subscribe((t) => (this.test = t));
    }
}
