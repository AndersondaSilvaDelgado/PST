package br.com.usinasantafe.pst.control;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pst.model.bean.estaticas.AreaBean;
import br.com.usinasantafe.pst.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.pst.model.bean.estaticas.QuestaoBean;
import br.com.usinasantafe.pst.model.bean.estaticas.SubAreaBean;
import br.com.usinasantafe.pst.model.bean.estaticas.TipoBean;
import br.com.usinasantafe.pst.model.bean.estaticas.TopicoBean;
import br.com.usinasantafe.pst.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.pst.model.dao.AreaDAO;
import br.com.usinasantafe.pst.model.dao.CabecAbordDAO;
import br.com.usinasantafe.pst.model.dao.ColabDAO;
import br.com.usinasantafe.pst.model.dao.FotoAbordDAO;
import br.com.usinasantafe.pst.model.dao.ItemAbordDAO;
import br.com.usinasantafe.pst.model.bean.variaveis.CabecAbordBean;
import br.com.usinasantafe.pst.model.bean.variaveis.FotoAbordBean;
import br.com.usinasantafe.pst.model.bean.variaveis.ItemAbordBean;
import br.com.usinasantafe.pst.model.dao.QuestaoDAO;
import br.com.usinasantafe.pst.model.dao.SubAreaDAO;
import br.com.usinasantafe.pst.model.dao.TipoDAO;
import br.com.usinasantafe.pst.model.dao.TopicoDAO;
import br.com.usinasantafe.pst.model.dao.TurnoDAO;
import br.com.usinasantafe.pst.util.AtualDadosServ;
import br.com.usinasantafe.pst.util.EnvioDadosServ;

public class AbordagemCTR {

    private CabecAbordBean cabecAbordBean;

    public AbordagemCTR() {
        if (cabecAbordBean == null)
            cabecAbordBean = new CabecAbordBean();
    }

    public void setMatricFuncObsForm(Long matricFuncObsForm) {
        this.cabecAbordBean.setMatricFuncCabecAbord(matricFuncObsForm);
    }

    public void setIdAreaForm(Long idAreaForm){
        this.cabecAbordBean.setIdAreaCabecAbord(idAreaForm);
    }

    public Long getIdAreaForm(){
        return this.cabecAbordBean.getIdAreaCabecAbord();
    }

    public void setIdSubAreaForm(Long idSubAreaForm){
        this.cabecAbordBean.setIdSubAreaCabecAbord(idSubAreaForm);
    }

    public void setIdTurno(Long idTurno){
        this.cabecAbordBean.setIdTurnoCabecAbord(idTurno);
    }

    public void setDetalhesCabAbord(Long extensaoMinCabAbord, Long pessoasContCabAbord, Long pessoasObsCabAbord){
        this.cabecAbordBean.setExtensaoMinCabecAbord(extensaoMinCabAbord);
        this.cabecAbordBean.setPessoasContCabecAbord(pessoasContCabAbord);
        this.cabecAbordBean.setPessoasObsCabecAbord(pessoasObsCabAbord);
    }

    public void setComentarioCabAbord(Long tipoCabAbord, String comentarioCabAbord){
        this.cabecAbordBean.setTipoCabecAbord(tipoCabAbord);
        this.cabecAbordBean.setComentCabecAbord(comentarioCabAbord);
    }

    public boolean verAbordAbert() {
        CabecAbordDAO cabecAbordDAO = new CabecAbordDAO();
        return cabecAbordDAO.cabecAbertList().size() > 0;
    }

    public boolean verEnvioDados() {
        CabecAbordDAO cabecAbordDAO = new CabecAbordDAO();
        return cabecAbordDAO.cabecFechList().size() > 0;
    }

    public boolean verColab(Long matricColab){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.verColab(matricColab);
    }

    public boolean verItemCabec(TipoBean tipoBean){
        CabecAbordDAO cabecAbordDAO = new CabecAbordDAO();
        ItemAbordDAO itemAbordDAO = new ItemAbordDAO();
        TopicoDAO topicoDAO = new TopicoDAO();
        QuestaoDAO questaoDAO = new QuestaoDAO();
        TipoDAO tipoDAO = new TipoDAO();
        if(tipoBean.getFlagTipo() == 0L){
            return true;
        }
        else{
            boolean ver = false;
            List<ItemAbordBean> itemAbordList = itemAbordDAO.getListItemCabec(cabecAbordDAO.cabecAbertList().get(0).getIdCabecAbord());
            for(ItemAbordBean itemAbordBean : itemAbordList){
                QuestaoBean questaoBean = questaoDAO.getQuestao(itemAbordBean.getIdQuestaoItemAbord());
                TopicoBean topicoBean = topicoDAO.getTopico(questaoBean.getIdTopico());
                TipoBean tipoBeanBD = tipoDAO.getTipoId(topicoBean.getIdTipo());
                if(tipoBean.getIdTipo().equals(tipoBeanBD.getIdTipo())){
                    ver = true;
                }
            }
            return ver;
        }
    }

    public void salvarCabecAberto(){
        CabecAbordDAO cabecAbordDAO = new CabecAbordDAO();
        cabecAbordDAO.salvarCabecAbert(cabecAbordBean);
    }

    public void salvarItem(Long idQuestao, Long seguro, Long inseguro){
        CabecAbordDAO cabecAbordDAO = new CabecAbordDAO();
        CabecAbordBean cabecAbordBean = cabecAbordDAO.getCabecAbert();
        ItemAbordBean itemAbordBean = new ItemAbordBean();
        itemAbordBean.setIdCabecItemAbord(cabecAbordBean.getIdCabecAbord());
        itemAbordBean.setIdQuestaoItemAbord(idQuestao);
        itemAbordBean.setQtdeSegItemAbord(seguro);
        itemAbordBean.setQtdeInsegItemAbord(inseguro);
        ItemAbordDAO itemAbordDAO = new ItemAbordDAO();
        itemAbordDAO.salvarItem(itemAbordBean);
    }

    public boolean verItemCabecAbert(Long idQuestao){
        CabecAbordDAO cabecAbordDAO = new CabecAbordDAO();
        CabecAbordBean cabecAbordBean = cabecAbordDAO.getCabecAbert();
        ItemAbordDAO itemAbordDAO = new ItemAbordDAO();
        return itemAbordDAO.verItemCabecAbert(cabecAbordBean.getIdCabecAbord(), idQuestao);
    }

    public ItemAbordBean getItemCabecAbert(Long idQuestao){
        CabecAbordDAO cabecAbordDAO = new CabecAbordDAO();
        CabecAbordBean cabecAbordBean = cabecAbordDAO.getCabecAbert();
        ItemAbordDAO itemAbordDAO = new ItemAbordDAO();
        return itemAbordDAO.getItemCabecAberts(cabecAbordBean.getIdCabecAbord(), idQuestao);
    }

    public ColabBean getColab(Long matricColab){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.getColab(matricColab);
    }

    public TipoBean getTipo(int posicao){
        TipoDAO tipoDAO = new TipoDAO();
        return tipoDAO.getTipo(posicao);
    }

    public List<AreaBean> areaList(){
        AreaDAO areaDAO = new AreaDAO();
        return areaDAO.areaList();
    }

    public List<SubAreaBean> subAreaList(){
        SubAreaDAO subAreaDAO = new SubAreaDAO();
        return subAreaDAO.subAreaList(getIdAreaForm());
    }

    public List<TurnoBean> turnoList(){
        TurnoDAO turnoDAO = new TurnoDAO();
        return turnoDAO.turnoList();
    }

    public List<TopicoBean> topicoList(Long idTipo){
        TopicoDAO topicoDAO = new TopicoDAO();
        return topicoDAO.topicoList(idTipo);
    }

    public List<QuestaoBean> questaoList(Long idTopico){
        QuestaoDAO questaoDAO = new QuestaoDAO();
        return questaoDAO.questaoList(idTopico);
    }

    public FotoAbordBean salvarFoto(Bitmap bitmap){
        CabecAbordDAO cabecAbordDAO = new CabecAbordDAO();
        CabecAbordBean cabecAbordBean = cabecAbordDAO.getCabecAbert();
        FotoAbordDAO fotoAbordDAO = new FotoAbordDAO();
        return fotoAbordDAO.salvarFoto(cabecAbordBean.getIdCabecAbord(), bitmap);
    }

    public List<FotoAbordBean> getListFotoCabecAbert(){
        CabecAbordDAO cabecAbordDAO = new CabecAbordDAO();
        CabecAbordBean cabecAbordBean = cabecAbordDAO.getCabecAbert();
        FotoAbordDAO fotoAbordDAO = new FotoAbordDAO();
        return fotoAbordDAO.getListFotoCabec(cabecAbordBean.getIdCabecAbord());
    }

    public void salvaBolFechado(){
        CabecAbordDAO cabecAbordDAO = new CabecAbordDAO();
        CabecAbordBean cabecAbordBean = cabecAbordDAO.getCabecAbert();
        cabecAbordDAO.salvarCabecFech(cabecAbordBean);
    }

    public List<CabecAbordBean> dadosFechEnvio() {
        CabecAbordDAO cabecAbordDAO = new CabecAbordDAO();
        ItemAbordDAO itemAbordDAO = new ItemAbordDAO();
        FotoAbordDAO fotoAbordDAO = new FotoAbordDAO();
        List<CabecAbordBean> cabecAbordList = cabecAbordDAO.cabecFechList();
        for (int i = 0; i < cabecAbordList.size(); i++) {
            List<ItemAbordBean> itemAbordBeanList = itemAbordDAO.getListItemCabec(cabecAbordList.get(i).getIdCabecAbord());
            cabecAbordList.get(i).setItemAbordBeanList(itemAbordBeanList);
            List<FotoAbordBean> fotoAbordBeanList = fotoAbordDAO.getListFotoCabec(cabecAbordList.get(i).getIdCabecAbord());
            cabecAbordList.get(i).setFotoAbordBeanList(fotoAbordBeanList);
        }
        Log.i("PST", "COMPLETO = " + new Gson().toJson(cabecAbordList));
        return cabecAbordList;
    }

    public void deleteCabec(List<CabecAbordBean> cabecAbordBeanList) {

        Log.i("PST", "RETORNO = " + new Gson().toJson(cabecAbordBeanList));

        for(CabecAbordBean cabecAbordBean : cabecAbordBeanList){

            CabecAbordDAO cabecAbordDAO = new CabecAbordDAO();
            cabecAbordDAO.delCabec(cabecAbordBean.getIdCabecAbord());

            ItemAbordDAO itemAbordDAO = new ItemAbordDAO();
            itemAbordDAO.delItemCabec(cabecAbordBean.getIdCabecAbord());

            FotoAbordDAO fotoAbordDAO = new FotoAbordDAO();
            fotoAbordDAO.delFotoCabec(cabecAbordBean.getIdCabecAbord());

        }

        EnvioDadosServ.getInstance().envioDados();

    }

    public void atualDadosColab(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList arrayList = new ArrayList();
        arrayList.add("ColabBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, arrayList);
    }

    public void atualDadosArea(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList arrayList = new ArrayList();
        arrayList.add("AreaBean");
        arrayList.add("SubAreaBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, arrayList);
    }

    public void atualDadosSubArea(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList arrayList = new ArrayList();
        arrayList.add("SubAreaBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, arrayList);
    }

    public void atualDadosTurno(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList arrayList = new ArrayList();
        arrayList.add("TurnoBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, arrayList);
    }

    public void atualDadosItem(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList arrayList = new ArrayList();
        arrayList.add("TipoBean");
        arrayList.add("TopicoBean");
        arrayList.add("QuestaoBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, arrayList);
    }

    public void clearBD(){

        CabecAbordDAO cabecAbordDAO = new CabecAbordDAO();
        List cabAbordList = cabecAbordDAO.cabecAbertList();

        if(cabAbordList.size() > 0){

            CabecAbordBean cabecAbordBean = (CabecAbordBean) cabAbordList.get(0);

            ItemAbordDAO itemAbordDAO = new ItemAbordDAO();
            itemAbordDAO.delItemCabec(cabecAbordBean.getIdCabecAbord());

            FotoAbordDAO fotoAbordDAO = new FotoAbordDAO();
            fotoAbordDAO.delFotoCabec(cabecAbordBean.getIdCabecAbord());

            cabecAbordBean.delete();

        }

    }

    public Bitmap getStringBitmap(String foto){
        FotoAbordDAO fotoAbordDAO = new FotoAbordDAO();
        return fotoAbordDAO.getStringBitmap(foto);
    }

}
