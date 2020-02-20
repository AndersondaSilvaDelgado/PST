package br.com.usinasantafe.pst;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.net.URI;
import java.util.List;

import br.com.usinasantafe.pst.util.EnvioDadosServ;

public class CameraActivity extends ActivityGeneric {

    private RecyclerView mRecyclerView;
    private List fotoAbordList;
    private PSTContext pstContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        pstContext = (PSTContext) getApplication();

        Button buttonCapturaFoto = (Button) findViewById(R.id.buttonCapturaFoto);
        Button buttonAvancaFoto = (Button) findViewById(R.id.buttonAvancaFoto);
        Button buttonRetFoto = (Button) findViewById(R.id.buttonRetFoto);

        buttonCapturaFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tirarFoto();
            }
        });

        mRecyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(CameraActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        fotoAbordList = pstContext.getAbordagemCTR().getListFotoCabecAbert();

        AdapterListFoto adapterListFoto = new AdapterListFoto(CameraActivity.this, fotoAbordList);
        mRecyclerView.setAdapter(adapterListFoto);

        buttonAvancaFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                pstContext.getAbordagemCTR().salvaBolFechado();
                EnvioDadosServ.getInstance().envioDados(CameraActivity.this);

                Intent it = new Intent(CameraActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonRetFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(CameraActivity.this, TopicoActivity.class);
                startActivity(it);
                finish();
            }

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
            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alerta.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 1 && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            fotoAbordList.add(pstContext.getAbordagemCTR().salvarFoto(bitmap));
        }

    }

    public void onBackPressed() {
    }

}
