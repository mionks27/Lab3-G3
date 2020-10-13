package pe.edu.pucp.tel306;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import pe.edu.pucp.tel306.Threads.ControladorModelView;
import android.view.Menu;
import android.view.MenuItem;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    boolean descanso1 = false;
    int pausa =0;
    String min = "";
    String seg = "";
    ImageButton start;
    boolean descanso = false;
    ControladorModelView contadorViewModel;
    int i= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Página Principal");
        TextView min = findViewById(R.id.TextMotivacional);
        min.setVisibility(View.GONE);


        ImageView nim = findViewById(R.id.imageView);
        nim.setVisibility(View.GONE);

        TextView textView = findViewById(R.id.textViewContadorMinutos);
        registerForContextMenu(textView);

        start = findViewById(R.id.imageButtonPlayPause);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pausa>0){
                    TextView min = findViewById(R.id.TextMotivacional);
                    min.setVisibility(View.VISIBLE);
                    ImageView nim = findViewById(R.id.imageView);
                    nim.setVisibility(View.VISIBLE);
                }
                if(pausa % 2 == 0){
                    start.setImageResource(R.drawable.ic_action_play);
                }else{
                    start.setImageResource(R.drawable.ic_action_pause);
                }
            }
        });

        ImageButton refresh = findViewById(R.id.imageButtonRefresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setImageResource(R.drawable.ic_action_play);
            }
        });



        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        contadorViewModel = viewModelProvider.get(ControladorModelView.class);
        contadorViewModel.getContadorSegundos().observe(this, new Observer<Pair<Boolean, Integer>>() {
            @Override
            public void onChanged(Pair<Boolean, Integer> booleanIntegerPair) {

                if (booleanIntegerPair.first){
                    int valor = booleanIntegerPair.second;
                    TextView segundos = findViewById(R.id.textViewSegundos);
                   if(valor > 10){
                       segundos.setText(String.valueOf(valor));
                   }else{
                       segundos.setText("0"+String.valueOf(valor));
                   }

                    if(valor == 0){
                        descanso = true;
                    }
                }



            }
        });

        contadorViewModel.getContadorMinutos().observe(this, new Observer<Pair<Boolean, Integer>>() {
            @Override
            public void onChanged(Pair<Boolean, Integer> booleanIntegerPair) {

                if (booleanIntegerPair.first){
                    int valor = booleanIntegerPair.second;
                    String minu = String.valueOf(valor);
                    TextView min = findViewById(R.id.textViewContadorMinutos);
                    if(valor > 10){
                        min.setText(String.valueOf(valor)+" :");
                    }else{
                        min.setText("0"+String.valueOf(valor)+" :");
                    }

                    if(valor == 0){
                        descanso1 = true;
                    }

                }


                if(descanso == true && descanso1 == true){
                    contadorViewModel.contadorDescanso();
                }

            }
        });

        contadorViewModel.getNotificacionFin().observe(this, new Observer<Pair<Boolean, String>>() {
            public void onChanged(Pair<Boolean, String> booleanIntegerPair) {

                if (booleanIntegerPair.first){
                    i++;
                    String valor = booleanIntegerPair.second;
                    TextView min = findViewById(R.id.TextMotivacional);
                    min.setText(valor);
                    if(i==5){
                        i = 0;

                    }
                }
                  }
        });

        contadorViewModel.getNotificacionCiclo().observe(this, new Observer<Pair<Boolean, String>>() {
            public void onChanged(Pair<Boolean, String> booleanIntegerPair) {

                if (booleanIntegerPair.first){
                    String valor = booleanIntegerPair.second;
                    TextView min = findViewById(R.id.textViewCiclos);
                    min.setText(valor);
                }
            }
        });

    }

    public void iniciarContador(View view) {
        pausa++;
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        ControladorModelView contadorViewModel = viewModelProvider.get(ControladorModelView.class);
        if(pausa % 2 == 0){
            contadorViewModel.pauseContador();
        }else{
            contadorViewModel.iniciarContador();
        }

    }

    public void refreshContador(View view) {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        ControladorModelView contadorViewModel = viewModelProvider.get(ControladorModelView.class);
            contadorViewModel.refreshContador();
        pausa = 0;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void buttonHelp (MenuItem item) {
        Intent intent = new Intent(MainActivity.this, HelpActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_item:
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
                return true;
            case R.id.reset_item:

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}