package br.com.usinasantafe.pst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pst.bean.estaticas.SubAreaBean;

public class ListaSubAreaActivity extends ActivityGeneric {

    private PSTContext pstContext;
    private List subAreaList;
    private ListView subAreaListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sub_area);

        pstContext = (PSTContext) getApplication();

        Button buttonRetSubArea = (Button) findViewById(R.id.buttonRetSubArea);

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
