package com.pts3.sport.network;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class VolleyHTTPSingleton {
    private static VolleyHTTPSingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private VolleyHTTPSingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

    }

    public static synchronized VolleyHTTPSingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyHTTPSingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {

            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
