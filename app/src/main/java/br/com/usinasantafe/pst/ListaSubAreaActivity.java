package br.com.usinasantafe.pst;

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

import br.com.usinasantafe.pst.model.bean.estaticas.SubAreaBean;
import br.com.usinasantafe.pst.util.ConexaoWeb;

public class ListaSubAreaActivity extends ActivityGeneric {

    private PSTContext pstContext;
    private List subAreaList;
    private ListView subAreaListView;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sub_area);

        pstContext = (PSTContext) getApplication();

        Button buttonRetSubArea = (Button) findViewById(R.id.buttonRetSubArea);
        Button buttonAtualSubArea = (Button) findViewById(R.id.buttonAtualSubArea);

        buttonAtualSubArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder( ListaSubAreaActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

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

                            AlertDialog.Builder alerta = new AlertDialog.Builder( ListaSubAreaActivity.this);
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

        SubAreaBean subAreaBean = new SubAreaBean();
        subAreaList = subAreaBean.get("idArea", pstContext.getAbordagemCTR().getIdAreaForm());

        ArrayList<String> itens = new ArrayList<String>();

        for(int i = 0; i < subAreaList.size(); i++){
            subAreaBean = (SubAreaBean) subAreaList.get(i);
            itens.add(subAreaBean.getDescrSubArea());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        subAreaListView = (ListView) findViewById(R.id.listSubArea);
        subAreaListView.setAdapter(adapterList);

        subAreaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                SubAreaBean subAreaBean = (SubAreaBean) subAreaList.get(position);
                pstContext.getAbordagemCTR().setIdSubAreaForm(subAreaBean.getIdSubArea());
                subAreaList.clear();

                Intent it = new Intent(ListaSubAreaActivity.this, ListaTurnoActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetSubArea.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaSubAreaActivity.this, ListaAreaActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed() {
    }

}
