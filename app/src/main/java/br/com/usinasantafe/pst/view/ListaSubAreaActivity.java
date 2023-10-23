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
import br.com.usinasantafe.pst.model.bean.estaticas.SubAreaBean;
import br.com.usinasantafe.pst.util.ConexaoWeb;

public class ListaSubAreaActivity extends ActivityGeneric {

    private PSTContext pstContext;
    private List<SubAreaBean> subAreaList;
    private ListView subAreaListView;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sub_area);

        pstContext = (PSTContext) getApplication();

        Button buttonRetSubArea = findViewById(R.id.buttonRetSubArea);
        Button buttonAtualSubArea = findViewById(R.id.buttonAtualSubArea);

        buttonAtualSubArea.setOnClickListener(v -> {

            AlertDialog.Builder alerta = new AlertDialog.Builder( ListaSubAreaActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaSubAreaActivity.this)) {

                    progressBar = new ProgressDialog(ListaSubAreaActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    pstContext.getAbordagemCTR().atualDadosSubArea(ListaSubAreaActivity.this, ListaSubAreaActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta1 = new AlertDialog.Builder( ListaSubAreaActivity.this);
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

        subAreaList = pstContext.getAbordagemCTR().subAreaList();

        ArrayList<String> itens = new ArrayList<>();

        for(SubAreaBean subAreaBean : subAreaList){
            itens.add(subAreaBean.getDescrSubArea());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        subAreaListView = findViewById(R.id.listSubArea);
        subAreaListView.setAdapter(adapterList);

        subAreaListView.setOnItemClickListener((l, v, position, id) -> {

            SubAreaBean subAreaBean = subAreaList.get(position);
            pstContext.getAbordagemCTR().setIdSubAreaForm(subAreaBean.getIdSubArea());
            subAreaList.clear();

            Intent it = new Intent(ListaSubAreaActivity.this, ListaTurnoActivity.class);
            startActivity(it);
            finish();

        });

        buttonRetSubArea.setOnClickListener(v -> {
            Intent it = new Intent(ListaSubAreaActivity.this, ListaAreaActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed() {
    }

}
