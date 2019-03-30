package com.enesusta.webservice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button b;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.button);
        tv1 = findViewById(R.id.text);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.setText("sunucu cevabi");
                new arkaPlan().execute("http://192.168.0.27:3000/listele");
            }
        });

    }
    class arkaPlan extends AsyncTask<String,String,String> {

        protected String doInBackground(String... params) {

            // ilk elemani sunucu adresi URL

            HttpURLConnection urlConnection = null;
            BufferedReader bufferedReader = null;

            try {

                URL url = new URL(params[0]);// http://127.0.0.1:3000/add
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect(); // boylece baglandik

                InputStream inputStream = urlConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                String dosya = "";

                while ((line = bufferedReader.readLine()) != null) {
                    Log.d("line =", line);
                    dosya += line;
                }

                return dosya;


            } catch (Exception e) {
                e.printStackTrace();
            }

            return "hata";
        }

        protected void onPostExecute(String s) {
            Log.d("postExecute'a gelen",s);
            try {
                JSONObject jo = new JSONObject(s);
                tv1.setText(jo.getString("k3"));


            }catch(Exception e) {
                e.printStackTrace();
            }


        }

    }
}
