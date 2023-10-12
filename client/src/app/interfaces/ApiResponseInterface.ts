export interface ApiResponse<T> {
    message: string;
    httpStatus: string;
    data: T
}