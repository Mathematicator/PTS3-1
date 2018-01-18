package com.pts3.sport;




        import android.content.Context;
        import android.os.Handler;
        import android.os.SystemClock;
        import android.widget.TextView;


/**
 * Created by Guillaume on 13/01/2018.
 */

public class Chronometre {
    int i=1;
    private TextView txtAffichage,txtValue;
    boolean isRunning =false,hasStop = false;
    long startime = 0L, timeMillisecond = 0L, timeSwapBuff = 0L, updateTime = 0L;
    //Attributs
    private Handler handler;
    //Implémentation du thread permettant de mettre à jour le temps
    Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeMillisecond = SystemClock.uptimeMillis() - startime;
            updateTime = timeSwapBuff + timeMillisecond;
            int sec = (int) (updateTime / 1000);
            int mins = sec / 60;
            sec %= 60;
            int milliseconds = (int) (updateTime % 1000);
            txtValue.setText("" + String.format("%02d", mins) + ":" + String.format("%02d", sec) + ":" + String.format("%03d", milliseconds));

            handler.postDelayed(this, 0);
        }


    };
    private Context context;

    // Constructeur
    public Chronometre(Context context, Handler handler,TextView txtAffichage,TextView txtValue) {
        this.context = context;
        this.handler = handler;
        this.txtAffichage=txtAffichage;
        this.txtValue = txtValue;
    }

    //Fonction start qui lance le chronometre
    public void start() {
        isRunning=true;
        startime = SystemClock.uptimeMillis();
        handler.postDelayed(updateTimerThread, 0);
        if (!hasStop) {
            timeSwapBuff += timeMillisecond;
        }
        hasStop = false;
    }

    //Fonction stop qui arrete le chronometre
    public void stop() {
        isRunning=false;
        timeSwapBuff += timeMillisecond;
        handler.removeCallbacks(updateTimerThread);
        hasStop = true;
    }

    //Fonction qui affiche le temps dans un txtView(A créer)
    public void affTemps() {
        txtAffichage.setText(txtAffichage.getText() + "\n" +i+"-"+txtValue.getText());

        i++;
    }

    public void restart() {
        startime = 0L; timeMillisecond = 0L; timeSwapBuff = 0L; updateTime = 0L;

        txtValue.setText("00:00:000");

        txtAffichage.setText("");
        i=1;
    }
    public boolean isRunning(){
        return isRunning;
    }
}
