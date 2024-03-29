package br.com.usinasantafe.pst.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pst.model.pst.Entidade;

@DatabaseTable(tableName="tbitemabordvar")
public class ItemAbordBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idItemAbord;
    @DatabaseField
    private Long idCabecItemAbord;
    @DatabaseField
    private Long idQuestaoItemAbord;
    @DatabaseField
    private Long qtdeInsegItemAbord;
    @DatabaseField
    private Long qtdeSegItemAbord;
    @DatabaseField
    private String dthrItemAbord;

    public ItemAbordBean() {
    }

    public Long getIdItemAbord() {
        return idItemAbord;
    }

    public void setIdItemAbord(Long idItemAbord) {
        this.idItemAbord = idItemAbord;
    }

    public Long getIdCabecItemAbord() {
        return idCabecItemAbord;
    }

    public void setIdCabecItemAbord(Long idCabecItemAbord) {
        this.idCabecItemAbord = idCabecItemAbord;
    }

    public Long getIdQuestaoItemAbord() {
        return idQuestaoItemAbord;
    }

    public void setIdQuestaoItemAbord(Long idQuestaoItemAbord) {
        this.idQuestaoItemAbord = idQuestaoItemAbord;
    }

    public Long getQtdeInsegItemAbord() {
        return qtdeInsegItemAbord;
    }

    public void setQtdeInsegItemAbord(Long qtdeInsegItemAbord) {
        this.qtdeInsegItemAbord = qtdeInsegItemAbord;
    }

    public Long getQtdeSegItemAbord() {
        return qtdeSegItemAbord;
    }

    public void setQtdeSegItemAbord(Long qtdeSegItemAbord) {
        this.qtdeSegItemAbord = qtdeSegItemAbord;
    }

    public String getDthrItemAbord() {
        return dthrItemAbord;
    }

    public void setDthrItemAbord(String dthrItemAbord) {
        this.dthrItemAbord = dthrItemAbord;
    }

}
