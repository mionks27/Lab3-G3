package pe.edu.pucp.tel306;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        setTitle("Editar");

        if(savedInstanceState != null) {
            String interTrabajoTexto = savedInstanceState.getString("interTrabajo");
            String interDescansoTexto = savedInstanceState.getString("interDescanso");
            String ciclosTexto = savedInstanceState.getString("ciclos");

            if(interTrabajoTexto != null) {
                TextView editTextInterTrabajo = findViewById(R.id.editTextInterTrabajo);
                editTextInterTrabajo.setText(interTrabajoTexto);
            }
            if(interDescansoTexto != null) {
                TextView editTextInterDescanso = findViewById(R.id.editTextInterDescanso);
                editTextInterDescanso.setText(interDescansoTexto);
            }
            if(ciclosTexto != null) {
                TextView editTextCiclos = findViewById(R.id.editTextCiclos);
                editTextCiclos.setText(ciclosTexto);
            }
        }

        Button buttonSaveEdit = findViewById(R.id.buttonSaveEdit);
        buttonSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView editTextInterTrabajo = findViewById(R.id.editTextInterTrabajo);
                TextView editTextInterDescanso = findViewById(R.id.editTextInterDescanso);
                TextView editTextCiclos = findViewById(R.id.editTextCiclos);

                String interTrabajo = editTextInterTrabajo.getText().toString();
                String interDescanso = editTextInterDescanso.getText().toString();
                String ciclos = editTextCiclos.getText().toString();

                if(interTrabajo.isEmpty()) {
                    editTextInterTrabajo.setError("No puede estar vacío");
                }
                if(interDescanso.isEmpty()) {
                    editTextInterDescanso.setError("No puede estar vacío");
                }
                if(ciclos.isEmpty()) {
                    editTextCiclos.setError("No puede estar vacío");
                }

                if(!interTrabajo.isEmpty() && !interDescanso.isEmpty() && !ciclos.isEmpty()) {
                    Toast.makeText(EditActivity.this, "Editado exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditActivity.this, MainActivity.class);

                    intent.putExtra("trabajo", interTrabajo);
                    intent.putExtra("descanso", interDescanso);
                    intent.putExtra("ciclo", ciclos);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView editTextInterTrabajo = findViewById(R.id.editTextInterTrabajo);
        TextView editTextInterDescanso = findViewById(R.id.editTextInterDescanso);
        TextView editTextCiclos = findViewById(R.id.editTextCiclos);

        String interTrabajo = editTextInterTrabajo.getText().toString();
        String interDescanso = editTextInterDescanso.getText().toString();
        String ciclos = editTextCiclos.getText().toString();

        outState.putString("interTrabajo", interTrabajo);
        outState.putString("interDescanso", interDescanso);
        outState.putString("ciclos", ciclos);
    }
}