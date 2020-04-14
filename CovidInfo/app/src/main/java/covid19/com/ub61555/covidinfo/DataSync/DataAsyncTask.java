package covid19.com.ub61555.covidinfo.DataSync;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class DataAsyncTask extends AsyncTask<Object,Object,String> {

    public AsyncTaskInterface taskInterface;
    public String COVID_SAMPLE_DATA_URL = null;

    Context mContext;


    public DataAsyncTask(Context context, String url){
        mContext = context;
        COVID_SAMPLE_DATA_URL = url;
    }


    @Override
    protected String doInBackground(Object... objects) {
        String response = "";
        try {
            URL covidURL =  new URL(COVID_SAMPLE_DATA_URL);
            response = getDataFromCovidAPI(covidURL);
        } catch (MalformedURLException e) {
            Log.e("Error While Reading the URL " , e.toString());
        }
        return response;
    }

    private String getDataFromCovidAPI(URL covidURL) {
        String response ="";
        HttpURLConnection urlConnection = null;
        InputStream ip = null;
        try {
            urlConnection= (HttpURLConnection) covidURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(20000);
            urlConnection.connect();
            ip = urlConnection.getInputStream();
            response = readFromStream(ip);

            if(urlConnection.getResponseCode()!=200) {
                Log.e("Error while getting response from the URL ", urlConnection.getResponseMessage());
            }
        } catch (IOException e) {
            Log.e("Error While opening the connection " , e.toString());
        }finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
        }

        return  response;
    }

    private String readFromStream(InputStream inputStream) throws IOException{
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
    protected void onPostExecute(String o) {

        taskInterface.getDataPostExecute(o.toString());
    }
}
