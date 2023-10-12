import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from 'src/app/interfaces/ApiResponseInterface';
import { Dish } from 'src/app/interfaces/DishInterface';
import { ApiService } from 'src/app/services/api/api.service';

@Injectable({
    providedIn: 'root',
})
export class DishService {
    constructor(private apiService: ApiService) {}
    apiUrl: string = '/dish';

    createDish(data: Dish): Observable<ApiResponse<Dish>> {
        return this.apiService.post<ApiResponse<Dish>, Dish>(this.apiUrl, data);
    }

    getDishs(): Observable<ApiResponse<Dish[]>> {
        return this.apiService.get<ApiResponse<Dish[]>>(`${this.apiUrl}/1`);
    }
}
