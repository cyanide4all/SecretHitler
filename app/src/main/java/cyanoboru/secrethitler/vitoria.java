package cyanoboru.secrethitler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class vitoria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitoria);

        TextView winners = (TextView) findViewById(R.id.ganadores);
        Intent i = getIntent();
        winners.setText(i.getStringExtra("winners"));
    }
}
