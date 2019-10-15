package br.com.usinasantafe.pst;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import br.com.usinasantafe.pst.bean.estaticas.ColabBean;
import br.com.usinasantafe.pst.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pst.util.AtualDadosServ;
import br.com.usinasantafe.pst.util.ConexaoWeb;

public class ConfiguracaoActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private EditText editTextMatricFuncConfig;
    private EditText editTextSenhaConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

        Button btOkConfig =  (Button) findViewById(R.id.buttonSalvarConfig );
        Button btCancConfig = (Button) findViewById(R.id.buttonCancConfig);
        Button btAtualBDConfig = (Button) findViewById(R.id.buttonAtualizarBD);
        editTextMatricFuncConfig = (EditText)  findViewById(R.id.editTextMatricFuncConfig);
        editTextSenhaConfig = (EditText)  findViewById(R.id.editTextSenhaConfig);

        ConfigBean configBean = new ConfigBean();

        if (configBean.hasElements()) {

            List configList = configBean.all();
            configBean = (ConfigBean) configList.get(0);
            configList.clear();

            editTextMatricFuncConfig.setText(String.valueOf(configBean.getMatricFuncConfig()));
            editTextSenhaConfig.setText(configBean.getSenhaConfig());

        }

        btOkConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editTextMatricFuncConfig.getText().toString().equals("") &&
                        !editTextSenhaConfig.getText().toString().equals("")){

                    ColabBean colabBean = new ColabBean();
                    List colabList = colabBean.get("matricColab", Long.parseLong(editTextMatricFuncConfig.getText().toString()));

                    if (colabList.size() > 0) {

                        colabBean = (ColabBean) colabList.get(0);

                        ConfigBean configBean = new ConfigBean();
                        configBean.setMatricFuncConfig(colabBean.getMatricColab());
                        configBean.setSenhaConfig(editTextSenhaConfig.getText().toString());
                        configBean.deleteAll();
                        configBean.insert();
                        configBean.commit();

                        Intent it = new Intent(ConfiguracaoActivity.this, MenuInicialActivity.class);
                        startActivity(it);
                        finish();

                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(ConfiguracaoActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("MATRICULA DO COLADORADOR INEXISTENTE! POR FAVOR, VERIFIQUE A NUMERAÇÃO DO MESMO.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();
                    }

                    colabList.clear();

                }

            }
        });

        btCancConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(ConfiguracaoActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();

            }
        });

        btAtualBDConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if(conexaoWeb.verificaConexao(ConfiguracaoActivity.this)){

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    AtualDadosServ.getInstance().atualizarBD(progressBar);
                    AtualDadosServ.getInstance().setContext(ConfiguracaoActivity.this);

                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfiguracaoActivity.this);
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

    }

    public void onBackPressed() {
    }

}
