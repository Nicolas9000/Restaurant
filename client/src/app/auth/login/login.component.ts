import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Login } from 'src/app/interfaces/AuthInterface';
import { Router } from '@angular/router';
import { CookieService } from 'src/app/services/cookie/cookie.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
    constructor(
        private fb: FormBuilder,
        private router: Router,
        private authService: AuthService,
        private cookieService: CookieService
    ) {}

    disabled: boolean;
    errorMessage: string;

    loginForm = this.fb.group(
        {
            email: [null, [Validators.required, Validators.email]],
            password: [null, Validators.required],
        },
        { updateOn: 'submit' }
    );

    ngOnInit(): void {
        if (this.authService.isLoggedIn()) {
            this.router.navigate(['/create_restaurant']);
        }
    }

    onSubmit(): void {
        if (this.loginForm.valid) {
            const loginData: Login = {
                email: this.loginForm.value.email,
                password: this.loginForm.value.password,
            };

            this.authService.signin(loginData).subscribe({
                next: (loginResponse) => {
                    this.cookieService.setCookie(loginResponse.data.token);
                    this.router.navigate(['/create_restaurant']);
                },
                error: (error) => {
                    this.errorMessage = error;
                },
            });
        }
    }
}
