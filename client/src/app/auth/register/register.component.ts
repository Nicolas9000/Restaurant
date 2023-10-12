import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Register, RegisterResponse } from 'src/app/interfaces/AuthInterface';
import { Router } from '@angular/router';
import { ApiResponse } from 'src/app/interfaces/ApiResponseInterface';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
    constructor(
        private fb: FormBuilder,
        private authService: AuthService,
        private router: Router
    ) {}

    errorMessage: string;

    registerForm = this.fb.group(
        {
            name: [null, Validators.required],
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
        if (this.registerForm.valid) {

            const registerData: Register = {
                name: this.registerForm.value.name,
                email: this.registerForm.value.email,
                password: this.registerForm.value.password,
                role: 'ROLE_RESTORER',
            };

            this.authService.signup(registerData).subscribe({
                next: (registerResponse) => {
                    this.router.navigate(['/login']);
                },
                error: (error: string) => {
                    this.errorMessage = error;
                },
            });
        }
    }
}
