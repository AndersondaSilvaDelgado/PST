package br.com.usinasantafe.pst.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbitemobsvar")
public class ItemObservBean {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idItemObserv;
    @DatabaseField
    private Long idCabItemObserv;
    @DatabaseField
    private Long idQuestaoItemObserv;
    @DatabaseField
    private Long qtdeInsegItemObserv;
    @DatabaseField
    private Long qtdeSegItemObserv;
    @DatabaseField
    private String descrItemObserv;

    public ItemObservBean() {
    }

    public Long getIdItemObserv() {
        return idItemObserv;
    }

    public void setIdItemObserv(Long idItemObserv) {
        this.idItemObserv = idItemObserv;
    }

    public Long getIdCabItemObserv() {
        return idCabItemObserv;
    }

    public void setIdCabItemObserv(Long idCabItemObserv) {
        this.idCabItemObserv = idCabItemObserv;
    }

    public Long getIdQuestaoItemObserv() {
        return idQuestaoItemObserv;
    }

    public void setIdQuestaoItemObserv(Long idQuestaoItemObserv) {
        this.idQuestaoItemObserv = idQuestaoItemObserv;
    }

    public Long getQtdeInsegItemObserv() {
        return qtdeInsegItemObserv;
    }

    public void setQtdeInsegItemObserv(Long qtdeInsegItemObserv) {
        this.qtdeInsegItemObserv = qtdeInsegItemObserv;
    }

    public Long getQtdeSegItemObserv() {
        return qtdeSegItemObserv;
    }

    public void setQtdeSegItemObserv(Long qtdeSegItemObserv) {
        this.qtdeSegItemObserv = qtdeSegItemObserv;
    }

    public String getDescrItemObserv() {
        return descrItemObserv;
    }

    public void setDescrItemObserv(String descrItemObserv) {
        this.descrItemObserv = descrItemObserv;
    }
}
