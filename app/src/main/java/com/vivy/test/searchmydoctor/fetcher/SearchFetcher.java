package com.vivy.test.searchmydoctor.fetcher;

import io.reactivex.disposables.Disposable;

/**
 * Interface for search doctor service.
 */
public interface SearchFetcher {
    Disposable searchDoctorByName(String docName, Double lat, Double longi);
    Disposable searchAllDoctors(Double lat, Double lng);
}
