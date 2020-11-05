package br.com.usinasantafe.pst.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pst.model.pst.Entidade;

@DatabaseTable(tableName="tbtipoobsest")
public class TipoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idTipo;
    @DatabaseField
    private String descrTipo;
    @DatabaseField
    private Long flagTipo;

    public TipoBean() {
    }

    public Long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Long idTipo) {
        this.idTipo = idTipo;
    }

    public String getDescrTipo() {
        return descrTipo;
    }

    public void setDescrTipo(String descrTipo) {
        this.descrTipo = descrTipo;
    }

    public Long getFlagTipo() {
        return flagTipo;
    }

    public void setFlagTipo(Long flagTipo) {
        this.flagTipo = flagTipo;
    }
}
