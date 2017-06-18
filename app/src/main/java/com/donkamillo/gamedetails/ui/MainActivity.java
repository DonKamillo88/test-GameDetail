package com.donkamillo.gamedetails.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.donkamillo.gamedetails.R;
import com.donkamillo.gamedetails.data.models.GameData;
import com.donkamillo.gamedetails.ui.gamedetail.GameDetailsFragment;
import com.donkamillo.gamedetails.ui.games.GameListFragment;
import com.donkamillo.gamedetails.ui.playerheader.PlayerHeaderFragment;
import com.donkamillo.gamedetails.ui.playerheader.PlayerHeaderPresenter;

public class MainActivity extends AppCompatActivity implements GameListFragment.OnItemSelectedListener {

    private FragmentManager fragmentManager;
    private PlayerHeaderPresenter playerHeaderPresenter;
    private FrameLayout frameDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        GameListFragment gamesFragment = GameListFragment.newInstance();
        ft.add(R.id.games_fragment_placeholder, gamesFragment);

        PlayerHeaderFragment headerFragment = PlayerHeaderFragment.newInstance();
        ft.add(R.id.header_fragment_placeholder, headerFragment);

        frameDetail = (FrameLayout) findViewById(R.id.game_details_fragment_placeholder);
        if (frameDetail != null) {
            GameDetailsFragment detailFragment = GameDetailsFragment.newInstance();
            ft.add(R.id.game_details_fragment_placeholder, detailFragment);
        }

        ft.commit();

        playerHeaderPresenter = new PlayerHeaderPresenter(headerFragment);
        playerHeaderPresenter.getPlayerInfo(this);
    }

    @Override
    public void onItemSelected(GameData.Data gameData, String currency) {
        if (frameDetail == null) {
            GameDetailsFragment detailFragment = GameDetailsFragment.newInstance(gameData, currency);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.games_fragment_placeholder, detailFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            playerHeaderPresenter.showLastLogin(false);
        } else {
            GameDetailsFragment gameDetailsFragment = (GameDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.game_details_fragment_placeholder);
            gameDetailsFragment.updateDate(gameData, currency);
        }
    }

    @Override
    public void onBackPressed() {
        int count = fragmentManager.getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        } else {
            fragmentManager.popBackStack();
            playerHeaderPresenter.showLastLogin(true);
        }
    }
}