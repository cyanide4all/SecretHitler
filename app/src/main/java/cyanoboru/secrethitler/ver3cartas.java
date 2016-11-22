package cyanoboru.secrethitler;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

import cyanoboru.secrethitler.core.CartaDeLey;
import cyanoboru.secrethitler.core.Partida;
import cyanoboru.secrethitler.core.Tablero;

public class ver3cartas extends AppCompatActivity {

    private ImageView[] images;
    private Button back;
    private Button show;
    private View container;

    private Tablero tablero;

    protected void showCards(){
        show.setVisibility(View.GONE);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

        tablero = Partida.getInstance().getTablero();
        List<CartaDeLey> cartas = tablero.ver3Cartas();

        images = new ImageView[3];
        back = (Button) findViewById(R.id.seeBack);
        show = (Button) findViewById(R.id.seecards);

        images[0] = (ImageView) findViewById(R.id.vlaw1);
        images[1] = (ImageView) findViewById(R.id.vlaw2);
        images[2] = (ImageView) findViewById(R.id.vlaw3);

        container = findViewById(R.id.containersee);
        container.setVisibility(View.GONE);

        int i = 0;
        for(ImageView im: images){
            if(cartas.get(i++).getLey().compareTo("Liberal") == 0){
                im.setImageResource(R.mipmap.leyliberal);
            }else{
                im.setImageResource(R.mipmap.leyfascista);
            }
        }

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCards();
            }
        });
        back.setVisibility(View.GONE);
    }
}
