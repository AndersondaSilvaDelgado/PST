package br.com.usinasantafe.pst.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pst.model.pst.Entidade;

@DatabaseTable(tableName="tbfotoabordvar")
public class FotoAbordBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idFotoAbord;
    @DatabaseField
    private Long idCabecFotoAbord;
    @DatabaseField
    private String fotoAbord;
    @DatabaseField
    private String dthrFotoAbord;

    public FotoAbordBean() {
    }

    public Long getIdFotoAbord() {
        return idFotoAbord;
    }

    public void setIdFotoAbord(Long idFotoAbord) {
        this.idFotoAbord = idFotoAbord;
    }

    public Long getIdCabecFotoAbord() {
        return idCabecFotoAbord;
    }

    public void setIdCabecFotoAbord(Long idCabecFotoAbord) {
        this.idCabecFotoAbord = idCabecFotoAbord;
    }

    public String getDthrFotoAbord() {
        return dthrFotoAbord;
    }

    public void setDthrFotoAbord(String dthrFotoAbord) {
        this.dthrFotoAbord = dthrFotoAbord;
    }

    public String getFotoAbord() {
        return fotoAbord;
    }

    public void setFotoAbord(String fotoAbord) {
        this.fotoAbord = fotoAbord;
    }
}
