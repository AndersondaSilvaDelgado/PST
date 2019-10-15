package br.com.usinasantafe.pst;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Handler;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import br.com.usinasantafe.pst.bean.estaticas.ColabBean;
import br.com.usinasantafe.pst.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pst.control.ConfigCTR;
import br.com.usinasantafe.pst.util.ConexaoWeb;
import br.com.usinasantafe.pst.util.EnvioDadosServ;
import br.com.usinasantafe.pst.util.VerifDadosServ;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView menuListView;
    private ConfigBean configBean;
    private PSTContext pstContext;
    private ProgressDialog progressBar;
    private ConfigCTR configCTR;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        pstContext = (PSTContext) getApplication();
        textViewProcesso = (TextView) findViewById(R.id.textViewProcesso);

        configCTR = new ConfigCTR();

        if (!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        if (!checkPermission(Manifest.permission.CAMERA)) {
            String[] PERMISSIONS = {Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        customHandler.postDelayed(updateTimerThread, 0);

        atualizarAplic();

        listarMenuInicial();

    }

    private void listarMenuInicial() {

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("FORMULÁRIO");
        itens.add("CONFIGURAÇÃO");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        menuListView = (ListView) findViewById(R.id.listaMenuInicial);
        menuListView.setAdapter(adapterList);

        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("FORMULÁRIO")) {

                    ColabBean colabBean = new ColabBean();
                    configBean = new ConfigBean();
                    if (colabBean.hasElements() && configBean.hasElements()) {

                        pstContext.getObservCTR().setMatricFuncObsForm(0L);

                        Intent it = new Intent(MenuInicialActivity.this, ObservadorActivity.class);
                        startActivity(it);
                        finish();
                    }

                } else if (text.equals("CONFIGURAÇÃO")) {

                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();

                } else if (text.equals("SAIR")) {

                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }

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
            if (configCTR.hasElements()) {
                if (EnvioDadosServ.getInstance().getStatusEnvio() == 1) {
                    textViewProcesso.setTextColor(Color.YELLOW);
                    textViewProcesso.setText("Enviando Dados...");
                } else if (EnvioDadosServ.getInstance().getStatusEnvio() == 2) {
                    textViewProcesso.setTextColor(Color.RED);
                    textViewProcesso.setText("Existem Dados para serem Enviados");
                } else if (EnvioDadosServ.getInstance().getStatusEnvio() == 3) {
                    textViewProcesso.setTextColor(Color.GREEN);
                    textViewProcesso.setText("Todos os Dados já foram Enviados");
                }
            } else {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Por Favor, realize a CONFIGURAÇÃO do aplicativo.");
            }
            customHandler.postDelayed(this, 10000);
        }
    };

    public void atualizarAplic(){
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(this)) {
            if (configCTR.hasElements()) {
                progressBar.setCancelable(true);
                progressBar.setMessage("BUSCANDO ATUALIZAÇÃO...");
                progressBar.show();
                VerifDadosServ.getInstance().verAtualAplic(pstContext.versaoAplic, this, progressBar);
            }
        } else {
            startTimer();
        }
    }

    public void startTimer() {

        boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE) == null);

        if (progressBar.isShowing()) {
            progressBar.dismiss();
        }

        if (alarmeAtivo) {

            Log.i("PMM", "NOVO TIMER");
            Intent intent = new Intent("EXECUTAR_ALARME");
            PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 1);

            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, p);

        } else {
            Log.i("PMM", "TIMER já ativo");
        }
    }

}
