package pe.edu.pucp.tel306;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        setTitle("Ayuda");

        TextView textViewMasInfo = findViewById(R.id.textViewMasInfo);
        textViewMasInfo.setText(Html.fromHtml("<a href='http://www.tomatotimers.com/'>Más información</a>"));
    }
}