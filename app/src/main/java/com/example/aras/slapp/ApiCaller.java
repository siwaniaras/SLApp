package com.example.aras.slapp;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiCaller extends AsyncTask<String , Void ,JSONObject> {


        String server_response;
        MainActivity mainActivity;
        String urlActivity;
        //ArrayList stationNames = new ArrayList();

        ApiCaller(MainActivity main, String url){
            mainActivity = main;
            urlActivity = url;


        }

        @Override
        protected JSONObject doInBackground(String... strings) {

            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urlActivity);

                urlConnection = (HttpURLConnection) url.openConnection();

                int responseCode = urlConnection.getResponseCode();

               if(responseCode == HttpURLConnection.HTTP_OK){
                  server_response = readStream(urlConnection.getInputStream());
                    Log.v("CatalogClient", server_response);
                    return new JSONObject(server_response);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            catch (JSONException e) {
            }
           return null;
      }

        @Override
        protected void onPostExecute(JSONObject s) {
            super.onPostExecute(s);

            Log.d("Response", "" + server_response);

            int [] stationId = null;
            String[] stationNames = null;
            try {
                JSONArray stations = s.getJSONArray("ResponseData");
                stationNames = new String[stations.length()];
                stationId = new int[stations.length()];
                for (int i = 0; i < stations.length() ; i++) {
                    JSONObject station = stations.getJSONObject(i);
                    stationNames[i] = station.getString("Name");
                    stationId[i] = station.getInt("SiteId");
                }
            } catch (JSONException e) {


            }

            mainActivity.setupList(stationNames, stationId);

        }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

}
