package com.pts3.sport;



        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Handler;
        import android.preference.PreferenceManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.RadioGroup;
        import android.widget.TableRow;
        import android.widget.TextView;

        import com.pts3.sport.dao.Eleve;
        import com.pts3.sport.database.ClasseManager;
        import com.pts3.sport.database.EleveManager;

        import java.util.ArrayList;
        import java.util.List;

public class ChronoActivity extends AppCompatActivity {

    private static final String ITERATOR_VALUE = "a";
    public static TextView txtValue;
    public static TextView txtAffichage;
    public Button start, lap, stop, valider,suivant;

    ArrayList<TextView> textViewList,textViews2List;
    ArrayList<EditText> editTextList;


    TextView temps1,temps2,temps3,temps4,eleve1,eleve2,eleve3,eleve4;
    EditText txtInput1,txtInput2,txtInput3,txtInput4;
    int iterator,iterator2;
    private Chronometre chrono;
    private SharedPreferences preferences;
    private String classe;


    private List<Eleve> listEleve;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono);
        eleve1 = findViewById(R.id.eleve1);
        eleve2 = findViewById(R.id.eleve2);
        eleve3 = findViewById(R.id.eleve3);
        eleve4 = findViewById(R.id.eleve4);
        start = (Button) findViewById(R.id.button);
        txtAffichage = (TextView) findViewById(R.id.txtAffi);
        txtValue = (TextView) findViewById(R.id.txtValue);
        suivant=findViewById(R.id.btnSuivant);
        lap = (Button) findViewById(R.id.buttonLap);
        stop = (Button)findViewById(R.id.btnStop);
        valider = (Button)findViewById(R.id.btnValider);
        Intent intent=getIntent();
        iterator = intent.getIntExtra(ITERATOR_VALUE,0);
        chrono = new Chronometre(this, new Handler());
        String[] nom = {"Théo", "Marceau", "Guillaume","Test"}; //mettre les eleves a noter (classe)

        textViewList = new ArrayList<>();
        editTextList = new ArrayList<>();


        txtInput1 =  findViewById(R.id.txtInput);
        txtInput2 =  findViewById(R.id.txtInput2);
        txtInput3 =  findViewById(R.id.txtInput3);
        txtInput4 = findViewById(R.id.txtInput4);
        temps1 = findViewById(R.id.temps1);
        temps2 = findViewById(R.id.temps2);
        temps3 = findViewById(R.id.temps3);
        temps4 = findViewById(R.id.temps4);
        editTextList.add(txtInput1);
        editTextList.add(txtInput2);
        editTextList.add(txtInput3);
        editTextList.add(txtInput4);
        textViewList.add(temps1);
        textViewList.add(temps2);
        textViewList.add(temps3);
        textViewList.add(temps4);
        textViews2List = new ArrayList<>();
        textViews2List.add(eleve1);
        textViews2List.add(eleve2);
        textViews2List.add(eleve3);
        textViews2List.add(eleve4);
        Log.i("iterateur",""+iterator);



        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        classe = preferences.getString("classe", "");



        EleveManager eleveManager = new EleveManager(this);
        ClasseManager classeManager = new ClasseManager(this);
        listEleve = eleveManager.recupererTout(classeManager.recuperer(classe));


        for(Eleve eleve : listEleve){

            if(iterator <= iterator2 && iterator2 <iterator +4){
                Log.i("Boucle 1",eleve.getNom());

                Log.i("Boucle 2",eleve.getNom());

                textViews2List.get(iterator2 % 4).setText(eleve.getNom());

            }
            iterator2++;
        }


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.start();


            }
        });


        lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.affTemps();
                //associer un numero

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.stop();
            }
        });

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<=4;i++){
                    int startL = txtAffichage.getLayout().getLineStart(i);
                    int endL= txtAffichage.getLayout().getLineEnd(i);
                    String getTextOnLine = (String) txtAffichage.getText().subSequence(startL,endL);
                    Log.i("temps",getTextOnLine);
                    int j=0;
                    for(EditText eT : editTextList){

                        if(String.valueOf(eT.getText()).equals(""+i)){
                            Log.i("numéro", String.valueOf(eT.getText()));
                            textViewList.get(j).setText(getTextOnLine);
                        }
                        j++;
                    }

                }

            }
        });
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChronoActivity.this,ChronoActivity.class);
                  intent.putExtra(ITERATOR_VALUE,iterator+4);
                startActivity(intent);


            }
        });

    }

}
