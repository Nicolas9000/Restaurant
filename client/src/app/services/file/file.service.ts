import { Injectable } from '@angular/core';
import { ApiService } from '../api/api.service';
import { Observable } from 'rxjs';
import { ApiResponse } from 'src/app/interfaces/ApiResponseInterface';

@Injectable({
    providedIn: 'root',
})
export class FileService {
    constructor(private apiService: ApiService) {}

    private apiUrl = '/file';

    uploadImage(data: FormData): Observable<ApiResponse<string>> {
        return this.apiService.post<ApiResponse<string>, FormData>(
            `${this.apiUrl}/save`,
            data,
            true
        );
    }
}
