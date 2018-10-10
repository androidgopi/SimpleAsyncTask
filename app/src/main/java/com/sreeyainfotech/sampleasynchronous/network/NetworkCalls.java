package com.sreeyainfotech.sampleasynchronous.network;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class NetworkCalls {
    public static String getRequest(String url, Context context) {

        String response = "";

        try {
            URL githubEndpoint = new URL(url);

            HttpURLConnection myConnection = (HttpURLConnection) githubEndpoint.openConnection();
            myConnection.setRequestMethod("GET");
            myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
            myConnection.setRequestProperty("Accept", "application/vnd.github.v3+json");

            if (myConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {//HTTP_OK code is 200
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader streamReader = new BufferedReader(responseBodyReader);
                StringBuilder responseStrBuilder = new StringBuilder();

                //get JSON String
                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                myConnection.disconnect();
                response = responseStrBuilder.toString();
            } else {
                Log.d("Main", "error in connection");
                return "";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static String postRequest(Context context, String url, JSONObject postData) {

        String response = null;

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url_obj = new URL(url);
            urlConnection = (HttpURLConnection) url_obj.openConnection();
            urlConnection.setDoOutput(true);
            // is output buffer writter
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            //set headers and method
            Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
            writer.write(String.valueOf(postData));
            // json data
            writer.close();
            InputStream inputStream = urlConnection.getInputStream();
            //input stream
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }

            int responseCode=urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                buffer.append(inputLine + "\n");
                if (buffer.length() == 0) {
                    // Stream was empty. No point in parsing.
                    return null;
                }
            }
            response = buffer.toString();

            inputStream.close();
            //send to post execute
            return response;
            }
            else {
                return "false : " + responseCode;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("error_in Post", "Error closing stream", e);
                }
            }
        }
        return null;
    }
}
