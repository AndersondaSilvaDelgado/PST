package br.com.usinasantafe.pst;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import br.com.usinasantafe.pst.model.bean.variaveis.CabAbordBean;
import br.com.usinasantafe.pst.model.bean.variaveis.FotoAbordBean;
import br.com.usinasantafe.pst.model.bean.variaveis.ItemAbordBean;
import br.com.usinasantafe.pst.model.pst.DatabaseHelper;
import br.com.usinasantafe.pst.util.EnvioDadosServ;
import br.com.usinasantafe.pst.util.Tempo;

public class ReceberAlarme extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if(DatabaseHelper.getInstance() == null){
			new DatabaseHelper(context);
		}

		Log.i("PST", "DATA HORA = " + Tempo.getInstance().dataComHora());
		Log.i("PST", "STATUS = " + EnvioDadosServ.getInstance().isEnviando());
		if(!EnvioDadosServ.getInstance().isEnviando()){
			Log.i("PST", "ENVIANDO");
			EnvioDadosServ.getInstance().envioDados(context);
		}

		Log.i("PST", "LISTA");

		CabAbordBean cabAbordBean = new CabAbordBean();
		List cabAbordList = cabAbordBean.all();

		Log.i("PST", "CABECALHO");

		for(int i = 0; i < cabAbordList.size(); i++){
			cabAbordBean = (CabAbordBean) cabAbordList.get(i);
			Log.i("PST", "idCabAbord = " + cabAbordBean.getIdCabAbord());
			Log.i("PST", "matricFuncCabAbord = " + cabAbordBean. getMatricFuncCabAbord());
			Log.i("PST", "idAreaCabAbord = " + cabAbordBean.getIdAreaCabAbord());
			Log.i("PST", "idSubAreaCabAbord = " + cabAbordBean.getIdSubAreaCabAbord());
			Log.i("PST", "idTurnoCabAbord = " + cabAbordBean.getIdTurnoCabAbord());
			Log.i("PST", "extensaoMinCabAbord = " + cabAbordBean.getExtensaoMinCabAbord());
			Log.i("PST", "pessoasContCabAbord = " + cabAbordBean.getPessoasContCabAbord());
			Log.i("PST", "pessoasObsCabAbord = " + cabAbordBean.getPessoasObsCabAbord());
			Log.i("PST", "tipoCabAbord = " + cabAbordBean.getTipoCabAbord());
			Log.i("PST", "comentCabAbord = " + cabAbordBean.getComentCabAbord());
			Log.i("PST", "dthrCabAbord = " + cabAbordBean.getDthrCabAbord());
			Log.i("PST", "statusCabAbord = " + cabAbordBean.getStatusCabAbord());
		}

		ItemAbordBean itemAbordBean = new ItemAbordBean();
		List itemAbordList = itemAbordBean.all();

		Log.i("PST", "ITEM");

		for(int i = 0; i < itemAbordList.size(); i++){
			itemAbordBean = (ItemAbordBean) itemAbordList.get(i);
			Log.i("PST", "idItemAbord = " + itemAbordBean.getIdItemAbord());
			Log.i("PST", "idCabItemAbord = " + itemAbordBean.getIdCabItemAbord());
			Log.i("PST", "idQuestaoItemAbord = " + itemAbordBean.getIdQuestaoItemAbord());
			Log.i("PST", "qtdeInsegItemAbord = " + itemAbordBean.getQtdeInsegItemAbord());
			Log.i("PST", "qtdeSegItemAbord = " + itemAbordBean.getQtdeSegItemAbord());
			Log.i("PST", "dthrItemAbord = " + itemAbordBean.getDthrItemAbord());
		}

		FotoAbordBean fotoAbordBean = new FotoAbordBean();
		List fotoAbordList = fotoAbordBean.all();

		Log.i("PST", "FOTO");

		for(int i = 0; i < fotoAbordList.size(); i++){
			fotoAbordBean = (FotoAbordBean) fotoAbordList.get(i);
			Log.i("PST", "idFotoAbord = " + fotoAbordBean.getIdFotoAbord());
			Log.i("PST", "idCabFotoAbord = " + fotoAbordBean.getIdCabFotoAbord());
			Log.i("PST", "fotoAbord = " + fotoAbordBean.getFotoAbord());
			Log.i("PST", "dthrFotoAbord = " + fotoAbordBean.getDthrFotoAbord());
		}

	}
}