package br.com.usinasantafe.pst;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ComentarioActivity extends ActivityGeneric {

    private RadioGroup radioGroupSegInseg;
    private PSTContext pstContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);

        radioGroupSegInseg = (RadioGroup) findViewById(R.id.radioGroupSegInseg);
        Button buttonRetComentario = (Button) findViewById(R.id.buttonRetComentario);
        Button buttonAvancaComentario = (Button) findViewById(R.id.buttonAvancaComentario);

        pstContext = (PSTContext) getApplication();

        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_enabled}, //disabled
                        new int[]{android.R.attr.state_enabled} //enabled
                },
                new int[] {
                        Color.BLACK //disabled
                        ,Color.BLUE //enabled
                }
        );

        RadioButton radioButtonSeguro = new RadioButton(getApplicationContext());
        radioButtonSeguro.setText("SEGURO");
        radioButtonSeguro.setTextColor(Color.BLACK);
        radioButtonSeguro.setTextSize(20F);
        radioButtonSeguro.setButtonTintList(colorStateList);
        radioGroupSegInseg.addView(radioButtonSeguro);

        RadioButton radioButtonInseguro = new RadioButton(getApplicationContext());
        radioButtonInseguro.setText("INSEGURO");
        radioButtonInseguro.setTextColor(Color.BLACK);
        radioButtonInseguro.setTextSize(20F);
        radioButtonInseguro.setButtonTintList(colorStateList);
        radioGroupSegInseg.addView(radioButtonInseguro);

        buttonRetComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(ComentarioActivity.this, DetalhesActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonAvancaComentario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                pstContext.setPosTipo(1);

                Intent it = new Intent(ComentarioActivity.this, TopicoActivity.class);
                startActivity(it);
                finish();

            }

        });

    }

    public void onBackPressed() {
    }

}
