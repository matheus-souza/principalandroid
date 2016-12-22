package br.gamesapp.principal_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import br.gamesapp.principal_android.MyApplication;
import br.gamesapp.principal_android.R;
import br.gamesapp.principal_android.activity.DadosAtividadeActivity;
import br.gamesapp.principal_android.adapter.AtividadesAdapter;
import br.gamesapp.principal_android.entidades.atividade.Atividade;

/**
 * Created by matheush on 15/12/16.
 */

public class AtividadesFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private MyApplication myApplication;

    private ArrayList<Atividade> atividades;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atividades, container, false);

        myApplication = (MyApplication) getActivity().getApplicationContext();

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragmetatividades);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        atividades = myApplication.getAtividadesAndamentoArrayList();
        AtividadesAdapter adapter = new AtividadesAdapter(getContext(), atividades, onClick());
        recyclerView.setAdapter(adapter);

        return view;
    }

    protected AtividadesAdapter.AtividadeOnClickListener onClick() {
        return new AtividadesAdapter.AtividadeOnClickListener() {
            @Override
            public void onClickAtividade(View view, int idx) {
                //Toast.makeText(getContext(), "CRICO MISERAVI"+idx, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), DadosAtividadeActivity.class);
                intent.putExtra("moderador", 1);
                intent.putExtra("atividadeid", idx);

                startActivity(intent);

            }
        };
    }
}
