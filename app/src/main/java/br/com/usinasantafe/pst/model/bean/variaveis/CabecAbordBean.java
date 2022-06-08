package br.com.usinasantafe.pst.model.bean.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

import br.com.usinasantafe.pst.model.pst.Entidade;

@DatabaseTable(tableName="tbcabecabordvar")
public class CabecAbordBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idCabecAbord;
    @DatabaseField
    private Long matricFuncCabecAbord;
    @DatabaseField
    private Long idAreaCabecAbord;
    @DatabaseField
    private Long idSubAreaCabecAbord;
    @DatabaseField
    private Long idTurnoCabecAbord;
    @DatabaseField
    private Long extensaoMinCabecAbord;
    @DatabaseField
    private Long pessoasContCabecAbord;
    @DatabaseField
    private Long pessoasObsCabecAbord;
    @DatabaseField
    private Long tipoCabecAbord;
    @DatabaseField
    private String comentCabecAbord;
    @DatabaseField
    private String dthrCabecAbord;
    @DatabaseField
    private Long statusCabecAbord;
    private List<ItemAbordBean> itemAbordBeanList;
    private List<FotoAbordBean> fotoAbordBeanList;

    public CabecAbordBean() {
    }

    public Long getIdCabecAbord() {
        return idCabecAbord;
    }

    public void setIdCabecAbord(Long idCabecAbord) {
        this.idCabecAbord = idCabecAbord;
    }

    public Long getMatricFuncCabecAbord() {
        return matricFuncCabecAbord;
    }

    public void setMatricFuncCabecAbord(Long matricFuncCabecAbord) {
        this.matricFuncCabecAbord = matricFuncCabecAbord;
    }

    public Long getIdAreaCabecAbord() {
        return idAreaCabecAbord;
    }

    public void setIdAreaCabecAbord(Long idAreaCabecAbord) {
        this.idAreaCabecAbord = idAreaCabecAbord;
    }

    public Long getIdSubAreaCabecAbord() {
        return idSubAreaCabecAbord;
    }

    public void setIdSubAreaCabecAbord(Long idSubAreaCabecAbord) {
        this.idSubAreaCabecAbord = idSubAreaCabecAbord;
    }

    public Long getIdTurnoCabecAbord() {
        return idTurnoCabecAbord;
    }

    public void setIdTurnoCabecAbord(Long idTurnoCabecAbord) {
        this.idTurnoCabecAbord = idTurnoCabecAbord;
    }

    public Long getExtensaoMinCabecAbord() {
        return extensaoMinCabecAbord;
    }

    public void setExtensaoMinCabecAbord(Long extensaoMinCabecAbord) {
        this.extensaoMinCabecAbord = extensaoMinCabecAbord;
    }

    public Long getPessoasContCabecAbord() {
        return pessoasContCabecAbord;
    }

    public void setPessoasContCabecAbord(Long pessoasContCabecAbord) {
        this.pessoasContCabecAbord = pessoasContCabecAbord;
    }

    public Long getPessoasObsCabecAbord() {
        return pessoasObsCabecAbord;
    }

    public void setPessoasObsCabecAbord(Long pessoasObsCabecAbord) {
        this.pessoasObsCabecAbord = pessoasObsCabecAbord;
    }

    public Long getTipoCabecAbord() {
        return tipoCabecAbord;
    }

    public void setTipoCabecAbord(Long tipoCabecAbord) {
        this.tipoCabecAbord = tipoCabecAbord;
    }

    public String getComentCabecAbord() {
        return comentCabecAbord;
    }

    public void setComentCabecAbord(String comentCabecAbord) {
        this.comentCabecAbord = comentCabecAbord;
    }

    public String getDthrCabecAbord() {
        return dthrCabecAbord;
    }

    public void setDthrCabecAbord(String dthrCabecAbord) {
        this.dthrCabecAbord = dthrCabecAbord;
    }

    public Long getStatusCabecAbord() {
        return statusCabecAbord;
    }

    public void setStatusCabecAbord(Long statusCabecAbord) {
        this.statusCabecAbord = statusCabecAbord;
    }

    public List<ItemAbordBean> getItemAbordBeanList() {
        return itemAbordBeanList;
    }

    public void setItemAbordBeanList(List<ItemAbordBean> itemAbordBeanList) {
        this.itemAbordBeanList = itemAbordBeanList;
    }

    public List<FotoAbordBean> getFotoAbordBeanList() {
        return fotoAbordBeanList;
    }

    public void setFotoAbordBeanList(List<FotoAbordBean> fotoAbordBeanList) {
        this.fotoAbordBeanList = fotoAbordBeanList;
    }
}
