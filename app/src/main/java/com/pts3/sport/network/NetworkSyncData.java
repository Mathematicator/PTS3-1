package com.pts3.sport.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import eu.tesseractsoft.mintimedialog.MinTimeDialog;

/*
Cette classe permet la synchronisation des données
    -Récupérer les données depuis la base de donnée externe (en utilisant NetworkGetData)
    -Sauvegarder les données depuis la base de donnée interne (en utilisant NetworkSaveData);
 */
public class NetworkSyncData extends BroadcastReceiver {

    private Context context;
    private boolean requestSuccess;
    private MinTimeDialog progressDialog;
    private boolean toastMessage = false;
    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        // si il y a une connexion
        if (activeNetwork != null) {
            // connecté en wifi ou mobile
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {

                progressDialog = new MinTimeDialog(context);
                progressDialog.setMinShownTimeMs(2000);
                progressDialog.setMessage("Synchronisation des données en cours depuis le serveur à distance...");
                progressDialog.show();


                //-Sauvegarder les données depuis la base de donnée interne (en utilisant NetworkSaveData);

                NetworkSaveData savedata = new NetworkSaveData(context);

                // -Récupérer les données depuis la base de donnée externe (en utilisant NetworkGetData)

                NetworkGetData getdata = new NetworkGetData(context);


                VolleyHTTPSingleton.getInstance(context).getRequestQueue().addRequestFinishedListener(new RequestQueue.RequestFinishedListener<String>() {
                    @Override
                    public void onRequestFinished(Request<String> request) {
                        if (progressDialog !=  null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                });
                progressDialog.setMinTimeReachedListener(new MinTimeDialog.MinTimeReachedListener(){
                    @Override
                    public void onMinTimeReached(long totalMinShownTime) {
                        if(!toastMessage ) {
                            toast("Données synchronisées avec succès");
                            toastMessage = true;
                        }
                    }
                });

            }

        }
    }

    private void toast(String text) {

        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

    }
}
