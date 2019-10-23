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

import br.com.usinasantafe.pst.bean.estaticas.AreaBean;
import br.com.usinasantafe.pst.util.ConexaoWeb;

public class ListaAreaActivity extends ActivityGeneric {

    private List areaList;
    private PSTContext pstContext;
    private ListView areaListView;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_area);

        pstContext = (PSTContext) getApplication();

        Button buttonRetArea = (Button) findViewById(R.id.buttonRetArea);
        Button buttonAtualArea = (Button) findViewById(R.id.buttonAtualArea);

        buttonAtualArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder( ListaAreaActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

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

                            AlertDialog.Builder alerta = new AlertDialog.Builder( ListaAreaActivity.this);
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

        AreaBean areaBean = new AreaBean();
        areaList = areaBean.all();

        ArrayList<String> itens = new ArrayList<String>();

        for (int i = 0; i < areaList.size(); i++) {
            areaBean = (AreaBean) areaList.get(i);
            itens.add(areaBean.getDescrArea());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        areaListView = (ListView) findViewById(R.id.listArea);
        areaListView.setAdapter(adapterList);

        areaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                AreaBean subAreaBean = (AreaBean) areaList.get(position);
                pstContext.getAbordagemCTR().setIdAreaForm(subAreaBean.getIdArea());
                areaList.clear();

                Intent it = new Intent(ListaAreaActivity.this, ListaSubAreaActivity.class);
                startActivity(it);
                finish();

            }

        });


        buttonRetArea.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaAreaActivity.this, ObservadorActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed() {
    }

}
