export interface Auth {}

export interface Register {
    name: string | null | undefined;
    email: string | null | undefined;
    password: string | null | undefined;
    role: string;
}

export interface RegisterResponse {
    message: string;
}

export interface Login {
    email: string | null | undefined;
    password: string | null | undefined;
}

export interface LoginResponse {
    token: string;
}
