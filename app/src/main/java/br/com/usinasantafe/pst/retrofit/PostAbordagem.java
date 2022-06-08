package br.com.usinasantafe.pst.retrofit;

import android.util.Log;

import java.util.List;

import br.com.usinasantafe.pst.control.AbordagemCTR;
import br.com.usinasantafe.pst.model.bean.variaveis.CabecAbordBean;
import br.com.usinasantafe.pst.util.EnvioDadosServ;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostAbordagem {

    public PostAbordagem() {
    }

    public void envioAbordagem(List<CabecAbordBean> cabecAbordList){

        try {

            AbordagemDao abordagemDao = ConnRetrofit.getInstance().conn().create(AbordagemDao.class);
            Call<List<CabecAbordBean>> call = abordagemDao.envioAbordagem(cabecAbordList);
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
            Log.i("PST", "Erro = " + e);
            EnvioDadosServ.getInstance().setEnviando(false);
        }

    }

}
