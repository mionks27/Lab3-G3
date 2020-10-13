package pe.edu.pucp.tel306;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        setTitle("Ayuda");

        TextView textViewMasInfo = findViewById(R.id.textViewMasInfo);
        textViewMasInfo.setText(Html.fromHtml("<a href='http://www.tomatotimers.com/'>Más información</a>"));
    }

    public void irATomato (View view) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, "http://www.tomatotimers.com/");
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(HelpActivity.this, "No se puede abrir.", Toast.LENGTH_SHORT).show();
        }
    }
}