package cyanoboru.secrethitler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // redirect main activity to add player
        startActivity(new Intent(MainActivity.this, add_player.class));
    }
}
