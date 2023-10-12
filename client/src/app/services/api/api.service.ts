import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { CookieService } from '../cookie/cookie.service';

@Injectable({
    providedIn: 'root',
})
export class ApiService {
    private apiUrl: string = 'http://localhost:8080/api';
    constructor(
        private http: HttpClient,
        private cookieService: CookieService
    ) {}

    get<T>(path: string): Observable<T> {
        return this.http.get<T>(this.apiUrl + path, this.headers()).pipe(
            tap((res) => this.log(res)),
            catchError((err) => this.handleError(err, err.error.message))
        );
    }

    post<T, DataType>(
        path: string,
        data: DataType,
        disableContentType?: boolean
    ): Observable<T> {
        console.log(data);
        return this.http
            .post<T>(this.apiUrl + path, data, this.headers(disableContentType))
            .pipe(
                tap((res) => this.log(res)),
                catchError((err) => this.handleError(err, err.error.message))
            );
    }

    put<T, DataType>(
        path: string,
        data: DataType,
        disableContentType?: boolean
    ): Observable<T> {
        return this.http
            .put<T>(this.apiUrl + path, data, this.headers(disableContentType))
            .pipe(
                tap((res) => this.log(res)),
                catchError((err) => this.handleError(err, err.error.message))
            );
    }

    private headers(disableContentType?: boolean) {
        const token = this.cookieService.getCookie();

        return {
            headers: new HttpHeaders({
                ...(disableContentType
                    ? {}
                    : { 'Content-Type': 'application/json' }),
                Authorization: `Bearer ${token}`,
            }),
        };
    }

    private log(response: any) {
        console.log(response);
    }

    private handleError(error: any, message: string) {
        console.error(`Erreur : ${message}`, error);

        // Renvoyer un observable d'erreur
        return throwError(() => message);
    }
}
