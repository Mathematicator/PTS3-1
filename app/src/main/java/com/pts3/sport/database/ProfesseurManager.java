package com.pts3.sport.database;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

/**
 * Created by Ragnulf on 31/12/2017.
 */

public class ProfesseurManager extends Manager {

    public ProfesseurManager(Context context) {
        super(context);
    }

    public boolean isLogin(String username, String password){

        Cursor c =  db.rawQuery("SELECT * FROM professeur WHERE nom_prof='"+username+"' and mdp_prof='"+password+"'", null);

        if(c.moveToFirst()) {
            Log.i("ID PROF ",(String) ""+c.getInt(0));
            return true;

        }
        else {
            return false;
        }

    }
    public int getId(String username, String password){

        Cursor c =  db.rawQuery("SELECT * FROM professeur WHERE nom_prof='"+username+"' and mdp_prof='"+password+"'", null);

        if(c.moveToFirst()) {
            Log.i("ID PROF ",(String) ""+c.getInt(0));
            return c.getInt(c.getColumnIndex("id_prof"));

        }
        else {
            return 0;
        }

    }

}
