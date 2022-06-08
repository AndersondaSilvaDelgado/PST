package br.com.usinasantafe.pst.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.usinasantafe.pst.R;
import br.com.usinasantafe.pst.model.bean.variaveis.FotoAbordBean;
import br.com.usinasantafe.pst.control.AbordagemCTR;

public class AdapterListFoto extends RecyclerView.Adapter<ImagemViewHolder> {

    private Context context;
    private List imagemList;
    private int pos;
    private FotoAbordBean fotoAbordBean;

    public AdapterListFoto(Context context, List imagemList) {
        this.context = context;
        this.imagemList = imagemList;
    }

    @NonNull
    @Override
    public ImagemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item_foto, viewGroup, false);
        return new ImagemViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImagemViewHolder holder, final int position) {

        pos = position;

        fotoAbordBean = (FotoAbordBean) imagemList.get(pos);
        AbordagemCTR abordagemCTR = new AbordagemCTR();
        holder.imagem.setImageBitmap(ThumbnailUtils.extractThumbnail(abordagemCTR.getStringBitmap(fotoAbordBean.getFotoAbord()),
                192, 192));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA APAGAR A FOTO DA OBSERVAÇÃO?");
                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        imagemList.remove(pos);
                        fotoAbordBean.delete();

                        Intent it = new Intent(context, CameraActivity.class);
                        context.startActivity(it);

                    }
                });

                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return imagemList.size();
    }
}