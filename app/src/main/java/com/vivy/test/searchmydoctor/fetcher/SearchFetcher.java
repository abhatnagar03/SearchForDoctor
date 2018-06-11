package com.vivy.test.searchmydoctor.fetcher;

import io.reactivex.disposables.Disposable;

/**
 * Interface for search doctor service.
 */
public interface SearchFetcher {
    /**
     * Search doctor by text
     * @param searchText filter text
     * @param lat current lat
     * @param longi current lang
     * @return list of Doctors
     */
    Disposable searchDoctorByText(String searchText, Double lat, Double longi);

    /**
     * Search all doctors near me
     * @param lat current lat
     * @param lng current lang
     * @return list of Doctors
     */
    Disposable searchAllDoctors(Double lat, Double lng);
}
