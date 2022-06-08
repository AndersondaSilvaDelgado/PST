package br.com.usinasantafe.pst.view;

import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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
