package com.jorgesys.androidpicassocache;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    private static String images[] = {"https://static01.nyt.com/images/2018/07/10/science/10SCI-MITO1/10SCI-MITO1-master1050.jpg","https://static01.nyt.com/images/2018/07/09/lens/09bdc1/09bdc1-master1050.jpg","https://static01.nyt.com/images/2018/07/09/lens/09bdc11/09bdc11-master1050.jpg","https://static01.nyt.com/images/2018/07/09/lens/09bdc12/09bdc12-master1050.jpg",
            "https://static01.nyt.com/images/2018/07/09/lens/09bdc8/09bdc8-master1050.jpg","https://static01.nyt.com/images/2018/07/09/lens/09bdc6/09bdc6-master1050.jpg"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView imageViewA = findViewById(R.id.imageViewA);
        TextView textViewA = findViewById(R.id.textViewA);
        loadImage(images[0], imageViewA,textViewA);

        ImageView imageViewB = findViewById(R.id.imageViewB);
        TextView textViewB = findViewById(R.id.textViewB);
        loadImage(images[1], imageViewB,textViewB);

        ImageView imageViewC = findViewById(R.id.imageViewC);
        TextView textViewC = findViewById(R.id.textViewC);
        loadImage(images[2], imageViewC,textViewC);

        ImageView imageViewD = findViewById(R.id.imageViewD);
        TextView textViewD = findViewById(R.id.textViewD);
        loadImage(images[3], imageViewD,textViewD);

        ImageView imageViewE = findViewById(R.id.imageViewE);
        TextView textViewE = findViewById(R.id.textViewE);
        loadImage(images[4], imageViewE,textViewE);

    }


    private void loadImage(final String urlImage, final ImageView imageView, final TextView textView){

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this,Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);

        /*Red from network.
        Green from cache memory.
        Blue from disk memory.*/

        Picasso.get().load(urlImage).networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "Load image from caché! " + urlImage);
                textView.setText("From Caché:\n " + Uri.parse(urlImage).getLastPathSegment());
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG, "onError() " + e.getMessage());
                Log.i(TAG, "Try to load image from internet! " + urlImage);
                textView.setText("From internet:\n " + Uri.parse(urlImage).getLastPathSegment());
                //Can´t find image in cache, load from internet.
                Picasso.get().load(urlImage).into(imageView);

            }
        });

    }


}
