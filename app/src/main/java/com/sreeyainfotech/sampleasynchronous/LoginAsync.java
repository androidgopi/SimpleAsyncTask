package com.sreeyainfotech.sampleasynchronous;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kstl on 16-04-2018.
 */

public class LoginAsync extends AsyncTask<Void ,Void,JSONObject> {

    Context mContext;
    String url;
    JSONObject jsonObject;
    private ProgressDialog dialog;
    private String response="";
    private JSONObject obj;
    public LoginAsyncResponse delegate=null;

    public LoginAsync(Context mContext, String url, JSONObject jsonObject){
        this.mContext=mContext;
        this.url=url;
        this.jsonObject=jsonObject;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialog = new ProgressDialog(mContext);
        dialog.setMessage("Authenticating...");
        dialog.setCancelable(false);
        if(!dialog.isShowing()){
            dialog.show();
        }
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {

        response = NetworkCalls.postRequest(mContext,url, jsonObject);
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

        if(dialog.isShowing()){
            dialog.dismiss();
        }
        delegate.loginAsyncResponse(result);
    }
}
