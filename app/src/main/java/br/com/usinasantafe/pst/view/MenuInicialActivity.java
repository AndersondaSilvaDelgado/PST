package br.com.usinasantafe.pst.view;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Handler;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.pst.BuildConfig;
import br.com.usinasantafe.pst.PSTContext;
import br.com.usinasantafe.pst.R;
import br.com.usinasantafe.pst.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pst.util.AtualDadosServ;
import br.com.usinasantafe.pst.util.ConexaoWeb;
import br.com.usinasantafe.pst.util.EnvioDadosServ;
import br.com.usinasantafe.pst.util.VerifDadosServ;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView menuListView;
    private PSTContext pstContext;
    private ProgressDialog progressBar;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        pstContext = (PSTContext) getApplication();
        textViewProcesso = findViewById(R.id.textViewProcesso);
        TextView textViewPrincipal = findViewById(R.id.textViewPrincipal);

        textViewPrincipal.setText("PRINCIPAL - V " + BuildConfig.VERSION_NAME);

        progressBar = new ProgressDialog(this);

        if (!checkPermission(Manifest.permission.CAMERA)) {
            String[] PERMISSIONS = {Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(this, PERMISSIONS, 112);
        }

        if (!checkPermission(Manifest.permission.INTERNET)) {
            String[] PERMISSIONS = {android.Manifest.permission.INTERNET};
            ActivityCompat.requestPermissions(this, PERMISSIONS, 112);
        }

        if (!checkPermission(Manifest.permission.ACCESS_NETWORK_STATE)) {
            String[] PERMISSIONS = {android.Manifest.permission.ACCESS_NETWORK_STATE};
            ActivityCompat.requestPermissions(this, PERMISSIONS, 112);
        }

        if (!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, PERMISSIONS, 112);
        }

        customHandler.postDelayed(updateTimerThread, 0);

        if(pstContext.getAbordagemCTR().verAbordAbert()){
            pstContext.setPosTipo(1);
            Intent it = new Intent(MenuInicialActivity.this, TopicoActivity.class);
            startActivity(it);
            finish();
        }

        listarMenuInicial();

    }

    private void listarMenuInicial() {

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("FORMULÁRIO");
        itens.add("CONFIGURAÇÃO");
        itens.add("ATUALIZAR DADOS");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        menuListView = findViewById(R.id.listaMenuInicial);
        menuListView.setAdapter(adapterList);

        menuListView.setOnItemClickListener((l, v, position, id) -> {

            TextView textView = v.findViewById(R.id.textViewItemList);
            String text = textView.getText().toString();

            if (text.equals("FORMULÁRIO")) {

                ColabBean colabBean = new ColabBean();

                if (colabBean.hasElements()) {

                    pstContext.getAbordagemCTR().clearBD();

                    Intent it = new Intent(MenuInicialActivity.this, ObservadorDigActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("BASE DE DADOS DESATUALIZADA! POR FAVOR, SELECIONE A OPÇÃO 'ATUALIZAR DADOS' PARA ATUALIZAR A BASE DE DADOS ANTES DE CRIAR UM NOVO FORMULÁRIO.");
                    alerta.setPositiveButton("OK", (dialog, which) -> {
                    });
                    alerta.show();

                }

            } else if (text.equals("ATUALIZAR DADOS")) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if(conexaoWeb.verificaConexao(MenuInicialActivity.this)){

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO BASE DE DADOS...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    pstContext.getConfigCTR().atualTodasTabelas(MenuInicialActivity.this, progressBar);

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", (dialog, which) -> {
                    });
                    alerta.show();

                }

            } else if (text.equals("CONFIGURAÇÃO")) {

                Intent it = new Intent(MenuInicialActivity.this, ConfigActivity.class);
                startActivity(it);
                finish();


            } else if (text.equals("SAIR")) {

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }

        });

    }

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    public void onBackPressed() {
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {
            int status = EnvioDadosServ.getInstance().getStatusEnvio();
            if (status == 1) {
                textViewProcesso.setTextColor(Color.YELLOW);
                textViewProcesso.setText("Enviando Dados...");
            }
            else if (status == 2) {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Existem Dados para serem Enviados");
            }
            else if (status == 3) {
                textViewProcesso.setTextColor(Color.GREEN);
                textViewProcesso.setText("Todos os Dados já foram Enviados");
            }
            customHandler.postDelayed(this, 10000);
        }
    };


}
