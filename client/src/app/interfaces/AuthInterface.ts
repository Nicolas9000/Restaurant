export interface Auth {

}

export interface Register {

}

export interface Login {
    email: string | null | undefined;
    password: string | null | undefined;
}

export interface LoginResponse {
    token: string;
}