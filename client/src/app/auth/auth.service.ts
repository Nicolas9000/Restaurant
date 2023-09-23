import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, tap, catchError } from 'rxjs';
import { ApiResponse } from '../interfaces/ApiResponse';
import { CookieService } from 'ngx-cookie-service';
import { Login, LoginResponse } from '../interfaces/AuthInterface';

@Injectable({ providedIn: 'root' })
export class AuthService {
    constructor(private http: HttpClient, private cookieService: CookieService) {}

    private apiUrl: string = 'http://localhost:8080/api/auth';

    isLoggingIn: boolean = false;
    redirectUrl: string | undefined;

    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    };

    test(): Observable<ApiResponse<null>> {
        return this.http.get<ApiResponse<null>>(`${this.apiUrl}/test`).pipe(
            tap((res) => this.log(res)),
            catchError((error) => this.handleError(error, []))
        );
    }


    teste(data: any = "ezzzz"): Observable<ApiResponse<null>> {
        return this.http
            .post<ApiResponse<null>>(
                `${this.apiUrl}/teste`,
                data,
                this.httpOptions
            )
            .pipe(
                tap((res) => this.log(res)),
                catchError((err) => this.handleError(err, []))
            );
    }

    signup(data: any): Observable<ApiResponse<null>> {
        return this.http
            .post<ApiResponse<null>>(
                `${this.apiUrl}/signup`,
                data,
                this.httpOptions
            )
            .pipe(
                tap((res) => this.log(res)),
                catchError((err) => this.handleError(err, []))
            );
    }

    signin(data: Login): Observable<LoginResponse> {
        return this.http
            .post<LoginResponse>(
                `${this.apiUrl}/signin`,
                data,
                this.httpOptions
            )
            .pipe(
                tap((res) => this.log(res)),
                catchError((err) => this.handleError(err, []))
            );
    }

    setCookie(token: string): void {
        this.cookieService.set('token', token);
        console.log(this.cookieService.get('token'));
    }

    private log(response: any) {
        console.log(response);
    }

    private handleError(error: Error, errorValue: any) {
        console.log(error);
        return of(errorValue);
    }
}
