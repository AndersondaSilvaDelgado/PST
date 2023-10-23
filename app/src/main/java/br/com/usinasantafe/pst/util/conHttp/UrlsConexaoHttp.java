package br.com.usinasantafe.pst.util.conHttp;

import br.com.usinasantafe.pst.PSTContext;

public class UrlsConexaoHttp {

    public static String versao = "versao_" + PSTContext.versaoWS.replace(".", "_");

    public static String url = "https://www.usinasantafe.com.br/pstdev/view/";
//    public static String url = "https://www.usinasantafe.com.br/pstqa/view/";
//    public static String url = "https://www.usinasantafe.com.br/pstprod/" + versao + "/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pst.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pst.util.conHttp.UrlsConexaoHttp";

    public static String AreaBean = url + "area.php";
    public static String SubAreaBean = url + "subarea.php";
    public static String ColabBean = url + "colab.php";
    public static String QuestaoBean = url + "questao.php";
    public static String TipoBean = url + "tipo.php";
    public static String TopicoBean = url + "topico.php";
    public static String TurnoBean = url + "turno.php";

    public UrlsConexaoHttp() {
    }

    public String getsInserirDados() {
        return url + "inserirdados.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Token")) {
            retorno = url + "aparelho.php";
        }
        return retorno;
    }

}
