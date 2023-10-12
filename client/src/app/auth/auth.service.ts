import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CookieService } from '../services/cookie/cookie.service';
import {
    Login,
    LoginResponse,
    Register,
    RegisterResponse,
} from '../interfaces/AuthInterface';
import { ApiResponse } from '../interfaces/ApiResponseInterface';
import { ApiService } from '../services/api/api.service';

@Injectable({ providedIn: 'root' })
export class AuthService {
    constructor(
        private cookieService: CookieService,
        private apiService: ApiService
    ) {}

    private apiUrl: string = '/auth';

    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    };

    isLoggedIn(): boolean {
        if (!this.cookieService.getCookie()) {
            return false;
        }
        return true;
    }

    signup(data: Register): Observable<ApiResponse<RegisterResponse>> {
        return this.apiService.post<ApiResponse<RegisterResponse>, Register>(
            `${this.apiUrl}/signup`,
            data
        );
    }

    signin(data: Login): Observable<ApiResponse<LoginResponse>> {
        return this.apiService.post<ApiResponse<LoginResponse>, Login>(
            `${this.apiUrl}/signin`,
            data
        );
    }
}
