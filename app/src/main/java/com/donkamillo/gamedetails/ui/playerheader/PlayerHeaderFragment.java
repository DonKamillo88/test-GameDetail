package com.donkamillo.gamedetails.ui.playerheader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.donkamillo.gamedetails.R;
import com.donkamillo.gamedetails.data.models.PlayerInfo;
import com.donkamillo.gamedetails.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DonKamillo on 16.06.2017.
 */

public class PlayerHeaderFragment extends Fragment implements PlayerHeaderContract.View {

    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.last_login_date)
    TextView lastLoginDate;
    @BindView(R.id.last_login_layout)
    LinearLayout lastLoginLayout;
    @BindView(R.id.main_layout)
    LinearLayout mainLayout;

    public static PlayerHeaderFragment newInstance() {
        return new PlayerHeaderFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_header_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLastLogin(boolean isShow) {
        lastLoginLayout.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void updatePlayerInfo(PlayerInfo playerInfo) {
        mainLayout.setVisibility(View.VISIBLE);
        showGameImage(playerInfo.getAvatarLink());
        name.setText(playerInfo.getName());
        balance.setText(Utils.getFormattedCurrency(playerInfo.getBalance()));
        lastLoginDate.setText(Utils.getFormattedDate(playerInfo.getLastLoginDate(), Utils.LAST_LOGIN_DATE_FORMAT));
    }

    @Override
    public void showErrorMessage(String string) {

    }

    public void showGameImage(String imageUrl) {
        Glide.with(this).load(imageUrl).into(avatar);
    }

}
