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

/**
 * Created by DonKamillo on 16.06.2017.
 */

public class PlayerHeaderFragment extends Fragment implements PlayerHeaderContract.View {
    private ImageView avatar;
    private TextView name, balance, lastLoginDate;
    private LinearLayout lastLoginLayout, mainLayout;

    public static PlayerHeaderFragment newInstance() {
        return new PlayerHeaderFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.player_header_fragment, container, false);
        avatar = (ImageView) view.findViewById(R.id.avatar);
        name = (TextView) view.findViewById(R.id.name);
        balance = (TextView) view.findViewById(R.id.balance);
        lastLoginDate = (TextView) view.findViewById(R.id.last_login_date);
        lastLoginLayout = (LinearLayout) view.findViewById(R.id.last_login_layout);
        mainLayout = (LinearLayout) view.findViewById(R.id.main_layout);

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
