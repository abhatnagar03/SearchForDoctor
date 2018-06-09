package com.vivy.test.searchmydoctor.fetcher;

/**
 * Interface for search doctor service.
 */
public interface SearchFetcher {
    void searchDoctorByName(String docName, Float lat, Float longi);
    void searchAllDoctors(Float lat, Float lng);
}
