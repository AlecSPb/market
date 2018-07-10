package ru.tsystem.javaschool.ordinaalena.services.api;

public interface SecurityService {
    /**
     * Finding logged user.
     * @return User's email
     */
    String findLoggedInEmail();

    /**
     * Autologin.
     * @param email Email
     * @param password Password
     */
    void autoLogin(String email, String password);
}
