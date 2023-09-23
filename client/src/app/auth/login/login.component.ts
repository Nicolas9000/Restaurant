import { Component, OnInit, Input } from '@angular/core';
import { AuthService } from '../auth.service';
import {
    FormControl,
    FormGroup,
    FormBuilder,
    Validators,
} from '@angular/forms';
import { ApiResponse } from 'src/app/interfaces/ApiResponse';
import { Login, LoginResponse } from 'src/app/interfaces/AuthInterface';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
    constructor(private fb: FormBuilder, private authService: AuthService) {}

    loginForm = this.fb.group({
        email: ['', [Validators.required, Validators.email]],
        password: ['', Validators.required],
    });

    disabled: boolean;

    ngOnInit(): void {}

    onSubmit(): void {
        if (this.loginForm.valid) {
            const formValue: Login = {
                email: this.loginForm.value.email,
                password: this.loginForm.value.password,
            };
            this.authService
                .signin(formValue)
                .subscribe((data: LoginResponse) =>
                    this.authService.setCookie(data.token)
                );
        }

        this.authService
            .teste('plz')
            .subscribe((res: ApiResponse<null>) => console.log(res));
    }
}
