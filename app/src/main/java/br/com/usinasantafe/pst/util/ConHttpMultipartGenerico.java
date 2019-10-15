package br.com.usinasantafe.pst.util;


import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ConHttpMultipartGenerico extends AsyncTask<String, Void, String>   {

	private String url = "http://internal.usinasantafe.com.br:8180/WebServiceTeste/UploadServlet";
	private byte[] data;

    private static ConHttpMultipartGenerico instance = null;

    public ConHttpMultipartGenerico() {
    }

    public static ConHttpMultipartGenerico getInstance() {
        if (instance == null)
            instance = new ConHttpMultipartGenerico();
        return instance;
    }

	@Override
	protected String doInBackground(String... params) {

        String answer = "";

        String url = params[0];
        String dado = params[1];
        String foto = params[2];

		try{

			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);

            ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
            valores.add(new BasicNameValuePair("dado", dado));
            valores.add(new BasicNameValuePair("foto", foto));

            httpPost.setEntity(new UrlEncodedFormEntity(valores));
            HttpResponse resposta = httpClient.execute(httpPost);
            answer = EntityUtils.toString(resposta.getEntity());

		} catch (Exception e) {
			
		}
		finally{
			
		}
		
		return answer;
	}

	protected void onPostExecute(String result) {

		try {
//			EnvioDadosServ.getInstance().setEnviando(false);
//			Log.i("ECM", "VALOR RECEBIDO --> " + result);
//			if(result.trim().equals("GRAVOU-CHECKLIST")){
//				EnvioDadosServ.getInstance().delChecklist();
//			}
//			else if(result.trim().equals("GRAVOU-BOLFECHADO")){
//				EnvioDadosServ.getInstance().delBolFechadoMM();
//			}
//			else if(result.trim().equals("GRAVOU-BOLFECHADOFERT")){
//				EnvioDadosServ.getInstance().delBolFechadoFert();
//			}
//			else if(result.trim().equals("GRAVOU-APONTAMM")){
//				EnvioDadosServ.getInstance().delApontaMM();
//			}
//			else if(result.trim().equals("GRAVOU-APONTAFERT")){
//				EnvioDadosServ.getInstance().delApontaFert();
//			}
//			else{
//                if(result.trim().contains("GRAVOU")){
//                    EnvioDadosServ.getInstance().atualDelBoletimMM(result);
//                }
//                else if(result.trim().contains("FERT")){
//					EnvioDadosServ.getInstance().atualDelBoletimFert(result);
//				}
//			}
		} catch (Exception e) {

			Log.i("PMM", "Erro2 = " + e);
		}

	}
	
}
