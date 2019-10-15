package br.com.usinasantafe.pst.bean.variaveis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class ImagemObservBean {

    private File fileImage;
    private Bitmap image;

    public ImagemObservBean() {
    }

    public ImagemObservBean(File fileImage) {
        this.fileImage = fileImage;
        this.image = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(fileImage.getPath()),
                192, 192);
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public File getFileImage() {
        return fileImage;
    }

    public void setFileImage(File fileImage) {
        this.fileImage = fileImage;
    }
}
