package br.com.usinasantafe.pst.util;

import br.com.usinasantafe.pst.PSTContext;

public class UrlsConexaoHttp {

    public static String urlPrincipal = "http://www.usinasantafe.com.br/pstdev/view/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/pstdev/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pst.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pst.util.UrlsConexaoHttp";

    public static String put = "?versao=" + PSTContext.versaoAplic.replace(".", "_");

    public static String AreaBean = urlPrincipal + "area.php" + put;
    public static String SubAreaBean = urlPrincipal + "subarea.php" + put;
    public static String ColabBean = urlPrincipal + "colab.php" + put;
    public static String QuestaoBean = urlPrincipal + "questao.php" + put;
    public static String TipoBean = urlPrincipal + "tipo.php" + put;
    public static String TopicoBean = urlPrincipal + "topico.php" + put;
    public static String TurnoBean = urlPrincipal + "turno.php" + put;

    public UrlsConexaoHttp() {
    }

    public String getsInserirDados() {
        return urlPrincEnvio + "inserirdados.php" + put;
    }

    public String getsInsertBolFechadoMM() {
        return urlPrincEnvio + "inserirbolfechadomm.php" + put;
    }

    public String getsInsertApontaFert() {
        return urlPrincEnvio + "inserirapontfert.php" + put;
    }

    public String getsInsertBolAbertoFert() {
        return urlPrincEnvio + "inserirbolabertofert.php" + put;
    }

    public String getsInsertBolFechadoFert() {
        return urlPrincEnvio + "inserirbolfechadofert.php" + put;
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Equip")) {
            retorno = urlPrincipal + "equip.php" + put;
        } else if (classe.equals("OS")) {
            retorno = urlPrincipal + "os.php" + put;
        } else if (classe.equals("Atividade")) {
            retorno = urlPrincipal + "atualativ.php" + put;
        } else if (classe.equals("AtualParada")) {
            retorno = urlPrincipal + "atualparada.php" + put;
        } else if (classe.equals("Atualiza")) {
            retorno = urlPrincipal + "atualaplic.php" + put;
        } else if (classe.equals("Operador")) {
            retorno = urlPrincipal + "motorista.php" + put;
        } else if (classe.equals("Turno")) {
            retorno = urlPrincipal + "turno.php" + put;
        } else if (classe.equals("EquipSeg")) {
            retorno = urlPrincipal + "equipseg.php" + put;
        } else if (classe.equals("CheckList")) {
            retorno = urlPrincipal + "atualchecklist.php" + put;
        } else if (classe.equals("Pneu")) {
            retorno = urlPrincipal + "pneu.php" + put;
        } else if (classe.equals("Bocal")) {
            retorno = urlPrincipal + "bocal.php" + put;
        } else if (classe.equals("PressaoBocal")) {
            retorno = urlPrincipal + "pressaobocal.php" + put;
        } else if (classe.equals("Perda")) {
            retorno = urlPrincipal + "perda.php" + put;
        }
        return retorno;
    }

}
