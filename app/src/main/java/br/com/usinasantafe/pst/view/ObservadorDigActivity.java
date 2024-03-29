package br.com.usinasantafe.pst.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.pst.PSTContext;
import br.com.usinasantafe.pst.R;
import br.com.usinasantafe.pst.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pst.util.ConexaoWeb;

public class ObservadorDigActivity extends ActivityGeneric {

    private PSTContext pstContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observador_dig);

        pstContext = (PSTContext) getApplication();

        Button buttonOkOperador = findViewById(R.id.buttonOkPadrao);
        Button buttonCancOperador = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(v -> {

            AlertDialog.Builder alerta = new AlertDialog.Builder( ObservadorDigActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ObservadorDigActivity.this)) {

                    progressBar = new ProgressDialog(ObservadorDigActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    pstContext.getAbordagemCTR().atualDadosColab(ObservadorDigActivity.this, ObservadorDigActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta1 = new AlertDialog.Builder( ObservadorDigActivity.this);
                    alerta1.setTitle("ATENÇÃO");
                    alerta1.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta1.setPositiveButton("OK", (dialog1, which1) -> {
                    });

                    alerta1.show();

                }


            });

            alerta.setPositiveButton("NÃO", (dialog, which) -> {
            });

            alerta.show();

        });

        buttonOkOperador.setOnClickListener(v -> {

            if (!editTextPadrao.getText().toString().equals("")) {

                if (pstContext.getAbordagemCTR().verColab(Long.parseLong(editTextPadrao.getText().toString()))) {

                    pstContext.getAbordagemCTR().setMatricFuncObsForm(pstContext.getAbordagemCTR().getColab(Long.parseLong(editTextPadrao.getText().toString())).getMatricColab());

                    Intent it = new Intent(ObservadorDigActivity.this, ListaAreaActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ObservadorDigActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("NUMERAÇÃO DO FUNCIONARIO INEXISTENTE! FAVOR VERIFICA A MESMA.");
                    alerta.setPositiveButton("OK", (dialog, which) -> {
                    });

                    alerta.show();

                }
            }

        });

        buttonCancOperador.setOnClickListener(v -> {
            if (editTextPadrao.getText().toString().length() > 0) {
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(ObservadorDigActivity.this, MenuInicialActivity.class);
        startActivity(it);
        finish();
    }

}
