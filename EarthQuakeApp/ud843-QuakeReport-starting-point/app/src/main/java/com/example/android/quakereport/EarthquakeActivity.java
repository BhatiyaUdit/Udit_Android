/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.quakereport.Adapter.CustomEarthQuakeAdapter;
import com.example.android.quakereport.Bean.EarthQuakeBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        DataAsyncTask asyncTask = new DataAsyncTask();
        asyncTask.execute();

                //new ArrayList<>();
        /*EarthQuakeBean earthQuakeBean = new EarthQuakeBean();
        earthQuakeBean.setMagnitude(7.1);
        earthQuakeBean.setLocation("San Francisco");
        earthQuakeBean.setDate("12-Apr-2020");

        earthQuakeBeans.add(earthQuakeBean);
        earthQuakeBeans.add(earthQuakeBean);
        earthQuakeBeans.add(earthQuakeBean);
        earthQuakeBeans.add(earthQuakeBean);
        earthQuakeBeans.add(earthQuakeBean);
        earthQuakeBeans.add(earthQuakeBean);
        earthQuakeBeans.add(earthQuakeBean);*/



      /*  // Create a fake list of earthquake locations.
        ArrayList<String> earthquakes = new ArrayList<>();
        earthquakes.add("San Francisco");
        earthquakes.add("London");
        earthquakes.add("Tokyo");
        earthquakes.add("Mexico City");
        earthquakes.add("Moscow");
        earthquakes.add("Rio de Janeiro");
        earthquakes.add("Paris");*/

        // Find a reference to the {@link ListView} in the layout


        // Create a new {@link ArrayAdapter} of earthquakes
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, earthquakes);*/

        // Set the adapter on the {@link ListView}
         // so the list can be populated in the user interface



    }

    public void updateUI(ArrayList<EarthQuakeBean> earthQuakeBeans){
        CustomEarthQuakeAdapter customEarthQuakeAdapter = new CustomEarthQuakeAdapter(this, R.layout.earthquake_list, earthQuakeBeans);
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        earthquakeListView.setAdapter(customEarthQuakeAdapter);

    }


    public class DataAsyncTask extends AsyncTask<Object,Object,Object> {

        public static final String USGS_SAMPLE_DATA_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-01-31&minmag=6&limit=10";

        @Override
        protected Object doInBackground(Object[] objects) {
            String response="";
            try {
                URL url = new URL(USGS_SAMPLE_DATA_URL);
                response= makeHTTPRequest(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return response;
        }

        private String makeHTTPRequest(URL url) {
            String response="";
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                response = readFromStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }

            return response;
        }

        private  String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if(o == null){
                return;
            }else{
                ArrayList<EarthQuakeBean> earthQuakeBeans = QueryUtils.extractEarthquakes(o.toString());
                updateUI(earthQuakeBeans);
            }
        }
    }
}
