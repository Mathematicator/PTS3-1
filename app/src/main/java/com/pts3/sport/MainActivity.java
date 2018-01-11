package com.pts3.sport;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.pts3.sport.database.ProfesseurManager;
import com.pts3.sport.network.NetworkSyncData;




public class MainActivity extends AppCompatActivity{

    private EditText username,password;
    private Context context;
    private Button btn;

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Définition de la view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //synchronisé la connexion avec la base de donnée
        registerReceiver(new NetworkSyncData(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        //Assignation aux ID du XML
        username = (EditText) findViewById(R.id.input_user);
        password = (EditText) findViewById(R.id.input_password);

        username.setTextColor(Color.GRAY);
        password.setTextColor(Color.GRAY);

        final ProfesseurManager eleveManager = new ProfesseurManager(getApplicationContext());

        //Bouton valider : on envoie la requête quand on appuie dessus
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eleveManager.isLogin(username.getText().toString(), password.getText().toString())) {
                    // connexion effectué
                    ouvrirAccueil();
                }
                else {
                    //erreur de connexion
                    context = getApplicationContext();
                    CharSequence text = "Identifiant ou mot de passe incorrect";
                    int duration = Toast.LENGTH_SHORT;
                    username.setTextColor(Color.RED);
                    password.setTextColor(Color.RED);
                    toast = Toast.makeText(context, text, duration);
                    toast.show();

                }

            }
        });

        if (haveInternetConnection() == true)
        {
            Log.e("checkInternet","connecté !");
        }
        else
        {
            Log.e("checkInternet","Pas de connexion");
        }
    }

    public void ouvrirAccueil(){
        Intent displayActivity = new Intent(this,ChoixActivity.class);
        this.startActivity(displayActivity);
    }


    public boolean haveInternetConnection(){
        // Fonction haveInternetConnection : return true si connecté, return false dans le cas contraire
        NetworkInfo network = ((ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (network==null || !network.isConnected())
        {
            // Le périphérique n'est pas connecté à Internet
            return false;
        }
        // Le périphérique est connecté à Internet
        return true;
    }

}