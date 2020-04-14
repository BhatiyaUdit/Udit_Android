package covid19.com.ub61555.covidinfo.DataSync.GetFlag;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetFlagAsyncTask extends AsyncTask<Object, Object , InputStream> {
    private static final String FLAG_SAMPLE_URL = "https://www.worldometers.info/img/flags/us-flag.gif";
    public GetFlagInterface getFlagInterface = null;

    @Override
    protected InputStream doInBackground(Object... objects) {
        InputStream response = null;
        try {
            URL covidURL =  new URL(FLAG_SAMPLE_URL);
            response = getDataFromURL(covidURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return response;
    }

    private InputStream getDataFromURL(URL covidURL) {
        InputStream response = null;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) covidURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            response = urlConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getFlagInterface.getFlagOnPostExecute(response);
        return response;

    }


    @Override
    protected void onPostExecute(InputStream inputStream) {
        getFlagInterface.getFlagOnPostExecute(inputStream);
    }
}
