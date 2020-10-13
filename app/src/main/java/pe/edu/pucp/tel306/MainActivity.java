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
    boolean pausar = true;
    Thread cronometro;
    int contadorLocalMinutos = 24;
    int contadorLocalSegundos = 59;
    Handler h = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Página Principal");

        ImageButton start = findViewById(R.id.imageButtonPlayPause);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausa++;
                if(pausa % 2 == 0){
                    pausar = true;
                    ImageButton imageButton = findViewById(R.id.imageButtonPlayPause);
                    imageButton.setImageResource(R.drawable.ic_action_play);
                }else{
                    pausar = false;
                    ImageButton imageButton = findViewById(R.id.imageButtonPlayPause);
                    imageButton.setImageResource(R.drawable.ic_action_pause);
                }
            }
        });

        ImageButton refresh = findViewById(R.id.imageButtonRefresh);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausar= true;
                contadorLocalMinutos = 24;
                contadorLocalSegundos = 59;
            }
        });

        cronometro = new Thread(new Runnable() {
            @Override
            public void run() {

                while (contadorLocalMinutos > 0){
                    if (pausar == false){
                        while (contadorLocalSegundos >= 0){
                            contadorLocalSegundos = contadorLocalSegundos -1;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                break;
                            }
                            if(contadorLocalSegundos == 0){
                                contadorLocalSegundos = 59;
                                break;
                            }
                        }

                        contadorLocalMinutos = contadorLocalMinutos -1;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }

                        if(contadorLocalMinutos == 0){
                            contadorLocalMinutos = 24;
                            break;
                        }
                    }

                }

                h.post(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = findViewById(R.id.textViewContadorSegundos);
                        if(contadorLocalSegundos > 10){
                            textView.setText(contadorLocalSegundos);
                        }else{
                            textView.setText("0" + contadorLocalSegundos );
                        }

                        TextView textView2 = findViewById(R.id.textViewContadorMinutos);
                        textView2.setText(contadorLocalMinutos +":");
                    }
                });

            }


        });

        TextView textView = findViewById(R.id.textViewContadorMinutos);
        registerForContextMenu(textView);

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