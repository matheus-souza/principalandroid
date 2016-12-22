package br.gamesapp.principal_android.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.gamesapp.principal_android.R;
import br.gamesapp.principal_android.fragment.AtividadesConcluidasFragment;
import br.gamesapp.principal_android.fragment.AtividadesFragment;

/**
 * Created by matheush on 15/12/16.
 */

public class TabsAdapter extends FragmentPagerAdapter {

    private Context context;

    public TabsAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        Bundle args = new Bundle();
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new AtividadesFragment();
                args.putString("tipo", context.getString(R.string.tabs_andamento));
                break;
            case 1:
                fragment = new AtividadesConcluidasFragment();
                args.putString("tipo", context.getString(R.string.tabs_concluidas));
                break;
        }

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return context.getString(R.string.tabs_andamento);
            case 1:
                return context.getString(R.string.tabs_concluidas);
        }

        return null;
    }
}
