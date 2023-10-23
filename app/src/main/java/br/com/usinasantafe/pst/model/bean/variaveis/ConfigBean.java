package br.com.usinasantafe.pst.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pst.model.pst.Entidade;

@DatabaseTable(tableName="tbconfigvar")
public class ConfigBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long nroAparelhoConfig;

    public ConfigBean() {
    }

    public Long getNroAparelhoConfig() {
        return nroAparelhoConfig;
    }

    public void setNroAparelhoConfig(Long nroAparelhoConfig) {
        this.nroAparelhoConfig = nroAparelhoConfig;
    }
}
