package br.com.usinasantafe.pst;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.List;

import br.com.usinasantafe.pst.bean.variaveis.FotoAbordBean;

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
        holder.imagem.setImageBitmap(ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(fotoAbordBean.getFileImage()),
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

                        File file = new File(fotoAbordBean.getFileImage());
                        file.delete();

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