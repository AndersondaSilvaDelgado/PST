package br.com.usinasantafe.pst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pst.bean.estaticas.TurnoBean;

public class ListaTurnoActivity extends ActivityGeneric {

    private List turnoList;
    private PSTContext pstContext;
    private ListView turnoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turno);

        pstContext = (PSTContext) getApplication();

        Button buttonRetTurno = (Button) findViewById(R.id.buttonRetTurno);
        Button buttonAtualTurno = (Button) findViewById(R.id.buttonAtualTurno);

        TurnoBean turnoBean = new TurnoBean();
        turnoList = turnoBean.all();

        ArrayList<String> itens = new ArrayList<String>();

        for (int i = 0; i < turnoList.size(); i++) {
            turnoBean = (TurnoBean) turnoList.get(i);
            itens.add(turnoBean.getDescrTurno());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        turnoListView = (ListView) findViewById(R.id.listTurno);
        turnoListView.setAdapter(adapterList);

        turnoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TurnoBean turnoBean = (TurnoBean) turnoList.get(position);
                pstContext.getObservCTR().setIdAreaForm(turnoBean.getIdTurno());
                turnoList.clear();

                Intent it = new Intent(ListaTurnoActivity.this, DetalhesActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaTurnoActivity.this, ListaSubAreaActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}
