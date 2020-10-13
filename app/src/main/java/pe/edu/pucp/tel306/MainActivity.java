package pe.edu.pucp.tel306;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import pe.edu.pucp.tel306.Threads.ControladorModelView;

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
                textView.setText(String.valueOf(integer));
            }
        });

        controladorModelView.getContadorSegundos().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                TextView textView = findViewById(R.id.textViewContadorSegundos);
                textView.setText(String.valueOf(integer));
            }
        });
    }

    public void iniciarContador (View view){
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        ControladorModelView controladorModelView = viewModelProvider.get(ControladorModelView.class);
        controladorModelView.iniciarciclo();

    }

}