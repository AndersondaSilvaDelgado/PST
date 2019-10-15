package br.com.usinasantafe.pst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pst.bean.estaticas.AreaBean;

public class ListaAreaActivity extends ActivityGeneric {

    private List areaList;
    private PSTContext pstContext;
    private ListView areaListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_area);

        pstContext = (PSTContext) getApplication();

        Button buttonRetArea = (Button) findViewById(R.id.buttonRetArea);

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
                pstContext.getObservCTR().setIdAreaForm(subAreaBean.getIdArea());
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
