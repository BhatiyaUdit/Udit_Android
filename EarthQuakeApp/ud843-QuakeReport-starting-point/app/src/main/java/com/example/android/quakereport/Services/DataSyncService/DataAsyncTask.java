package com.example.android.quakereport.Services.DataSyncService;

import android.os.AsyncTask;
import android.widget.ListView;

import com.example.android.quakereport.Adapter.CustomEarthQuakeAdapter;
import com.example.android.quakereport.Bean.EarthQuakeBean;
import com.example.android.quakereport.CommonApplication;
import com.example.android.quakereport.EarthquakeActivity;
import com.example.android.quakereport.QueryUtils;
import com.example.android.quakereport.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Properties;


