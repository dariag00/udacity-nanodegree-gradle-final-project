package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.builditbigger.JokeActivity;
import com.example.joketellingapp.JokeTellingApp;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointAsyncTask extends AsyncTask<Context, Void, String> {

    private MyApi myApiService = null;
    private Context context;

    private EndpointListener endpointListener;

    public EndpointAsyncTask(EndpointListener endpointListener){
        this.endpointListener = endpointListener;
    }

    @Override
    protected String doInBackground(Context ... contexts) {
        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://192.168.43.52:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        context = contexts[0];

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            Log.e(EndpointAsyncTask.class.getName(), "Error: ", e);
            return null;
        }

    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
        if(s != null && !s.isEmpty()){
            endpointListener.onTaskCompleted(s);
        } else {
            endpointListener.onTaskCompleted("");
        }
    }


    public interface EndpointListener {
        void onTaskCompleted(String obtainedJoke);
    }

}
