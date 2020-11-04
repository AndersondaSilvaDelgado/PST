package br.com.usinasantafe.pst.view;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import br.com.usinasantafe.pst.R;

public class ImagemViewHolder extends RecyclerView.ViewHolder {

    ImageView imagem;
    CardView cardView;

    ImagemViewHolder(View itemView) {
        super(itemView);

        imagem = itemView.findViewById(R.id.ivImage);
        cardView = itemView.findViewById(R.id.cardview);
    }

}
