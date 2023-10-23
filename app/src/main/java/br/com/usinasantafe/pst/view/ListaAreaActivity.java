package br.com.usinasantafe.pst.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pst.PSTContext;
import br.com.usinasantafe.pst.R;
import br.com.usinasantafe.pst.model.bean.estaticas.AreaBean;
import br.com.usinasantafe.pst.util.ConexaoWeb;

public class ListaAreaActivity extends ActivityGeneric {

    private List<AreaBean> areaList;
    private PSTContext pstContext;
    private ListView areaListView;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_area);

        pstContext = (PSTContext) getApplication();

        Button buttonRetArea = findViewById(R.id.buttonRetArea);
        Button buttonAtualArea = findViewById(R.id.buttonAtualArea);

        buttonAtualArea.setOnClickListener(v -> {

            AlertDialog.Builder alerta = new AlertDialog.Builder( ListaAreaActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaAreaActivity.this)) {

                    progressBar = new ProgressDialog(ListaAreaActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    pstContext.getAbordagemCTR().atualDadosArea(ListaAreaActivity.this, ListaAreaActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta1 = new AlertDialog.Builder( ListaAreaActivity.this);
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

        areaList = pstContext.getAbordagemCTR().areaList();

        ArrayList<String> itens = new ArrayList<String>();

        for (AreaBean areaBean : areaList) {
            itens.add(areaBean.getDescrArea());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        areaListView = findViewById(R.id.listArea);
        areaListView.setAdapter(adapterList);

        areaListView.setOnItemClickListener((l, v, position, id) -> {

            AreaBean subAreaBean = areaList.get(position);
            pstContext.getAbordagemCTR().setIdAreaForm(subAreaBean.getIdArea());
            areaList.clear();

            Intent it = new Intent(ListaAreaActivity.this, ListaSubAreaActivity.class);
            startActivity(it);
            finish();

        });


        buttonRetArea.setOnClickListener(v -> {
            Intent it = new Intent(ListaAreaActivity.this, ObservadorDigActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed() {
    }

}
