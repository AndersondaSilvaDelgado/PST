package br.com.usinasantafe.pst.bean.dao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import br.com.usinasantafe.pst.bean.variaveis.FotoAbordBean;

public class FotoAbordDAO {

    public FotoAbordDAO() {
    }

    public FotoAbordBean salvarFoto(Long idCabAbord, File file){
        FotoAbordBean fotoAbordBean = new FotoAbordBean();
        fotoAbordBean.setIdCabFotoAbord(idCabAbord);
        fotoAbordBean.setNameImage(file.getName());
        fotoAbordBean.setFileImage(file.getPath());
        fotoAbordBean.insert();
        return fotoAbordBean;
    }

    public List getListFotoCabecAbert(Long idCabec){
        FotoAbordBean fotoAbordBean = new FotoAbordBean();
        return fotoAbordBean.get("idCabFotoAbord", idCabec);
    }

    public String getBitmapString(FotoAbordBean fotoAbordBean){

        File file = new File(fotoAbordBean.getFileImage());
        Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 95, stream);

        byte[] byteArray = stream.toByteArray();

        return(Base64.encodeToString(byteArray, Base64.DEFAULT));
    }

}