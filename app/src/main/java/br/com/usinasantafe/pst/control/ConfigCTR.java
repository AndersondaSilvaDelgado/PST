package br.com.usinasantafe.pst.control;

import br.com.usinasantafe.pst.bean.variaveis.ConfigBean;

public class ConfigCTR {

    public ConfigCTR() {
    }

    public boolean hasElements(){
        ConfigBean configBean = new ConfigBean();
        return configBean.hasElements();
    }

}
