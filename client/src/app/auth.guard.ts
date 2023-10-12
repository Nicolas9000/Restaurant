import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth/auth.service';
import { Inject, inject } from '@angular/core';

export const authGuard: CanActivateFn = () => {
    const router = Inject(Router);
    const authService = inject(AuthService);

    if (authService.isLoggedIn()) {
        return true;
    }

    router.navigate['/login'];
    return false;
};
