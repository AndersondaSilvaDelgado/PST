package br.com.usinasantafe.pst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetalhesActivity extends ActivityGeneric {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Button buttonAvancaDetalhes = (Button) findViewById(R.id.buttonAvancaDetalhes);
        Button buttonRetDetalhes = (Button) findViewById(R.id.buttonRetDetalhes);

        buttonAvancaDetalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(DetalhesActivity.this, ComentarioActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonRetDetalhes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(DetalhesActivity.this, ListaTurnoActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed()  {
    }

}
