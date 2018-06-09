package com.vivy.test.searchmydoctor.fetcher;

/**
 * Interface for login service.
 */
public interface LoginFetcher {
    /**
     *  Login the user using the below parameters
     *
     * @param username Username
     * @param password Password
     */
    void login(String username, String password);
}
