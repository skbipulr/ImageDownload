package com.example.user.imagedownload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

      private static final String IMAGE_URL = "https://tineye.com/images/widgets/mona.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // new DownloadTask().execute(IMAGE_URL);
        ImageView imageView = findViewById(R.id.imageView);
        Picasso.with(this).load(IMAGE_URL).into(imageView);
    }

    private class DownloadTask extends AsyncTask<String,Void,Bitmap>{


        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL imateUrl = new URL(strings[0]);
                HttpURLConnection httpURLConnection =(HttpURLConnection) imateUrl.openConnection();
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();

              return BitmapFactory.decodeStream(inputStream);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ((ImageView)findViewById(R.id.imageView)).setImageBitmap(bitmap);
        }
    }
}
