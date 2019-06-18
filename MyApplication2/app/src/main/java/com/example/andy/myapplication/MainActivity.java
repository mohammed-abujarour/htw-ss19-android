package com.example.andy.myapplication;
// code source: https://www.tutorialspoint.com/android-asynctask-example-and-explanation

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    URL ImageUrl = null;
    InputStream is = null;
    Bitmap bmImg = null;
    ImageView imageView= null;
    ProgressDialog p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.asyncTask);
        Log.d("Main","Main method is working ...");
        imageView=findViewById(R.id.image);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskExample asyncTask=new AsyncTaskExample();
                asyncTask.execute("https://images.unsplash.com/photo-1560344319-89a55a8728d1");
            }
        });


    }

    /** source: https://www.vogella.com/tutorials/AndroidBackgroundProcessing/article.html
     * To use AsyncTask you must subclass it.
     * The parameters are the following AsyncTask <TypeOfVarArgParams, ProgressValue, ResultValue>.
     *
     * An AsyncTask is started via the execute() method.
     * This execute() method calls the doInBackground() and the onPostExecute() method.
     *
     * TypeOfVarArgParams is passed into the doInBackground() method as input.
     * ProgressValue is used for progress information and ResultValue must be returned from doInBackground() method.
     * This parameter is passed to onPostExecute() as a parameter.
     *
     * The doInBackground() method contains the coding instruction which should be performed in a background thread.
     * This method runs automatically in a separate Thread.
     *
     * The onPostExecute() method synchronizes itself again with the user interface thread and allows it to be updated.
     * This method is called by the framework once the doInBackground() method finishes.
     */
    private class AsyncTaskExample extends AsyncTask<String, String, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p=new ProgressDialog(MainActivity.this);
            p.setMessage("Please wait...It is downloading");
           // p.setCancelable(false);
            p.show();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {

                ImageUrl = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) ImageUrl
                        .openConnection();
                conn.setDoInput(true);
                conn.connect();
                is = conn.getInputStream();

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                bmImg = BitmapFactory.decodeStream(is, null, options);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return bmImg;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(imageView!=null) {
                p.hide();
                imageView.setImageBitmap(bitmap);
            }else {
                p.show();
            }
        }
    }
}

