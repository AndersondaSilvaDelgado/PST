package br.com.usinasantafe.pst.util.retrofit;

import java.util.List;

import br.com.usinasantafe.pst.model.bean.variaveis.CabecAbordBean;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AbordagemDao {

    @POST("inserirdados.php")
    Call<List<CabecAbordBean>> envioAbordagem(@Body List<CabecAbordBean> cabecAbordBeanList, @Header("Authorization") String auth);

}
