package br.com.usinasantafe.pst.util;

import android.content.Context;

import java.util.List;

import br.com.usinasantafe.pst.control.ObservCTR;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private List listDatasFrenteTO;
    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private boolean enviando = false;

    public EnvioDadosServ() {

        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static EnvioDadosServ getInstance() {
        if (instance == null) {
            instance = new EnvioDadosServ();
        }
        return instance;
    }

    //////////////////////// ENVIAR DADOS ////////////////////////////////////////////

    public void enviarCabecalho() {

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] dados = new String[3];

        dados[0] = urlsConexaoHttp.getsInsertBolAbertoMM();
        dados[1] = "dados";
        dados[2] = "imagem";

        ConHttpMultipartGenerico conHttpMultipartGenerico = new ConHttpMultipartGenerico();
        conHttpMultipartGenerico.execute(dados);

    }

//    public void enviarChecklist() {
//
//        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
//        List cabecCheckListLista = boletinsChecklist();
//
//        JsonArray jsonArrayCabec = new JsonArray();
//        JsonArray jsonArrayItem = new JsonArray();
//
//        for (int i = 0; i < cabecCheckListLista.size(); i++) {
//
//            cabecCheckListTO = (CabecCheckListTO) cabecCheckListLista.get(i);
//            Gson gsonCabec = new Gson();
//            jsonArrayCabec.add(gsonCabec.toJsonTree(cabecCheckListTO, cabecCheckListTO.getClass()));
//
//            RespItemCheckListTO respItemCheckListTO = new RespItemCheckListTO();
//            List listaItem = respItemCheckListTO.get("idCabIt", cabecCheckListTO.getIdCab());
//
//            for (int j = 0; j < listaItem.size(); j++) {
//                respItemCheckListTO = (RespItemCheckListTO) listaItem.get(j);
//                Gson gsonItem = new Gson();
//                jsonArrayItem.add(gsonItem.toJsonTree(respItemCheckListTO, respItemCheckListTO.getClass()));
//            }
//
//        }
//
//        JsonObject jsonCabec = new JsonObject();
//        jsonCabec.add("cabecalho", jsonArrayCabec);
//
//        JsonObject jsonItem = new JsonObject();
//        jsonItem.add("item", jsonArrayItem);
//
//        String dados = jsonCabec.toString() + "_" + jsonItem.toString();
//
//        Log.i("PMM", "CHECKLIST = " + dados);
//
//        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();
//
//        String[] url = {urlsConexaoHttp.getsInserirCheckList()};
//        Map<String, Object> parametrosPost = new HashMap<String, Object>();
//        parametrosPost.put("dado", dados);
//
//        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
//        conHttpPostGenerico.setParametrosPost(parametrosPost);
//        conHttpPostGenerico.execute(url);
//
//    }
//
//    public void enviarBolFechadosMM() {
//
//        BoletimMMTO boletimMMTO = new BoletimMMTO();
//        List boletimMMList = boletinsFechadoMM();
//
//        JsonArray jsonArrayBoletim = new JsonArray();
//        JsonArray jsonArrayAponta = new JsonArray();
//        JsonArray jsonArrayImplemento = new JsonArray();
//        JsonArray jsonArrayRendimento = new JsonArray();
//
//        for (int i = 0; i < boletimMMList.size(); i++) {
//
//            boletimMMTO = (BoletimMMTO) boletimMMList.get(i);
//            Gson gsonCabec = new Gson();
//            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimMMTO, boletimMMTO.getClass()));
//
//            ApontaMMTO apontaMMTO = new ApontaMMTO();
//            List apontaMMList = apontaMMTO.get("idBolAponta", boletimMMTO.getIdBoletim());
//
//            for (int j = 0; j < apontaMMList.size(); j++) {
//
//                apontaMMTO = (ApontaMMTO) apontaMMList.get(j);
//                Gson gsonItem = new Gson();
//                jsonArrayAponta.add(gsonItem.toJsonTree(apontaMMTO, apontaMMTO.getClass()));
//
//                ImplementoTO implementoTO = new ImplementoTO();
//                List implementoList = implementoTO.get("idApontImplemento", apontaMMTO.getIdAponta());
//
//                for (int l = 0; l < implementoList.size(); l++) {
//                    implementoTO = (ImplementoTO) implementoList.get(l);
//                    Gson gsonItemImp = new Gson();
//                    jsonArrayImplemento.add(gsonItemImp.toJsonTree(implementoTO, implementoTO.getClass()));
//                }
//
//                implementoList.clear();
//
//            }
//
//            apontaMMList.clear();
//
//            RendimentoTO rendimentoTO = new RendimentoTO();
//            List rendList = rendimentoTO.get("idBolRendimento", boletimMMTO.getIdBoletim());
//
//            for (int j = 0; j < rendList.size(); j++) {
//                rendimentoTO = (RendimentoTO) rendList.get(j);
//                Gson gsonRend = new Gson();
//                jsonArrayRendimento.add(gsonRend.toJsonTree(rendimentoTO, rendimentoTO.getClass()));
//            }
//
//            rendList.clear();
//
//        }
//
//        boletimMMList.clear();
//
//        JsonObject jsonBoletim = new JsonObject();
//        jsonBoletim.add("boletim", jsonArrayBoletim);
//
//        JsonObject jsonAponta = new JsonObject();
//        jsonAponta.add("aponta", jsonArrayAponta);
//
//        JsonObject jsonImplemento = new JsonObject();
//        jsonImplemento.add("implemento", jsonArrayImplemento);
//
//        JsonObject jsonRend = new JsonObject();
//        jsonRend.add("rendimento", jsonArrayRendimento);
//
//        String dados = jsonBoletim.toString() + "_" + jsonAponta.toString() + "|" + jsonImplemento.toString() + "#" + jsonRend.toString();
////        String dados = jsonBoletim.toString() + "_" + jsonAponta.toString() + "|" + jsonImplemento.toString() + "#" + jsonRend.toString() + "?";
//
//        Log.i("PMM", "FECHADO = " + dados);
//
//        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();
//
//        String[] url = {urlsConexaoHttp.getsInsertBolFechadoMM()};
//        Map<String, Object> parametrosPost = new HashMap<String, Object>();
//        parametrosPost.put("dado", dados);
//
//        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
//        conHttpPostGenerico.setParametrosPost(parametrosPost);
//        conHttpPostGenerico.execute(url);
//
//    }
//
//    public void enviarBolAbertosMM() {
//
//        BoletimMMTO boletimMMTO = new BoletimMMTO();
//        List boletimMMList = boletinsAbertoSemEnvioMM();
//
//        JsonArray jsonArrayBoletim = new JsonArray();
//        JsonArray jsonArrayAponta = new JsonArray();
//        JsonArray jsonArrayImplemento = new JsonArray();
//
//        for (int i = 0; i < boletimMMList.size(); i++) {
//
//            boletimMMTO = (BoletimMMTO) boletimMMList.get(i);
//            Gson gsonCabec = new Gson();
//            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimMMTO, boletimMMTO.getClass()));
//
//            ApontaMMTO apontaMMTO = new ApontaMMTO();
//
//            ArrayList listaPesq = new ArrayList();
//            EspecificaPesquisa pesquisa = new EspecificaPesquisa();
//            pesquisa.setCampo("statusAponta");
//            pesquisa.setValor(2L);
//            pesquisa.setTipo(1);
//            listaPesq.add(pesquisa);
//
//            EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
//            pesquisa2.setCampo("idBolAponta");
//            pesquisa2.setValor(boletimMMTO.getIdBoletim());
//            pesquisa2.setTipo(1);
//            listaPesq.add(pesquisa2);
//
//            List apontaList = apontaMMTO.get(listaPesq);
//
//            for (int j = 0; j < apontaList.size(); j++) {
//
//                apontaMMTO = (ApontaMMTO) apontaList.get(j);
//                Gson gsonItem = new Gson();
//                jsonArrayAponta.add(gsonItem.toJsonTree(apontaMMTO, apontaMMTO.getClass()));
//
//                ImplementoTO implementoTO = new ImplementoTO();
//                List implementoList = implementoTO.get("idApontImplemento", apontaMMTO.getIdAponta());
//
//                for (int l = 0; l < implementoList.size(); l++) {
//                    implementoTO = (ImplementoTO) implementoList.get(l);
//                    Gson gsonItemImp = new Gson();
//                    jsonArrayImplemento.add(gsonItemImp.toJsonTree(implementoTO, implementoTO.getClass()));
//                }
//
//                implementoList.clear();
//
//            }
//
//            apontaList.clear();
//
//        }
//
//        boletimMMList.clear();
//
//        JsonObject jsonBoletim = new JsonObject();
//        jsonBoletim.add("boletim", jsonArrayBoletim);
//
//        JsonObject jsonAponta = new JsonObject();
//        jsonAponta.add("aponta", jsonArrayAponta);
//
//        JsonObject jsonImplemento = new JsonObject();
//        jsonImplemento.add("implemento", jsonArrayImplemento);
//
//
//        String dados = jsonBoletim.toString() + "_" + jsonAponta.toString() + "|" + jsonImplemento.toString();
////        String dados = jsonBoletim.toString() + "_" + jsonAponta.toString() + "|" + jsonImplemento.toString() + "?";
//
//        Log.i("PMM", "ABERTO = " + dados);
//
//        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();
//
//        String[] url = {urlsConexaoHttp.getsInsertBolAbertoMM()};
//        Map<String, Object> parametrosPost = new HashMap<String, Object>();
//        parametrosPost.put("dado", dados);
//
//        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
//        conHttpPostGenerico.setParametrosPost(parametrosPost);
//        conHttpPostGenerico.execute(url);
//
//    }
//
//    public void envioApontaMM() {
//
//        JsonArray jsonArrayAponta = new JsonArray();
//        JsonArray jsonArrayImplemento = new JsonArray();
//
//        ApontaMMTO apontaMMTO = new ApontaMMTO();
//        List apontaList = apontamentosMM();
//
//        for (int i = 0; i < apontaList.size(); i++) {
//
//            apontaMMTO = (ApontaMMTO) apontaList.get(i);
//            Gson gson = new Gson();
//            jsonArrayAponta.add(gson.toJsonTree(apontaMMTO, apontaMMTO.getClass()));
//
//            ImplementoTO implementoTO = new ImplementoTO();
//            List implementoList = implementoTO.get("idApontImplemento", apontaMMTO.getIdAponta());
//
//            for (int j = 0; j < implementoList.size(); j++) {
//                implementoTO = (ImplementoTO) implementoList.get(j);
//                Gson gsonItem = new Gson();
//                jsonArrayImplemento.add(gsonItem.toJsonTree(implementoTO, implementoTO.getClass()));
//            }
//
//            implementoList.clear();
//
//        }
//
//        apontaList.clear();
//
//        JsonObject jsonAponta = new JsonObject();
//        jsonAponta.add("aponta", jsonArrayAponta);
//
//        JsonObject jsonImplemento = new JsonObject();
//        jsonImplemento.add("implemento", jsonArrayImplemento);
//
//        String dados = jsonAponta.toString() + "_" + jsonImplemento.toString();
////        String dados = jsonAponta.toString() + "|" + jsonImplemento.toString() + "?";
//
//        Log.i("PMM", "APONTAMENTO = " + dados);
//
//        String[] url = {urlsConexaoHttp.getsInsertApontaMM()};
//        Map<String, Object> parametrosPost = new HashMap<String, Object>();
//        parametrosPost.put("dado", dados);
//
//        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
//        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
//        conHttpPostCadGenerico.execute(url);
//
//    }
//
//    public void enviarBolFechadosFert() {
//
//        BoletimFertTO boletimFertTO = new BoletimFertTO();
//        List boletimFertList = boletinsFechadoFert();
//
//        JsonArray jsonArrayBoletim = new JsonArray();
//        JsonArray jsonArrayAponta = new JsonArray();
//        JsonArray jsonArrayRecolhimento = new JsonArray();
//
//        for (int i = 0; i < boletimFertList.size(); i++) {
//
//            boletimFertTO = (BoletimFertTO) boletimFertList.get(i);
//            Gson gsonCabec = new Gson();
//            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimFertTO, boletimFertTO.getClass()));
//
//            ApontaFertTO apontaFertTO = new ApontaFertTO();
//            List apontaFertList = apontaFertTO.get("idBolApontaFert", boletimFertTO.getIdBolFert());
//
//            for (int j = 0; j < apontaFertList.size(); j++) {
//
//                apontaFertTO = (ApontaFertTO) apontaFertList.get(j);
//                Gson gsonItem = new Gson();
//                jsonArrayAponta.add(gsonItem.toJsonTree(apontaFertTO, apontaFertTO.getClass()));
//
//            }
//
//            apontaFertList.clear();
//
//            RecolhimentoTO recolhimentoTO = new RecolhimentoTO();
//            List recolhimentoList = recolhimentoTO.get("idBolRecol", boletimFertTO.getIdBolFert());
//
//            for (int j = 0; j < recolhimentoList.size(); j++) {
//                recolhimentoTO = (RecolhimentoTO) recolhimentoList.get(j);
//                Gson gsonRecol = new Gson();
//                jsonArrayRecolhimento.add(gsonRecol.toJsonTree(recolhimentoTO, recolhimentoTO.getClass()));
//            }
//
//            recolhimentoList.clear();
//
//        }
//
//        boletimFertList.clear();
//
//        JsonObject jsonBoletim = new JsonObject();
//        jsonBoletim.add("boletim", jsonArrayBoletim);
//
//        JsonObject jsonAponta = new JsonObject();
//        jsonAponta.add("aponta", jsonArrayAponta);
//
//        JsonObject jsonRecolhimento = new JsonObject();
//        jsonRecolhimento.add("recolhimento", jsonArrayRecolhimento);
//
//        String dados = jsonBoletim.toString() + "_" + jsonAponta.toString() + "|" + jsonRecolhimento.toString();
//
//        Log.i("PMM", "FECHADO = " + dados);
//
//        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();
//
//        String[] url = {urlsConexaoHttp.getsInsertBolFechadoFert()};
//        Map<String, Object> parametrosPost = new HashMap<String, Object>();
//        parametrosPost.put("dado", dados);
//
//        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
//        conHttpPostGenerico.setParametrosPost(parametrosPost);
//        conHttpPostGenerico.execute(url);
//
//    }
//
//    public void enviarBolAbertosFert() {
//
//        BoletimFertTO boletimFertTO = new BoletimFertTO();
//        List boletimFertList = boletinsAbertoSemEnvioFert();
//
//        JsonArray jsonArrayBoletim = new JsonArray();
//        JsonArray jsonArrayAponta = new JsonArray();
//
//        for (int i = 0; i < boletimFertList.size(); i++) {
//
//            boletimFertTO = (BoletimFertTO) boletimFertList.get(i);
//            Gson gsonCabec = new Gson();
//            jsonArrayBoletim.add(gsonCabec.toJsonTree(boletimFertTO, boletimFertTO.getClass()));
//
//            ApontaFertTO apontaFertTO = new ApontaFertTO();
//
//            ArrayList listaPesq = new ArrayList();
//            EspecificaPesquisa pesquisa = new EspecificaPesquisa();
//            pesquisa.setCampo("statusApontaFert");
//            pesquisa.setValor(2L);
//            pesquisa.setTipo(1);
//            listaPesq.add(pesquisa);
//
//            EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
//            pesquisa2.setCampo("idBolApontaFert");
//            pesquisa2.setValor(boletimFertTO.getIdBolFert());
//            pesquisa2.setTipo(1);
//            listaPesq.add(pesquisa2);
//
//            List apontaList = apontaFertTO.get(listaPesq);
//
//            for (int j = 0; j < apontaList.size(); j++) {
//
//                apontaFertTO = (ApontaFertTO) apontaList.get(j);
//                Gson gsonItem = new Gson();
//                jsonArrayAponta.add(gsonItem.toJsonTree(apontaFertTO, apontaFertTO.getClass()));
//
//            }
//
//            apontaList.clear();
//
//        }
//
//        boletimFertList.clear();
//
//        JsonObject jsonBoletim = new JsonObject();
//        jsonBoletim.add("boletim", jsonArrayBoletim);
//
//        JsonObject jsonAponta = new JsonObject();
//        jsonAponta.add("aponta", jsonArrayAponta);
//
//        String dados = jsonBoletim.toString() + "_" + jsonAponta.toString();
//
//        Log.i("PMM", "ABERTO = " + dados);
//
//        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();
//
//        String[] url = {urlsConexaoHttp.getsInsertBolAbertoFert()};
//        Map<String, Object> parametrosPost = new HashMap<String, Object>();
//        parametrosPost.put("dado", dados);
//
//        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
//        conHttpPostGenerico.setParametrosPost(parametrosPost);
//        conHttpPostGenerico.execute(url);
//
//    }
//
//    public void envioApontaFert() {
//
//        JsonArray jsonArrayAponta = new JsonArray();
//
//        ApontaFertTO apontaFertTO = new ApontaFertTO();
//        List apontaList = apontamentosFert();
//
//        for (int i = 0; i < apontaList.size(); i++) {
//
//            apontaFertTO = (ApontaFertTO) apontaList.get(i);
//            Gson gson = new Gson();
//            jsonArrayAponta.add(gson.toJsonTree(apontaFertTO, apontaFertTO.getClass()));
//
//        }
//
//        apontaList.clear();
//
//        JsonObject jsonAponta = new JsonObject();
//        jsonAponta.add("aponta", jsonArrayAponta);
//
//        String dados = jsonAponta.toString();
//
//        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
//        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
//        conHttpPostCadGenerico.execute(url);
//
//    }
//        Log.i("PMM", "APONTAMENTO = " + dados);
//
//        String[] url = {urlsConexaoHttp.getsInsertApontaFert()};
//        Map<String, Object> parametrosPost = new HashMap<String, Object>();
//        parametrosPost.put("dado", dados);
//


    //////////////////////VERIFICAÇÃO DE DADOS///////////////////////////

    public Boolean verifCabec() {
        ObservCTR observCTR = new ObservCTR();
        return observCTR.verEnvioDadosCabec();
    }

    /////////////////////////MECANISMO DE ENVIO//////////////////////////////////

    public void envioDados(Context context) {
        enviando = true;
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            envioDadosPrinc();
        }
        else{
            enviando = false;
        }

    }

    public void envioDadosPrinc() {
        if(verifCabec()){
            enviarCabecalho();
        }
    }

    public boolean verifDadosEnvio() {
        if (!verifCabec()){
            enviando = false;
            return false;
        } else {
            return true;
        }
    }

    public int getStatusEnvio() {
        if (enviando) {
            statusEnvio = 1;
        } else {
            if (!verifDadosEnvio()) {
                statusEnvio = 3;
            } else {
                statusEnvio = 2;
            }
        }
        return statusEnvio;
    }

    public void setStatusEnvio(int statusEnvio) {
        this.statusEnvio = statusEnvio;
    }
}