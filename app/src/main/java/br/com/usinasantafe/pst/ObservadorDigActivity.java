package br.com.usinasantafe.pst;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.pst.bean.estaticas.ColabBean;
import br.com.usinasantafe.pst.util.ConexaoWeb;

public class ObservadorDigActivity extends ActivityGeneric {

    private PSTContext pstContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observador_dig);

        pstContext = (PSTContext) getApplication();

        Button buttonOkOperador = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancOperador = (Button) findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder( ObservadorDigActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ObservadorDigActivity.this)) {

                            progressBar = new ProgressDialog(ObservadorDigActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

//                            pmmContext.getBoletimCTR().atualDadosOperador(OperadorActivity.this, OperadorActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder( ObservadorDigActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            alerta.show();

                        }


                    }
                });

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();

            }

        });

        buttonOkOperador.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    ColabBean colabBean = new ColabBean();
                    List colabList = colabBean.get("matricColab", Long.parseLong(editTextPadrao.getText().toString()));

                    if (colabList.size() > 0) {

                        colabBean = (ColabBean) colabList.get(0);
                        pstContext.getObservCTR().setMatricFuncObsForm(colabBean.getMatricColab());
                        colabList.clear();

                        Intent it = new Intent(ObservadorDigActivity.this, ObservadorActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ObservadorDigActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("NUMERAÇÃO DO FUNCIONARIO INEXISTENTE! FAVOR VERIFICA A MESMA.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();

                    }
                }

            }

        });

        buttonCancOperador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(ObservadorDigActivity.this, ObservadorActivity.class);
        startActivity(it);
        finish();
    }

}
