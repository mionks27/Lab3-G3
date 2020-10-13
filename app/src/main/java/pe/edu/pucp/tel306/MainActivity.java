package pe.edu.pucp.tel306;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import pe.edu.pucp.tel306.Threads.ControladorModelView;
import android.view.Menu;
import android.view.MenuItem;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int pausa =0;
    boolean corre = false;
    Thread cronometro;
    int contadorLocalMinutos = 24;
    int contadorLocalSegundos = 59;
    TextView cronometroText;
    Handler h = new Handler();
    ImageButton startPause , refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setTitle("Página Principal");
        startPause = findViewById(R.id.imageButtonPlayPause);
        refresh = findViewById(R.id.imageButtonRefresh);
        cronometroText = findViewById(R.id.textViewContadorMinutos);

        cronometro = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){
                    if (corre == true){

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        contadorLocalSegundos= contadorLocalSegundos -1;

                        if(contadorLocalSegundos == 0){
                            contadorLocalMinutos = contadorLocalMinutos -1;
                            contadorLocalSegundos = 59;
                        }

                        if (contadorLocalMinutos == 0){
                            contadorLocalMinutos = 24;
                        }
                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                    String segundos = "";
                                    String minutos = "";

                                if(contadorLocalSegundos >= 10){
                                    segundos = String.valueOf(contadorLocalSegundos);
                                }else{
                                    segundos = "0"+String.valueOf(contadorLocalSegundos);
                                }
                                if(contadorLocalMinutos>= 10){
                                    minutos = String.valueOf(contadorLocalMinutos);
                                }else {
                                    minutos = String.valueOf(contadorLocalMinutos);
                                }

                                cronometroText.setText(minutos+":"+segundos);
                            }
                        });

                    }

                }
            }


        });
        cronometro.start();

        TextView textView = findViewById(R.id.textViewContadorMinutos);
        registerForContextMenu(textView);

    }

    public void onClick(View view){

        switch (view.getId()){
            case R.id.imageButtonPlayPause:
                corre = true;
                break;
            case R.id.imageButtonRefresh:
                corre = false;
                contadorLocalMinutos = 24;
                contadorLocalSegundos = 59;
                break;
        }

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