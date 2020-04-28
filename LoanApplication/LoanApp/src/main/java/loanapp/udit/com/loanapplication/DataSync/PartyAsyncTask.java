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
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;

import loanapp.udit.com.loanapplication.CommonApplicationUtil;

public class PartyAsyncTask extends AsyncTask<String, Void, String> {

    public PartyAsyncTaskInterface partyAsyncTaskInterface;
    public static final String SEARCH_PARTY_INPUT_JSON = "{\"InputParameters\":{\"P_USER_ID\":\"PU00144\",\"P_GEO_CNTRY_ID\":9,\"SEARCH_PARAM\":[{\"PRTY_ID\":null,\"KYC\":\"%%\",\"PRTY_CTGRY_TYP_ID\":null,\"PARTY_FULL_NAME\":null,\"PARTY_CONTACT\":null,\"INDV_BIRTH_DT\":null,\"GEO_REGN_NM\":null,\"CREATED_BY\":\"8F01012\"}]}}";
    HttpURLConnection urlConnection = null;

    public PartyAsyncTask() {
        CommonApplicationUtil.ErrorMessage="";
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        try {
            URL url = new URL(strings[0]);
            response = getDataFromAPI(url, SEARCH_PARTY_INPUT_JSON);
        } catch (MalformedURLException e) {
            CommonApplicationUtil.ErrorMessage="URL was malformed";
            Log.e("Error While Reading the URL ", e.toString());
        }
        return response;
    }

    private String getDataFromAPI(URL url, String RequestJSON) {
        String response = "";
        InputStream ip = null;
        try {
            String authorization= populateBasicAuth();
            urlConnection = (HttpURLConnection) url.openConnection();
            addPropertiesToUrlConnection(authorization);
            urlConnection.connect();
            writeJSONRequestToUrlConnection(RequestJSON);

            Log.i("STATUS", String.valueOf(urlConnection.getResponseCode()));
            Log.i("MSG", urlConnection.getResponseMessage());

            response = getResponseFromStream(urlConnection.getInputStream());

            Log.i("RESPONSE ", response);

            if (urlConnection.getResponseCode() != 200) {
                Log.e("Error while getting response from the URL ", urlConnection.getResponseMessage());
                CommonApplicationUtil.ErrorMessage=urlConnection.getResponseMessage();
            }
        } catch (IOException e) {
            Log.e("Error While opening the connection ", e.toString());
            e.printStackTrace();
            CommonApplicationUtil.ErrorMessage= "IO Exception Occured";
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return response;
    }

    private String getResponseFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
            inputStreamReader.close();
        }
        return output.toString();
    }

    private void writeJSONRequestToUrlConnection(String RequestJSON) throws IOException {
        DataOutputStream os = new DataOutputStream(urlConnection.getOutputStream());
        os.writeBytes(RequestJSON);
        os.flush();
        os.close();
    }

    private void addPropertiesToUrlConnection(String authorization) throws ProtocolException {
        urlConnection.setRequestProperty("Authorization", authorization);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
    }

    private String populateBasicAuth() {
        String auth = CommonApplicationUtil.ServiceUser + ":" + CommonApplicationUtil.ServicePassword;
        String authString = "Basic " + Base64.encodeToString((auth).getBytes(), Base64.NO_WRAP);
        return authString;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        partyAsyncTaskInterface.getDataPostExecute(s);
    }
}
