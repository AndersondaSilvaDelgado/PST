package br.com.usinasantafe.pst.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pst.PSTContext;
import br.com.usinasantafe.pst.R;
import br.com.usinasantafe.pst.model.bean.estaticas.TipoBean;
import br.com.usinasantafe.pst.model.bean.estaticas.TopicoBean;
import br.com.usinasantafe.pst.util.ConexaoWeb;

public class TopicoActivity extends ActivityGeneric {

    private PSTContext pstContext;
    private List<TopicoBean> topicoList;
    private ListView topicoListView;
    private ProgressDialog progressBar;
    private TipoBean tipoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topico);

        pstContext = (PSTContext) getApplication();

        TextView textViewTipo = findViewById(R.id.textViewTipo);
        Button buttonAvancaTopico = findViewById(R.id.buttonAvancaTopico);
        Button buttonAtualTopico = findViewById(R.id.buttonAtualTopico);
        Button buttonRetTopico = findViewById(R.id.buttonRetTopico);
        Button buttonCancAbord = findViewById(R.id.buttonCancAbord);

        buttonAtualTopico.setOnClickListener(v -> {

            AlertDialog.Builder alerta = new AlertDialog.Builder( TopicoActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

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

                    AlertDialog.Builder alerta1 = new AlertDialog.Builder( TopicoActivity.this);
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

        tipoBean = pstContext.getAbordagemCTR().getTipo(pstContext.getPosTipo() - 1);
        textViewTipo.setText(tipoBean.getDescrTipo());
        topicoList = pstContext.getAbordagemCTR().topicoList(tipoBean.getIdTipo());

        ArrayList<String> itens = new ArrayList<>();

        for(TopicoBean topicoBean : topicoList){
            itens.add(topicoBean.getDescrTopico());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        topicoListView = findViewById(R.id.listTopico);
        topicoListView.setAdapter(adapterList);

        topicoListView.setOnItemClickListener((l, v, position, id) -> {

            TopicoBean topicoBean = topicoList.get(position);
            pstContext.setIdTopico(topicoBean.getIdTopico());
            topicoList.clear();

            Intent it = new Intent(TopicoActivity.this, QuestaoActivity.class);
            startActivity(it);
            finish();

        });

        buttonAvancaTopico.setOnClickListener(v -> {

            if(pstContext.getAbordagemCTR().verItemCabec(tipoBean)){
                if(topicoList.size() == pstContext.getPosTipo()){
                    Intent it = new Intent(TopicoActivity.this, CameraActivity.class);
                    startActivity(it);
                } else {
                    pstContext.setPosTipo(pstContext.getPosTipo() + 1);
                    Intent it = new Intent(TopicoActivity.this, TopicoActivity.class);
                    startActivity(it);
                }
                finish();
                topicoList.clear();
            } else {
                AlertDialog.Builder alerta = new AlertDialog.Builder(TopicoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("ABORDAGEM SEM NENHUM TÓPICO PREENCHIDO! POR FAVOR, PREENCHA ALGUM TÓPICO PARA TERMINAR A ABORDAGEM.");
                alerta.setPositiveButton("OK", (dialog, which) -> {
                });
                alerta.show();
            }
        });

        buttonRetTopico.setOnClickListener(v -> {
            if(pstContext.getPosTipo() > 1){
                pstContext.setPosTipo(pstContext.getPosTipo() - 1);
                Intent it = new Intent(TopicoActivity.this, TopicoActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonCancAbord.setOnClickListener(v -> {

            AlertDialog.Builder alerta = new AlertDialog.Builder(TopicoActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ABANDONA A ABORDAGEM?");
            alerta.setPositiveButton("SIM", (dialog, which) -> {
                pstContext.getAbordagemCTR().clearBD();
                Intent it = new Intent(TopicoActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            });

            alerta.setNegativeButton("NÃO", (dialog, which) -> {
            });

            alerta.show();

        });

    }

    public void onBackPressed()  {
    }

}
