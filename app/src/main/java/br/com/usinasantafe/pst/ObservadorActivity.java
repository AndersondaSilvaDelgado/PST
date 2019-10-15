package br.com.usinasantafe.pst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pst.bean.estaticas.ColabBean;
import br.com.usinasantafe.pst.bean.variaveis.ConfigBean;

public class ObservadorActivity extends ActivityGeneric {

    private TextView textViewObservador;
    private PSTContext pstContext;
    private Long matricFuncApar;
    private Long matricFuncObs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observador);

        textViewObservador = (TextView) findViewById(R.id.textViewObservador);
        Button buttonAvancaObservador = (Button) findViewById(R.id.buttonAvancaObservador);
        Button buttonRetObservador = (Button) findViewById(R.id.buttonRetObservador);
        Button buttonAlterarObservador = (Button) findViewById(R.id.buttonAlterarObservador);

        pstContext = (PSTContext) getApplication();

        ConfigBean configBean = new ConfigBean();
        List listConfigTO = configBean.all();
        configBean = (ConfigBean) listConfigTO.get(0);
        listConfigTO.clear();

        if(pstContext.getObservCTR().getMatricFuncObsForm() == 0){
            pstContext.getObservCTR().setMatricFuncObsForm(configBean.getMatricFuncConfig());
        }

        pstContext.getObservCTR().setMatricFuncAparForm(configBean.getMatricFuncConfig());

        ColabBean colabBean = new ColabBean();
        List colabList = colabBean.get("matricColab", pstContext.getObservCTR().getMatricFuncObsForm());
        colabBean = (ColabBean) colabList.get(0);
        colabList.clear();

        textViewObservador.setText(colabBean.getMatricColab() + " - " + colabBean.getNomeColab());

        buttonAlterarObservador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ObservadorActivity.this, ObservadorDigActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonAvancaObservador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ObservadorActivity.this, ListaAreaActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonRetObservador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ObservadorActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed() {
    }

}
