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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pst.model.bean.estaticas.TipoBean;
import br.com.usinasantafe.pst.model.bean.estaticas.TopicoBean;
import br.com.usinasantafe.pst.util.ConexaoWeb;

public class TopicoActivity extends ActivityGeneric {

    private PSTContext pstContext;
    private List topicoList;
    private ListView topicoListView;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topico);

        pstContext = (PSTContext) getApplication();

        TextView textViewTipo = (TextView) findViewById(R.id.textViewTipo);
        Button buttonAvancaTopico = (Button) findViewById(R.id.buttonAvancaTopico);
        Button buttonAtualTopico = (Button) findViewById(R.id.buttonAtualTopico);
        Button buttonRetTopico = (Button) findViewById(R.id.buttonRetTopico);
        Button buttonCancAbord = (Button) findViewById(R.id.buttonCancAbord);

        buttonAtualTopico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder( TopicoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(TopicoActivity.this)) {

                            progressBar = new ProgressDialog(TopicoActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pstContext.getAbordagemCTR().atualDadosItem(TopicoActivity.this, TopicoActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder( TopicoActivity.this);
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

        TipoBean tipoBean = new TipoBean();
        List tipoList = tipoBean.all();
        tipoBean = (TipoBean) tipoList.get(pstContext.getPosTipo() - 1);
        tipoList.clear();

        textViewTipo.setText(tipoBean.getDescrTipo());

        TopicoBean topicoBean = new TopicoBean();
        topicoList = topicoBean.get("idTipo", tipoBean.getIdTipo());

        ArrayList<String> itens = new ArrayList<String>();

        for(int i = 0; i < topicoList.size(); i++){
            topicoBean = (TopicoBean) topicoList.get(i);
            itens.add(topicoBean.getDescrTopico());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        topicoListView = (ListView) findViewById(R.id.listTopico);
        topicoListView.setAdapter(adapterList);

        topicoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TopicoBean topicoBean = (TopicoBean) topicoList.get(position);
                pstContext.setIdTopico(topicoBean.getIdTopico());
                topicoList.clear();

                Intent it = new Intent(TopicoActivity.this, QuestaoActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonAvancaTopico.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(topicoList.size() == pstContext.getPosTipo()){
                    Intent it = new Intent(TopicoActivity.this, CameraActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    pstContext.setPosTipo(pstContext.getPosTipo() + 1);
                    Intent it = new Intent(TopicoActivity.this, TopicoActivity.class);
                    startActivity(it);
                    finish();
                }

                topicoList.clear();

            }
        });

        buttonRetTopico.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(pstContext.getPosTipo() > 1){
                    pstContext.setPosTipo(pstContext.getPosTipo() - 1);
                    Intent it = new Intent(TopicoActivity.this, TopicoActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

        buttonCancAbord.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(TopicoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ABANDONA A ABORDAGEM?");
                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pstContext.getAbordagemCTR().clearBD();
                        Intent it = new Intent(TopicoActivity.this, MenuInicialActivity.class);
                        startActivity(it);
                        finish();
                    }
                });

                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                alerta.show();

            }
        });

    }

    public void onBackPressed()  {
    }

}
