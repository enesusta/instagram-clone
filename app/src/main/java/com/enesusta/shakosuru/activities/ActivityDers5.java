package com.enesusta.shakosuru.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.enesusta.shakosuru.R;
import com.enesusta.shakosuru.controller.webservice.Comment;
import com.enesusta.shakosuru.controller.webservice.Post;
import com.enesusta.shakosuru.controller.webservice.Client;
import com.enesusta.shakosuru.controller.webservice.RestAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityDers5 extends AppCompatActivity {

    private TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ders5);
        textViewResult = findViewById(R.id.text_view_result);


        requestPost();
        //requestComments();

    }

    public void requestPost() {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "1");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");


        Retrofit retrofit;
        RestAPI restAPI;
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        restAPI = retrofit.create(RestAPI.class);

        Call<List<Post>> call = restAPI.getPosts("/posts/3");

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code : " + response.code());
                    return;

                }

                List<Post> posts = response.body();

                for (Post post : posts) {

                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID : " + post.getId() + "\n";
                    content += "Title :" + post.getTitle() + "\n";
                    content += "Text : " + post.getTitle() + "\n\n";

                    textViewResult.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });


    }

    public void requestComments() {
        Client client = new Client();
        Call<List<Comment>> call = client.getRestAPI().getComments(2);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code : " + response.code());
                    return;

                }

                List<Comment> comments = response.body();

                for (Comment comment : comments) {

                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "Post ID : " + comment.getId() + "\n";
                    content += "Name :" + comment.getName() + "\n";
                    content += "Email : " + comment.getEmail() + "\n";
                    content += "Text :" + comment.getText() + "\n\n";

                    textViewResult.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }


}
