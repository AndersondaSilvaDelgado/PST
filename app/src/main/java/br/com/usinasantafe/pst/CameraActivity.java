package br.com.usinasantafe.pst;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pst.bean.variaveis.ImagemObservBean;

public class CameraActivity extends ActivityGeneric {

    RecyclerView mRecyclerView;
    List<ImagemObservBean> imagemObservList;
    ImagemObservBean imagemObservBean;
    private File[] listFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Button buttonCapturaFoto = (Button) findViewById(R.id.buttonCapturaFoto);

        buttonCapturaFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tirarFoto();
            }
        });

        mRecyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(CameraActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        File file = new File(android.os.Environment.getExternalStorageDirectory()
                + File.separator + "DCIM"
                + File.separator + "Camera");

        imagemObservList = new ArrayList<>();

        if (file.isDirectory()) {
            listFile = file.listFiles();
            for (int i = 0; i < listFile.length; i++) {
                // Get the name image file
                File fileFoto = listFile[i];
                imagemObservBean = new ImagemObservBean(fileFoto);
                imagemObservList.add(imagemObservBean);
            }
        }

        AdapterListFoto adapterListFoto = new AdapterListFoto(CameraActivity.this, imagemObservList);
        mRecyclerView.setAdapter(adapterListFoto);

    }

    public void tirarFoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK){
            File file = new File(android.os.Environment.getExternalStorageDirectory()
                    + File.separator + "DCIM"
                    + File.separator + "Camera");

            if (file.isDirectory()) {
                listFile = file.listFiles();
                File fileUltFoto = listFile[listFile.length - 1];
                imagemObservBean = new ImagemObservBean(fileUltFoto);
                imagemObservList.add(imagemObservBean);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onBackPressed() {
    }

}
