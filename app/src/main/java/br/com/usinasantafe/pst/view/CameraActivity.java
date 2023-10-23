package br.com.usinasantafe.pst.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.usinasantafe.pst.PSTContext;
import br.com.usinasantafe.pst.R;
import br.com.usinasantafe.pst.model.bean.variaveis.FotoAbordBean;
import br.com.usinasantafe.pst.util.EnvioDadosServ;

public class CameraActivity extends ActivityGeneric {

    private RecyclerView mRecyclerView;
    private List<FotoAbordBean> fotoAbordList;
    private PSTContext pstContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        pstContext = (PSTContext) getApplication();

        Button buttonCapturaFoto = findViewById(R.id.buttonCapturaFoto);
        Button buttonAvancaFoto = findViewById(R.id.buttonAvancaFoto);
        Button buttonRetFoto = findViewById(R.id.buttonRetFoto);

        mRecyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(CameraActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        fotoAbordList = pstContext.getAbordagemCTR().getListFotoCabecAbert();

        AdapterListFoto adapterListFoto = new AdapterListFoto(CameraActivity.this, fotoAbordList);
        mRecyclerView.setAdapter(adapterListFoto);

        buttonCapturaFoto.setOnClickListener(v -> tirarFoto());

        buttonAvancaFoto.setOnClickListener(v -> {

            pstContext.getAbordagemCTR().salvaBolFechado();
            EnvioDadosServ.getInstance().envioDados(CameraActivity.this);

            Intent it = new Intent(CameraActivity.this, MenuInicialActivity.class);
            startActivity(it);
            finish();
        });

        buttonRetFoto.setOnClickListener(v -> {
            Intent it = new Intent(CameraActivity.this, TopicoActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void tirarFoto(){
        if(fotoAbordList.size() < 4){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 1);
        }
        else{
            AlertDialog.Builder alerta = new AlertDialog.Builder(CameraActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("CADA ABORDAGEM PODEM TER APENAS 4 FOTOS. POR FAVOR, EXCLUA UMA FOTO PARA PODE TIRA UMA NOVA FOTO.");
            alerta.setPositiveButton("OK", (dialog, which) -> {
            });
            alerta.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 1 && resultCode == RESULT_OK){

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            fotoAbordList.add(pstContext.getAbordagemCTR().salvarFoto(bitmap));

            Intent it = new Intent(CameraActivity.this, CameraActivity.class);
            startActivity(it);
            finish();

        }

    }

    public void onBackPressed() {
    }

}
