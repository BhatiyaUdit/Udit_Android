package loanapp.udit.com.loanapplication.DataSync;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PartyAsyncTask extends AsyncTask<String, Void, String> {

    public PartyAsyncTaskInterface partyAsyncTaskInterface;
    public static final String SEARCH_PARTY_INPUT_JSON = "{\n" +
            "  \"InputParameters\": {\n" +
            "    \"P_USER_ID\": \"PU00144\",\n" +
            "    \"P_GEO_CNTRY_ID\": 9,\n" +
            "    \"SEARCH_PARAM\": [\n" +
            "      {\n" +
            "        \"PRTY_ID\": null,\n" +
            "        \"KYC\": \"%%\",\n" +
            "        \"PRTY_CTGRY_TYP_ID\": null,\n" +
            "        \"PARTY_FULL_NAME\": null,\n" +
            "        \"PARTY_CONTACT\": null,\n" +
            "        \"INDV_BIRTH_DT\": null,\n" +
            "        \"GEO_REGN_NM\": null,\n" +
            "        \"CREATED_BY\":null\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        try {
            URL url = new URL(strings[0]);
            response = getDataFromAPI(url);
        } catch (MalformedURLException e) {
            Log.e("Error While Reading the URL ", e.toString());
        }
        return response;
    }

    private String getDataFromAPI(URL url) {
        String response = "";
        HttpURLConnection urlConnection = null;
        InputStream ip = null;
        try {
            String auth = "jd-lFs4Cm7nsAAcKsMlsp3uj6Zn" + ":" + "2e46a5130a5b19dbe1b622145cdb1d1d7ecb5cb6";
            String authString = "Basic " + Base64.encodeToString((auth).getBytes(), Base64.NO_WRAP);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Authorization", authString);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            Log.i("REQUEST", String.valueOf(urlConnection));
            urlConnection.connect();

            DataOutputStream os = new DataOutputStream(urlConnection.getOutputStream());
            os.writeBytes(SEARCH_PARTY_INPUT_JSON);

            os.flush();
            os.close();
            Log.i("STATUS", String.valueOf(urlConnection.getResponseCode()));
            Log.i("MSG", urlConnection.getResponseMessage());

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                response += line;
            }

            Log.i("RESPONSE ", response);
            if (urlConnection.getResponseCode() != 200) {
                Log.e("Error while getting response from the URL ", urlConnection.getResponseMessage());
            }
        } catch (IOException e) {
            Log.e("Error While opening the connection ", e.toString());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        partyAsyncTaskInterface.getDataPostExecute(s);
    }
}
