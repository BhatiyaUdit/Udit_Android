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
import android.widget.ListView;
import com.example.android.quakereport.Adapter.CustomEarthQuakeAdapter;
import com.example.android.quakereport.Bean.EarthQuakeBean;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    public static final String USGS_SAMPLE_DATA_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-01-31&minmag=6&limit=10";
    public static final String USGS_LATEST_TEN_EQ_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        DataAsyncTask asyncTask = new DataAsyncTask();
        asyncTask.execute(USGS_LATEST_TEN_EQ_URL);

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


    public class DataAsyncTask extends AsyncTask<String,Void,ArrayList<EarthQuakeBean>> {

        @Override
        protected ArrayList<EarthQuakeBean> doInBackground(String... strings) {
            if(strings.length==0){
                return null;
            }
            ArrayList<EarthQuakeBean> earthQuakeBeans = QueryUtils.getEarthQuakeData(strings[0]);
            return earthQuakeBeans;
        }

        @Override
        protected void onPostExecute(ArrayList<EarthQuakeBean> earthQuakeBeans) {
            super.onPostExecute(earthQuakeBeans);
            if (earthQuakeBeans != null && earthQuakeBeans.size() > 0) {
                updateUI(earthQuakeBeans);
            } else {
                return;
            }
        }
    }
}
