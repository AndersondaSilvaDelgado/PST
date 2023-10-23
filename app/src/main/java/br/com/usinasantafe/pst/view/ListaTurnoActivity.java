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
import br.com.usinasantafe.pst.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pst.util.ConexaoWeb;

public class ListaTurnoActivity extends ActivityGeneric {

    private List<TurnoBean> turnoList;
    private PSTContext pstContext;
    private ListView turnoListView;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turno);

        pstContext = (PSTContext) getApplication();

        Button buttonRetTurno = findViewById(R.id.buttonRetTurno);
        Button buttonAtualTurno = findViewById(R.id.buttonAtualTurno);

        buttonAtualTurno.setOnClickListener(v -> {

            AlertDialog.Builder alerta = new AlertDialog.Builder( ListaTurnoActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaTurnoActivity.this)) {

                    progressBar = new ProgressDialog(ListaTurnoActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    pstContext.getAbordagemCTR().atualDadosTurno(ListaTurnoActivity.this, ListaTurnoActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta1 = new AlertDialog.Builder( ListaTurnoActivity.this);
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

        turnoList = pstContext.getAbordagemCTR().turnoList();

        ArrayList<String> itens = new ArrayList<>();

        for (TurnoBean turnoBean : turnoList) {
            itens.add(turnoBean.getDescrTurno());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        turnoListView = findViewById(R.id.listTurno);
        turnoListView.setAdapter(adapterList);

        turnoListView.setOnItemClickListener((l, v, position, id) -> {

            TurnoBean turnoBean = turnoList.get(position);
            pstContext.getAbordagemCTR().setIdTurno(turnoBean.getIdTurno());
            turnoList.clear();

            Intent it = new Intent(ListaTurnoActivity.this, DetalhesActivity.class);
            startActivity(it);
            finish();

        });

        buttonRetTurno.setOnClickListener(v -> {
            Intent it = new Intent(ListaTurnoActivity.this, ListaSubAreaActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed()  {
    }

}
