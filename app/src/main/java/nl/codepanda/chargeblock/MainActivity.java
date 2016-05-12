package nl.codepanda.chargeblock;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String value = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button save = (Button)findViewById(R.id.save);
        EditText etvalue = (EditText)findViewById(R.id.editText);

        value = etvalue.getText().toString();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!value.equals("")) {
                    Database database = new Database(MainActivity.this);
                    database.saveSpeed(4);
                    Toast.makeText(MainActivity.this, "Snelheid opgeslagen!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Voer een snelheid in", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
