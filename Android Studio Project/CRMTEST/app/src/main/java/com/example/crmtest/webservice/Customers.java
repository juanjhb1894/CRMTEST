package com.example.crmtest.webservice;

import android.os.StrictMode;
import android.util.Log;

import com.example.crmtest.datamodel.CustomerData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Customers {

    String LOG_TAG = "Customers";

    public ArrayList<CustomerData> getAllCustomers() throws IOException, JSONException {

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ArrayList<CustomerData> customers = new ArrayList<CustomerData>();
        String SERVICE_URL= new BaseParameters().baseURL + "customers/list";
        Log.e(LOG_TAG,SERVICE_URL);
        HttpURLConnection conn = null;
        final StringBuilder json = new StringBuilder();
        try {
            // Connect to the web service
            URL url = new URL(SERVICE_URL);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Read the JSON data into the StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                json.append(buff, 0, read);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to service", e);
            throw new IOException("Error connecting to service", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        Log.e(LOG_TAG,json.toString());
        JSONArray jsonArray = new JSONArray(json.toString());
        for (int i = 0; i < jsonArray.length(); i++) {
            // Create a marker for each city in the JSON data.
            JSONObject jsonObj = jsonArray.getJSONObject(i);

            customers.add( new CustomerData(jsonObj.getInt("id"), jsonObj.getString("email"),
                    jsonObj.getString("phone"), jsonObj.getString("name"),
                    jsonObj.getInt("status"), jsonObj.getString("created_at")));
        }

        return customers;
    }

    public ArrayList<CustomerData> getFilteredCustomers(String parameter) throws IOException, JSONException {

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ArrayList<CustomerData> customers = new ArrayList<CustomerData>();
        String SERVICE_URL= new BaseParameters().baseURL + "customers/list";
        Log.e(LOG_TAG,SERVICE_URL);
        HttpURLConnection conn = null;
        final StringBuilder json = new StringBuilder();
        try {
            // Connect to the web service
            URL url = new URL(SERVICE_URL);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Read the JSON data into the StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                json.append(buff, 0, read);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to service", e);
            throw new IOException("Error connecting to service", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        Log.e(LOG_TAG,json.toString());
        JSONArray jsonArray = new JSONArray(json.toString());
        for (int i = 0; i < jsonArray.length(); i++) {
            // Create a marker for each city in the JSON data.
            JSONObject jsonObj = jsonArray.getJSONObject(i);

            if(jsonObj.getString("email").toLowerCase().contains(parameter.toLowerCase()) || jsonObj.getString("name").toLowerCase().contains(parameter.toLowerCase()) ||
                    jsonObj.getString("phone").toLowerCase().contains(parameter.toLowerCase()))
            {
                customers.add( new CustomerData(jsonObj.getInt("id"), jsonObj.getString("email"),
                        jsonObj.getString("phone"), jsonObj.getString("name"),
                        jsonObj.getInt("status"), jsonObj.getString("created_at")));
            }
        }

        return customers;
    }
}
