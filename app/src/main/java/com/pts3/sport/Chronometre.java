package com.pts3.sport;



        import android.content.Context;
        import android.os.Handler;
        import android.os.SystemClock;

/**
 * Created by Guillaume on 13/01/2018.
 */

public class Chronometre {
    int i=1;
    boolean hasStop = false;
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
            ChronoActivity.txtValue.setText("" + String.format("%02d", mins) + ":" + String.format("%02d", sec) + ":" + String.format("%03d", milliseconds));
            handler.postDelayed(this, 0);
        }


    };
    private Context context;

    // Constructeur
    public Chronometre(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    //Fonction start qui lance le chronometre
    public void start() {
        startime = SystemClock.uptimeMillis();
        handler.postDelayed(updateTimerThread, 0);
        if (!hasStop) {
            timeSwapBuff += timeMillisecond;
        }
        hasStop = false;
    }

    //Fonction stop qui arrete le chronometre
    public void stop() {
        timeSwapBuff += timeMillisecond;
        handler.removeCallbacks(updateTimerThread);
        hasStop = true;
    }

    //Fonction qui affiche le temps dans un txtView(A créer)
    public void affTemps() {

        ChronoActivity.txtAffichage.setText(ChronoActivity.txtAffichage.getText() + "\n" +i+"-"+ChronoActivity.txtValue.getText());
        i++;
    }
}
