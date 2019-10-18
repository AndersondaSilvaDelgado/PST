package br.com.usinasantafe.pst;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ComentarioActivity extends ActivityGeneric {

    private RadioGroup radioGroupSegInseg;
    private EditText editTextComentario;
    private PSTContext pstContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);

        radioGroupSegInseg = (RadioGroup) findViewById(R.id.radioGroupSegInseg);
        Button buttonRetComentario = (Button) findViewById(R.id.buttonRetComentario);
        Button buttonAvancaComentario = (Button) findViewById(R.id.buttonAvancaComentario);
        editTextComentario = (EditText) findViewById(R.id.editTextComentario);

        pstContext = (PSTContext) getApplication();

        buttonAvancaComentario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int selectedRadioButtonID = radioGroupSegInseg.getCheckedRadioButtonId();

                if ((selectedRadioButtonID != -1) && (!editTextComentario.getText().toString().equals(""))) {

                    RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonID);
                    String selectedRadioButtonText = selectedRadioButton.getText().toString();
                    Long tipo;
                    if(selectedRadioButtonText.equals("SEGURO")){
                        tipo = 1L;
                    }
                    else{
                        tipo = 2L;
                    }

                    pstContext.getAbordagemCTR().setComentarioCabAbord(tipo, editTextComentario.getText().toString());
                    pstContext.getAbordagemCTR().salvarCabecAberto();

                    pstContext.setPosTipo(1);

                    Intent it = new Intent(ComentarioActivity.this, TopicoActivity.class);
                    startActivity(it);
                    finish();

                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ComentarioActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("CAMPO(S) VAZIO(S)! POR FAVOR, PREENCHA TODOS OS CAMPOS PARA TERMINAR A ABORDAGEM.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alerta.show();

                }



            }

        });

        buttonRetComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(ComentarioActivity.this, DetalhesActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void onBackPressed() {
    }

}
