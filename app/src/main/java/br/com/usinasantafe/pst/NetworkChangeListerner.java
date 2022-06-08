package br.com.usinasantafe.pst;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import br.com.usinasantafe.pst.model.bean.variaveis.CabecAbordBean;
import br.com.usinasantafe.pst.model.bean.variaveis.FotoAbordBean;
import br.com.usinasantafe.pst.model.bean.variaveis.ItemAbordBean;
import br.com.usinasantafe.pst.model.pst.DatabaseHelper;
import br.com.usinasantafe.pst.util.EnvioDadosServ;
import br.com.usinasantafe.pst.util.Tempo;

public class NetworkChangeListerner extends BroadcastReceiver {

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
//
//		Log.i("PST", "LISTA");
//
//		CabecAbordBean cabecAbordBean = new CabecAbordBean();
//		List cabAbordList = cabecAbordBean.all();
//
//		Log.i("PST", "CABECALHO");
//
//		for(int i = 0; i < cabAbordList.size(); i++){
//			cabecAbordBean = (CabecAbordBean) cabAbordList.get(i);
//			Log.i("PST", "idCabAbord = " + cabecAbordBean.getIdCabecAbord());
//			Log.i("PST", "matricFuncCabAbord = " + cabecAbordBean.getMatricFuncCabecAbord());
//			Log.i("PST", "idAreaCabAbord = " + cabecAbordBean.getIdAreaCabecAbord());
//			Log.i("PST", "idSubAreaCabAbord = " + cabecAbordBean.getIdSubAreaCabecAbord());
//			Log.i("PST", "idTurnoCabAbord = " + cabecAbordBean.getIdTurnoCabecAbord());
//			Log.i("PST", "extensaoMinCabAbord = " + cabecAbordBean.getExtensaoMinCabecAbord());
//			Log.i("PST", "pessoasContCabAbord = " + cabecAbordBean.getPessoasContCabecAbord());
//			Log.i("PST", "pessoasObsCabAbord = " + cabecAbordBean.getPessoasObsCabecAbord());
//			Log.i("PST", "tipoCabAbord = " + cabecAbordBean.getTipoCabecAbord());
//			Log.i("PST", "comentCabAbord = " + cabecAbordBean.getComentCabecAbord());
//			Log.i("PST", "dthrCabAbord = " + cabecAbordBean.getDthrCabecAbord());
//			Log.i("PST", "statusCabAbord = " + cabecAbordBean.getStatusCabecAbord());
//		}
//
//		ItemAbordBean itemAbordBean = new ItemAbordBean();
//		List itemAbordList = itemAbordBean.all();
//
//		Log.i("PST", "ITEM");
//
//		for(int i = 0; i < itemAbordList.size(); i++){
//			itemAbordBean = (ItemAbordBean) itemAbordList.get(i);
//			Log.i("PST", "idItemAbord = " + itemAbordBean.getIdItemAbord());
//			Log.i("PST", "idCabItemAbord = " + itemAbordBean.getIdCabecItemAbord());
//			Log.i("PST", "idQuestaoItemAbord = " + itemAbordBean.getIdQuestaoItemAbord());
//			Log.i("PST", "qtdeInsegItemAbord = " + itemAbordBean.getQtdeInsegItemAbord());
//			Log.i("PST", "qtdeSegItemAbord = " + itemAbordBean.getQtdeSegItemAbord());
//			Log.i("PST", "dthrItemAbord = " + itemAbordBean.getDthrItemAbord());
//		}
//
//		FotoAbordBean fotoAbordBean = new FotoAbordBean();
//		List fotoAbordList = fotoAbordBean.all();
//
//		Log.i("PST", "FOTO");
//
//		for(int i = 0; i < fotoAbordList.size(); i++){
//			fotoAbordBean = (FotoAbordBean) fotoAbordList.get(i);
//			Log.i("PST", "idFotoAbord = " + fotoAbordBean.getIdFotoAbord());
//			Log.i("PST", "idCabFotoAbord = " + fotoAbordBean.getIdCabecFotoAbord());
//			Log.i("PST", "fotoAbord = " + fotoAbordBean.getFotoAbord());
//			Log.i("PST", "dthrFotoAbord = " + fotoAbordBean.getDthrFotoAbord());
//		}

	}
}