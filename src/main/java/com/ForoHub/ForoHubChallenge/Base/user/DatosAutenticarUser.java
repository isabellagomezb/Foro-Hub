package com.ForoHub.ForoHubChallenge.Base.user;

public record DatosAutenticarUser(
        String login,
        String password
) {
    @Override
    public String login() {
        return login;
    }

    @Override
    public String password() {
        return password;
    }
}
