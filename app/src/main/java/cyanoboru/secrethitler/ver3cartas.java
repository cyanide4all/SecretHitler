package cyanoboru.secrethitler;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class ver3cartas extends AppCompatActivity {

    private ImageView[] images;
    private Button back;
    private Button show;
    private View container;

    protected void showCards(){
        View myView = container;

        int cx = myView.getWidth() / 2;
        int cy = myView.getHeight();

        float finalRadius = (float) Math.hypot(cx, cy);

        Animator anim = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
        }

        myView.setVisibility(View.VISIBLE);
        anim.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver3cartas);

        images = new ImageView[3];
        back = (Button) findViewById(R.id.seeBack);
        show = (Button) findViewById(R.id.seecards);

        images[0] = (ImageView) findViewById(R.id.vlaw1);
        images[1] = (ImageView) findViewById(R.id.vlaw2);
        images[2] = (ImageView) findViewById(R.id.vlaw3);

        container = findViewById(R.id.containersee);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCards();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
