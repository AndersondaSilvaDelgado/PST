package br.com.usinasantafe.pst.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pst.pst.Entidade;

@DatabaseTable(tableName="tbcabobsvar")
public class CabObservBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabObserv;
    @DatabaseField
    private Long matricFuncAparCabObserv;
    @DatabaseField
    private Long matricFuncCabObserv;
    @DatabaseField
    private Long idAreaCabObserv;
    @DatabaseField
    private Long idSubAreaCabObserv;
    @DatabaseField
    private Long idTurnoCabObserv;
    @DatabaseField
    private Long statusCabObserv;

    public CabObservBean() {
    }

    public Long getIdCabObserv() {
        return idCabObserv;
    }

    public void setIdCabObserv(Long idCabObserv) {
        this.idCabObserv = idCabObserv;
    }

    public Long getMatricFuncAparCabObserv() {
        return matricFuncAparCabObserv;
    }

    public void setMatricFuncAparCabObserv(Long matricFuncAparCabObserv) {
        this.matricFuncAparCabObserv = matricFuncAparCabObserv;
    }

    public Long getMatricFuncCabObserv() {
        return matricFuncCabObserv;
    }

    public void setMatricFuncCabObserv(Long matricFuncCabObserv) {
        this.matricFuncCabObserv = matricFuncCabObserv;
    }

    public Long getIdAreaCabObserv() {
        return idAreaCabObserv;
    }

    public void setIdAreaCabObserv(Long idAreaCabObserv) {
        this.idAreaCabObserv = idAreaCabObserv;
    }

    public Long getIdSubAreaCabObserv() {
        return idSubAreaCabObserv;
    }

    public void setIdSubAreaCabObserv(Long idSubAreaCabObserv) {
        this.idSubAreaCabObserv = idSubAreaCabObserv;
    }

    public Long getIdTurnoCabObserv() {
        return idTurnoCabObserv;
    }

    public void setIdTurnoCabObserv(Long idTurnoCabObserv) {
        this.idTurnoCabObserv = idTurnoCabObserv;
    }

    public Long getStatusCabObserv() {
        return statusCabObserv;
    }

    public void setStatusCabObserv(Long statusCabObserv) {
        this.statusCabObserv = statusCabObserv;
    }
}
