package br.com.usinasantafe.pst.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import br.com.usinasantafe.pst.PSTContext;
import br.com.usinasantafe.pst.R;

public class ComentarioActivity extends ActivityGeneric {

    private RadioGroup radioGroupSegInseg;
    private EditText editTextComentario;
    private PSTContext pstContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);

        radioGroupSegInseg = findViewById(R.id.radioGroupSegInseg);
        Button buttonRetComentario = findViewById(R.id.buttonRetComentario);
        Button buttonAvancaComentario = findViewById(R.id.buttonAvancaComentario);
        editTextComentario = findViewById(R.id.editTextComentario);

        pstContext = (PSTContext) getApplication();

        buttonAvancaComentario.setOnClickListener(v -> {

            int selectedRadioButtonID = radioGroupSegInseg.getCheckedRadioButtonId();

            if ((selectedRadioButtonID != -1) && (!editTextComentario.getText().toString().trim().equals(""))) {

                RadioButton selectedRadioButton = findViewById(selectedRadioButtonID);
                String selectedRadioButtonText = selectedRadioButton.getText().toString();
                Long tipo;
                if(selectedRadioButtonText.equals("SEGURO")){
                    tipo = 1L;
                } else {
                    tipo = 2L;
                }

                pstContext.getAbordagemCTR().setComentarioCabAbord(tipo, editTextComentario.getText().toString());
                pstContext.getAbordagemCTR().salvarCabecAberto();

                pstContext.setPosTipo(1);

                Intent it = new Intent(ComentarioActivity.this, TopicoActivity.class);
                startActivity(it);
                finish();

            } else {

                AlertDialog.Builder alerta = new AlertDialog.Builder(ComentarioActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("CAMPO(S) VAZIO(S)! POR FAVOR, PREENCHA TODOS OS CAMPOS PARA TERMINAR A ABORDAGEM.");
                alerta.setPositiveButton("OK", (dialog, which) -> {
                });
                alerta.show();

            }

        });

        buttonRetComentario.setOnClickListener(v -> {

            Intent it = new Intent(ComentarioActivity.this, DetalhesActivity.class);
            startActivity(it);
            finish();

        });

    }

    public void onBackPressed() {
    }

}
