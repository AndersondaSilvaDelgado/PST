package br.com.usinasantafe.pst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pst.bean.estaticas.TipoBean;
import br.com.usinasantafe.pst.bean.estaticas.TopicoBean;

public class TopicoActivity extends ActivityGeneric {

    private PSTContext pstContext;
    private List topicoList;
    private ListView topicoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topico);

        pstContext = (PSTContext) getApplication();

        TextView textViewTipo = (TextView) findViewById(R.id.textViewTipo);
        Button buttonAvancaTopico = (Button) findViewById(R.id.buttonAvancaTopico);

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

    }

    public void onBackPressed()  {
    }

}
