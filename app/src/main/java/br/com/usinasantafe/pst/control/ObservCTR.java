package br.com.usinasantafe.pst.control;

import br.com.usinasantafe.pst.bean.dao.ObservDAO;
import br.com.usinasantafe.pst.bean.variaveis.CabObservBean;

public class ObservCTR {

    private CabObservBean cabObservBean;

    public ObservCTR() {
        if (cabObservBean == null)
            cabObservBean = new CabObservBean();
    }

    public void setMatricFuncAparForm(Long matricFuncAparForm) {
        this.cabObservBean.setMatricFuncAparCabObserv(matricFuncAparForm);
    }

    public void setMatricFuncObsForm(Long matricFuncObsForm) {
        this.cabObservBean.setMatricFuncCabObserv(matricFuncObsForm);
    }

    public Long getMatricFuncObsForm(){
        return this.cabObservBean.getMatricFuncCabObserv();
    }

    public void setIdAreaForm(Long idAreaForm){
        this.cabObservBean.setIdAreaCabObserv(idAreaForm);
    }

    public Long getIdAreaForm(){
        return this.cabObservBean.getIdAreaCabObserv();
    }

    public void setIdSubAreaForm(Long idSubAreaForm){
        this.cabObservBean.setIdSubAreaCabObserv(idSubAreaForm);
    }

    public boolean verEnvioDadosCabec() {
        ObservDAO observDAO = new ObservDAO();
        return observDAO.cabFechList().size() > 0;
    }

}
