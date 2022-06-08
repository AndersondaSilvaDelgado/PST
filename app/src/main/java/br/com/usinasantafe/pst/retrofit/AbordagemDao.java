package br.com.usinasantafe.pst.retrofit;

import java.util.List;

import br.com.usinasantafe.pst.model.bean.estaticas.AreaBean;
import br.com.usinasantafe.pst.model.bean.variaveis.CabecAbordBean;
import br.com.usinasantafe.pst.model.bean.variaveis.FotoAbordBean;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AbordagemDao {

    @POST("inserirdados.php")
    Call<List<CabecAbordBean>> envioAbordagem(@Body List<CabecAbordBean> cabecAbordBeanList);

}
