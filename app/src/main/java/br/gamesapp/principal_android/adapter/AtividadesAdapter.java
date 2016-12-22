package br.gamesapp.principal_android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.gamesapp.principal_android.R;
import br.gamesapp.principal_android.entidades.atividade.Atividade;

/**
 * Created by matheush on 15/12/16.
 */

public class AtividadesAdapter extends RecyclerView.Adapter<AtividadesAdapter.AtividadesViewHolder> {

    private final ArrayList<Atividade> atividadeArrayList;
    private final Context context;

    private AtividadeOnClickListener atividadeOnClickListener;

    public AtividadesAdapter(Context context, ArrayList<Atividade> atividadeArrayList, AtividadeOnClickListener atividadeOnClickListener) {
        this.context = context;
        this.atividadeArrayList = atividadeArrayList;
        this.atividadeOnClickListener = atividadeOnClickListener;
    }

    @Override
    public int getItemCount() {
        return this.atividadeArrayList != null ? this.atividadeArrayList.size() : 0;
    }

    @Override
    public AtividadesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_atividades, viewGroup, false);

        AtividadesViewHolder holder = new AtividadesViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final AtividadesViewHolder holder, final int position) {
        //Atualiza a view
        Atividade a = atividadeArrayList.get(position);
        Log.d("LogXadapter", "Atividade no adapter da RecyclerView: " + a.toString());

        holder.tvNomeAtividade.setText(a.getNome());
        holder.tvDataAtividade.setText(a.getData());

        //Click
        if (atividadeOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    atividadeOnClickListener.onClickAtividade(holder.itemView, position);
                }
            });
        }
    }

    public interface AtividadeOnClickListener {
        public void onClickAtividade(View view, int idx);
    }

    public class AtividadesViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeAtividade;
        TextView tvDataAtividade;

        public AtividadesViewHolder(View view) {
            super(view);

            tvNomeAtividade = (TextView) view.findViewById(R.id.tv_nome_atividade_adapter);
            tvDataAtividade = (TextView) view.findViewById(R.id.tv_data_adapter);
        }
    }


}
