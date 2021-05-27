package com.example.dogdog2;

import com.example.dogdog2.Home.Friend;
import com.example.dogdog2.Home.Friends;
import com.example.dogdog2.Profile.Organization;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LoveAndPetsApi {

    @GET("products")
    Call<List<Friends>> getFriends(@Query("page") int petsPage, @Query("per_page") Integer perPage);

    @GET("brands")
    Call<List<Organization>> getOrganizations();

    @GET("products/{id}")
    Call<Friend> getFriend(@Path("id") long petId);

}
