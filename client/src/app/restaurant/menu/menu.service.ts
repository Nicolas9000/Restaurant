import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from 'src/app/interfaces/ApiResponseInterface';
import { Menu } from 'src/app/interfaces/MenuInterface';
import { ApiService } from 'src/app/services/api/api.service';

@Injectable({
    providedIn: 'root',
})
export class MenuService {
    constructor(private apiService: ApiService) {}

    private apiUrl: string = '/menu';

    createMenu(data: Menu): Observable<ApiResponse<Menu>> {
        return this.apiService.post<ApiResponse<Menu>, Menu>(this.apiUrl, data);
    }

    getMenus(): Observable<ApiResponse<Menu[]>> {
        return this.apiService.get<ApiResponse<Menu[]>>(`${this.apiUrl}/1`);
    }

    
}
