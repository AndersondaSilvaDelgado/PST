package br.com.usinasantafe.pst.model.dao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.List;

import br.com.usinasantafe.pst.model.bean.variaveis.FotoAbordBean;
import br.com.usinasantafe.pst.util.Tempo;

public class FotoAbordDAO {

    public FotoAbordDAO() {
    }

    public FotoAbordBean salvarFoto(Long idCabAbord, Bitmap bitmap){
        FotoAbordBean fotoAbordBean = new FotoAbordBean();
        fotoAbordBean.setIdCabecFotoAbord(idCabAbord);
        fotoAbordBean.setFotoAbord(getBitmapString(bitmap));
        fotoAbordBean.setDthrFotoAbord(Tempo.getInstance().dataComHora());
        fotoAbordBean.insert();
        return fotoAbordBean;
    }

    public List<FotoAbordBean> getListFotoCabec(Long idCabec){
        FotoAbordBean fotoAbordBean = new FotoAbordBean();
        return fotoAbordBean.get("idCabecFotoAbord", idCabec);
    }

    private String getBitmapString(Bitmap foto){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        foto.compress(Bitmap.CompressFormat.JPEG, 95, stream);

        byte[] byteArray = stream.toByteArray();

        return(Base64.encodeToString(byteArray, Base64.DEFAULT));

    }

    public Bitmap getStringBitmap(String foto){

        try{
            byte [] encodeByte= Base64.decode(foto ,Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        }catch(Exception e){
            Log.i("PST", "Erro = " + e.toString());
            return null;
        }

    }

    public void delFotoCabec(Long idCabec){
        FotoAbordBean fotoAbordBean = new FotoAbordBean();
        List fotoAbordList = fotoAbordBean.get("idCabecFotoAbord", idCabec);
        for (int i = 0; i < fotoAbordList.size(); i++) {
            fotoAbordBean = (FotoAbordBean) fotoAbordList.get(i);
            fotoAbordBean.delete();
        }
    }

}
