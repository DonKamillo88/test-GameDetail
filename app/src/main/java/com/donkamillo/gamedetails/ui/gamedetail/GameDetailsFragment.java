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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by DonKamillo on 16.06.2017.
 */

public class GameDetailsFragment extends Fragment {
    public static final String GAME_DETAIL_ARG = "game_detail";
    public static final String CURRENCY_ARG = "currency";

    @BindView(R.id.main_layout)
    LinearLayout mainLL;
    @BindView(R.id.details_game_name)
    TextView nameTV;
    @BindView(R.id.jackpot)
    TextView jackpotTV;
    @BindView(R.id.date)
    TextView dateTV;

    private Unbinder unbinder;

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
        unbinder = ButterKnife.bind(this, view);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
