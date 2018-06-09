package com.vivy.test.searchmydoctor.fetcher;

import io.reactivex.disposables.Disposable;

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
    Disposable login(String username, String password);
}
