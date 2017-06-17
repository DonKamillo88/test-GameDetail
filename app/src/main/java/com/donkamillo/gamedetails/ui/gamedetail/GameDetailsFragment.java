package com.donkamillo.gamedetails.ui.gamedetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.donkamillo.gamedetails.R;
import com.donkamillo.gamedetails.data.models.GameData;
import com.donkamillo.gamedetails.util.Utils;

/**
 * Created by DonKamillo on 16.06.2017.
 */

public class GameDetailsFragment extends Fragment {
    public static final String GAME_DETAIL_ARG = "game_detail";
    public static final String CURRENCY_ARG = "currency";

    private LinearLayout mainLL;
    private TextView nameTV, jackpotTV, dateTV;

    public static GameDetailsFragment newInstance(GameData.Data gameData, String currency) {
        Bundle b = new Bundle();
        b.putString(CURRENCY_ARG, currency);
        b.putSerializable(GAME_DETAIL_ARG, gameData);

        final GameDetailsFragment fragment = new GameDetailsFragment();
        fragment.setArguments(b);
        return fragment;
    }

    public static GameDetailsFragment newInstance() {
        return new GameDetailsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.game_details_fragment, container, false);

        mainLL = (LinearLayout) view.findViewById(R.id.main_layout);
        nameTV = (TextView) view.findViewById(R.id.name);
        jackpotTV = (TextView) view.findViewById(R.id.jackpot);
        dateTV = (TextView) view.findViewById(R.id.date);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String currency = bundle.getString(CURRENCY_ARG);
            GameData.Data gameData = (GameData.Data) bundle.getSerializable(GAME_DETAIL_ARG);
            updateDate(gameData, currency);
        }
    }

    public void updateDate(GameData.Data gameData, String currency) {
        nameTV.setText(gameData.getName());
        dateTV.setText(Utils.getFormattedDate(gameData.getDate(), Utils.GAME_DATE_FORMAT));
        jackpotTV.setText(Utils.getFormattedCurrency(currency, gameData.getJackpot()));
        mainLL.setVisibility(View.VISIBLE);
    }

}
