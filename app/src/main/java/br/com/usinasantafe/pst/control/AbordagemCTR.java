package br.com.usinasantafe.pst.control;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.List;

import br.com.usinasantafe.pst.bean.dao.CabAbordDAO;
import br.com.usinasantafe.pst.bean.dao.FotoAbordDAO;
import br.com.usinasantafe.pst.bean.dao.ItemAbordDAO;
import br.com.usinasantafe.pst.bean.variaveis.CabAbordBean;
import br.com.usinasantafe.pst.bean.variaveis.FotoAbordBean;
import br.com.usinasantafe.pst.bean.variaveis.ItemAbordBean;

public class AbordagemCTR {

    private CabAbordBean cabAbordBean;

    public AbordagemCTR() {
        if (cabAbordBean == null)
            cabAbordBean = new CabAbordBean();
    }

    public void setMatricFuncAparForm(Long matricFuncAparForm) {
        this.cabAbordBean.setMatricFuncAparCabAbord(matricFuncAparForm);
    }

    public void setMatricFuncObsForm(Long matricFuncObsForm) {
        this.cabAbordBean.setMatricFuncCabAbord(matricFuncObsForm);
    }

    public Long getMatricFuncObsForm(){
        return this.cabAbordBean.getMatricFuncCabAbord();
    }

    public void setIdAreaForm(Long idAreaForm){
        this.cabAbordBean.setIdAreaCabAbord(idAreaForm);
    }

    public Long getIdAreaForm(){
        return this.cabAbordBean.getIdAreaCabAbord();
    }

    public void setIdSubAreaForm(Long idSubAreaForm){
        this.cabAbordBean.setIdSubAreaCabAbord(idSubAreaForm);
    }

    public void setIdTurno(Long idTurno){
        this.cabAbordBean.setIdTurnoCabAbord(idTurno);
    }

    public void setDetalhesCabAbord(Long extensaoMinCabAbord, Long pessoasContCabAbord, Long pessoasObsCabAbord){
        this.cabAbordBean.setExtensaoMinCabAbord(extensaoMinCabAbord);
        this.cabAbordBean.setPessoasContCabAbord(pessoasContCabAbord);
        this.cabAbordBean.setPessoasObsCabAbord(pessoasObsCabAbord);
    }

    public void setComentarioCabAbord(Long tipoCabAbord, String comentarioCabAbord){
        this.cabAbordBean.setTipoCabAbord(tipoCabAbord);
        this.cabAbordBean.setComentCabAbord(comentarioCabAbord);
    }

    public boolean verEnvioDadosCabec() {
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        return cabAbordDAO.cabecFechList().size() > 0;
    }

    public void salvarCabecAberto(){
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        cabAbordDAO.salvarCabecAbert(cabAbordBean);
    }

    public void salvarItem(Long idQuestao, Long seguro, Long inseguro){
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        CabAbordBean cabAbordBean = cabAbordDAO.getCabecAbert();
        ItemAbordBean itemAbordBean = new ItemAbordBean();
        itemAbordBean.setIdCabItemAbord(cabAbordBean.getIdCabAbord());
        itemAbordBean.setIdQuestaoItemAbord(idQuestao);
        itemAbordBean.setQtdeSegItemAbord(seguro);
        itemAbordBean.setQtdeInsegItemAbord(inseguro);
        ItemAbordDAO itemAbordDAO = new ItemAbordDAO();
        itemAbordDAO.salvarItem(itemAbordBean);
    }

    public boolean verItemCabecAbert(Long idQuestao){
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        CabAbordBean cabAbordBean = cabAbordDAO.getCabecAbert();
        ItemAbordDAO itemAbordDAO = new ItemAbordDAO();
        return itemAbordDAO.verItemCabecAbert(cabAbordBean.getIdCabAbord(), idQuestao);
    }

    public ItemAbordBean getItemCabecAbert(Long idQuestao){
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        CabAbordBean cabAbordBean = cabAbordDAO.getCabecAbert();
        ItemAbordDAO itemAbordDAO = new ItemAbordDAO();
        return itemAbordDAO.getItemCabecAberts(cabAbordBean.getIdCabAbord(), idQuestao);
    }

    public FotoAbordBean salvarFoto(File file){
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        CabAbordBean cabAbordBean = cabAbordDAO.getCabecAbert();
        FotoAbordDAO fotoAbordDAO = new FotoAbordDAO();
        return fotoAbordDAO.salvarFoto(cabAbordBean.getIdCabAbord(), file);
    }

    public List getListFotoCabecAbert(){
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        CabAbordBean cabAbordBean = cabAbordDAO.getCabecAbert();
        FotoAbordDAO fotoAbordDAO = new FotoAbordDAO();
        return fotoAbordDAO.getListFotoCabecAbert(cabAbordBean.getIdCabAbord());
    }

    public void salvaBolFechado(){
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        CabAbordBean cabAbordBean = cabAbordDAO.getCabecAbert();
        cabAbordDAO.salvarCabecFech(cabAbordBean);
    }

    public String dadosCabecFechEnvio() {
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        CabAbordBean cabAbordBean = (CabAbordBean) cabAbordDAO.cabecFechList().get(0);
        JsonArray jsonArrayCabec = new JsonArray();
        Gson gsonCabec = new Gson();
        jsonArrayCabec.add(gsonCabec.toJsonTree(cabAbordBean, cabAbordBean.getClass()));
        JsonObject jsonCabec = new JsonObject();
        jsonCabec.add("cabec", jsonArrayCabec);
        return jsonCabec.toString();
    }

    public String dadosItemFechEnvio() {
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        ItemAbordDAO itemAbordDAO = new ItemAbordDAO();
        CabAbordBean cabAbordBean = (CabAbordBean) cabAbordDAO.cabecFechList().get(0);
        List itemAbordList = itemAbordDAO.getListItemCabecFech(cabAbordBean.getIdCabAbord());

        JsonArray jsonArrayItem = new JsonArray();

        for (int i = 0; i < itemAbordList.size(); i++) {
            ItemAbordBean itemAbordBean = (ItemAbordBean) itemAbordList.get(i);
            Gson gsonRend = new Gson();
            jsonArrayItem.add(gsonRend.toJsonTree(itemAbordBean, itemAbordBean.getClass()));
        }

        JsonObject jsonItem = new JsonObject();
        jsonItem.add("item", jsonArrayItem);
        return jsonItem.toString();
    }

    public String getFoto(int pos){
        CabAbordDAO cabAbordDAO = new CabAbordDAO();
        CabAbordBean cabAbordBean = (CabAbordBean) cabAbordDAO.cabecFechList().get(0);
        FotoAbordDAO fotoAbordDAO = new FotoAbordDAO();
        List fotoAbordList = fotoAbordDAO.getListFotoCabecAbert(cabAbordBean.getIdCabAbord());

        if(fotoAbordList.size() >= pos) {
            FotoAbordBean fotoAbordBean = (FotoAbordBean) fotoAbordList.get(pos - 1);
            return fotoAbordDAO.getBitmapString(fotoAbordBean);
        }
        else{
            return "";
        }
    }

}
