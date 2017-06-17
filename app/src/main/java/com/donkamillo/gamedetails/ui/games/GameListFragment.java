package com.donkamillo.gamedetails.ui.games;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.donkamillo.gamedetails.R;
import com.donkamillo.gamedetails.data.models.GameData;

/**
 * Created by DonKamillo on 16.06.2017.
 */

public class GameListFragment extends Fragment implements GamesContract.View {

    private OnItemSelectedListener onItemSelectedListener;
    private GamesCardsAdapter adapter;
    private ProgressBar progressBar;
    private String currency;

    public interface OnItemSelectedListener {
        void onItemSelected(GameData.Data data, String currency);
    }

    public static GameListFragment newInstance() {
        return new GameListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            onItemSelectedListener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implemenet GameListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener) {
            onItemSelectedListener = (OnItemSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString() + " must implemenet GameListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.games_list_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_view);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        adapter = new GamesCardsAdapter(getContext(), new GamesCardsAdapter.GameCardsInterface() {
            @Override
            public void onItemClick(GameData.Data game) {
                onItemSelectedListener.onItemSelected(game, currency);
            }
        });
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        GamesPresenter gamesPresenter = new GamesPresenter(this);
        gamesPresenter.getGames(getContext());
    }

    @Override
    public void updateGamesData(GameData gamesData) {
        if (gamesData != null) {
            currency = gamesData.getCurrency();
            adapter.swapItems(gamesData.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setProgressBar(boolean b) {
        progressBar.setVisibility(b ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }


}

