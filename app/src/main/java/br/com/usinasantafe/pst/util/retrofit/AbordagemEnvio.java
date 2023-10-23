package br.com.usinasantafe.pst.util.retrofit;

import android.util.Log;

import java.util.List;

import br.com.usinasantafe.pst.control.AbordagemCTR;
import br.com.usinasantafe.pst.model.bean.variaveis.CabecAbordBean;
import br.com.usinasantafe.pst.model.dao.AtualAplicDAO;
import br.com.usinasantafe.pst.util.EnvioDadosServ;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbordagemEnvio {

    public AbordagemEnvio() {
    }

    public void envioDadosAbordagem(List<CabecAbordBean> cabecAbordList){

        try {

            AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
            AbordagemDao abordagemDao = ConnRetrofit.getInstance().conn().create(AbordagemDao.class);
            Call<List<CabecAbordBean>> call = abordagemDao.envioAbordagem(cabecAbordList, "Bearer " + atualAplicDAO.token());
            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<List<CabecAbordBean>> call, Response<List<CabecAbordBean>> response) {
                    AbordagemCTR abordagemCTR = new AbordagemCTR();
                    abordagemCTR.deleteCabec(response.body());
                }

                @Override
                public void onFailure(Call<List<CabecAbordBean>> call, Throwable t) {
                    Log.i("PST", "Erro = " + t);
                    EnvioDadosServ.getInstance().setEnviando(false);
                }
            });

        } catch (Exception e) {
            EnvioDadosServ.getInstance().setEnviando(false);
        }

    }

}
