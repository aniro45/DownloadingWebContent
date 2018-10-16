package com.example.admin.downloadingwebcontent;

import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    TextView textView1;
    TextView textView2;

    public class TaskDownload extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... urls) {

            Log.i("URL", urls[0]);

            String result = "";
            URL url;
            HttpURLConnection urlConnection;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char currentCharacterInHtmlSource = (char) data;

                    result = result + currentCharacterInHtmlSource;

                    data = reader.read();

                }

                return result;

            } catch (IOException e) {

                e.printStackTrace();

            }


            return "Failed";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        //ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.);

        TaskDownload task = new TaskDownload();
        String result = null;
        try {
            result = task.execute("https://www.ecowebhosting.co.uk/").get();

        } catch (ExecutionException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        Log.i("URL Content", result);

    }
}
