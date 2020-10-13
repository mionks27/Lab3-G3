package pe.edu.pucp.tel306;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import pe.edu.pucp.tel306.Threads.ControladorModelView;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Página Principal");
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        ControladorModelView controladorModelView = viewModelProvider.get(ControladorModelView.class);
        controladorModelView.getContadorMinutos().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                TextView textView = findViewById(R.id.textViewContadorMinutos);
                if(integer > 10){
                    textView.setText(String.valueOf(integer));
                }else{
                    textView.setText("0"+String.valueOf(integer));
                }

            }
        });

        controladorModelView.getContadorSegundos().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                TextView textView = findViewById(R.id.textViewContadorSegundos);
                textView.setText(String.valueOf(integer) + ":");
            }
        });
    }

    public void iniciarContador (View view){
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        ControladorModelView controladorModelView = viewModelProvider.get(ControladorModelView.class);
        controladorModelView.iniciarciclo();

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
}