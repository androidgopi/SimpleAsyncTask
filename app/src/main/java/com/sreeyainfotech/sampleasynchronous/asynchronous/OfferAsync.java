package com.sreeyainfotech.sampleasynchronous.asynchronous;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.sreeyainfotech.sampleasynchronous.interfaces.OfferAsyncResponse;
import com.sreeyainfotech.sampleasynchronous.network.NetworkCalls;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kstl on 16-04-2018.
 */

public class OfferAsync extends AsyncTask<Void, Void, JSONObject> {

    Context mContext;
    String url;
    JSONObject jsonObject;
    private ProgressDialog dialog;
    private String response = "";
    private JSONObject obj;
    public OfferAsyncResponse delegate = null;

    public OfferAsync(Context mContext, String url) {
        this.mContext = mContext;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialog = new ProgressDialog(mContext);
        dialog.setMessage("Authenticating...");
        dialog.setCancelable(false);
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {

        response = NetworkCalls.getRequest(url, mContext);
        try {
            obj = new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);

        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        delegate.offerAsyncResponce(result);
    }
}
