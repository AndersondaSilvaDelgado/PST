package br.com.usinasantafe.pst.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pst.PSTContext;
import br.com.usinasantafe.pst.R;

public class DetalhesActivity extends ActivityGeneric {

    private EditText editTextExtensao;
    private EditText editTextPessoasCont;
    private EditText editTextPessoasObs;
    private PSTContext pstContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Button buttonAvancaDetalhes = findViewById(R.id.buttonAvancaDetalhes);
        Button buttonRetDetalhes = findViewById(R.id.buttonRetDetalhes);

        pstContext = (PSTContext) getApplication();

        editTextExtensao = findViewById(R.id.editTextExtensao);
        editTextPessoasCont = findViewById(R.id.editTextPessoasCont);
        editTextPessoasObs = findViewById(R.id.editTextPessoasObs);

        buttonAvancaDetalhes.setOnClickListener(v -> {

            if ((editTextExtensao.getText().length() > 0)
                    && (editTextPessoasCont.getText().length() > 0)
                    && (editTextPessoasObs.getText().length() > 0)) {

                if(Long.parseLong(editTextExtensao.getText().toString()) <= 500L){

                    pstContext.getAbordagemCTR().setDetalhesCabAbord(Long.parseLong(editTextExtensao.getText().toString()),
                            Long.parseLong(editTextPessoasCont.getText().toString()),
                            Long.parseLong(editTextPessoasObs.getText().toString()));

                    Intent it = new Intent(DetalhesActivity.this, ComentarioActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(DetalhesActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("EXTENSO EM MINUTOS DA ABORDAGEM ULTRAPASSOU O LIMITE MÁXIMO, FAVOR ALTERAR O VALOR DIGITADO!");
                    alerta.setPositiveButton("OK", (dialog, which) -> {
                    });
                    alerta.show();

                }

            } else {

                AlertDialog.Builder alerta = new AlertDialog.Builder(DetalhesActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("CAMPO(S) VAZIO(S)! POR FAVOR, PREENCHA TODOS OS CAMPOS PARA TERMINAR A ABORDAGEM.");
                alerta.setPositiveButton("OK", (dialog, which) -> {
                });
                alerta.show();

            }
        });

        buttonRetDetalhes.setOnClickListener(v -> {
            Intent it = new Intent(DetalhesActivity.this, ListaTurnoActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed() {
    }

}
