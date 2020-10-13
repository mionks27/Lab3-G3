package pe.edu.pucp.tel306;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        setTitle("Editar");

        TextView editTextInterTrabajo = findViewById(R.id.editTextInterTrabajo);
        TextView editTextInterDescanso = findViewById(R.id.editTextInterDescanso);

        String interTrabajo = editTextInterTrabajo.getText().toString();
        String interDescanso = editTextInterDescanso.getText().toString();



    }
}