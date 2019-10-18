package br.com.usinasantafe.pst.bean.variaveis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.ByteArrayOutputStream;
import java.io.File;

import br.com.usinasantafe.pst.pst.Entidade;

@DatabaseTable(tableName="tbfotoabordvar")
public class FotoAbordBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idFotoAbord;
    @DatabaseField
    private Long idCabFotoAbord;
    @DatabaseField
    private String nameImage;
    @DatabaseField
    private String fileImage;

    public FotoAbordBean() {
    }

    public Long getIdFotoAbord() {
        return idFotoAbord;
    }

    public void setIdFotoAbord(Long idFotoAbord) {
        this.idFotoAbord = idFotoAbord;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public String getFileImage() {
        return fileImage;
    }

    public void setFileImage(String fileImage) {
        this.fileImage = fileImage;
    }

    public Long getIdCabFotoAbord() {
        return idCabFotoAbord;
    }

    public void setIdCabFotoAbord(Long idCabFotoAbord) {
        this.idCabFotoAbord = idCabFotoAbord;
    }
}
