package com.enesusta.shakosuru.controller.webservice;

import com.enesusta.shakosuru.controller.webservice.Comment;
import com.enesusta.shakosuru.controller.webservice.Post;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RestAPI {

    @GET("/posts")
    Call<List<Post>> getPosts();
/*
    @GET("/posts")
    Call<List<Post>> getPosts(@Query("userId") int userId);
    */

    // posts?userId=4&_sort=id&_order=desc
    @GET("/posts")
    Call<List<Post>> getPosts(
            @Query("userId") int userId,
            @Query("_sort") String sort,
            @Query("_order") String order
    );

    @GET
    Call<List<Post>> getPosts(@Url String url);


    @GET("/posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> parameters);


    @GET("/posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    @GET
    Call<List<Comment>> getComments(@Url String url);


}
