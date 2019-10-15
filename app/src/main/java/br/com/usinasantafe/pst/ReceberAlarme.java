package br.com.usinasantafe.pst;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import br.com.usinasantafe.pst.pst.DatabaseHelper;
import br.com.usinasantafe.pst.util.EnvioDadosServ;
import br.com.usinasantafe.pst.util.Tempo;

public class ReceberAlarme extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if(DatabaseHelper.getInstance() == null){
			new DatabaseHelper(context);
		}

		Log.i("PST", "DATA HORA = " + Tempo.getInstance().dataComHora());
//		teste();

		if(EnvioDadosServ.getInstance().verifDadosEnvio()){
			Log.i("PST", "ENVIANDO");
			EnvioDadosServ.getInstance().envioDados(context);
		}
	}
}