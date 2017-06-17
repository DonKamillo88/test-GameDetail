package com.donkamillo.gamedetails.data.models;

/**
 * Created by DonKamillo on 15.06.2017.
 */

public class PlayerInfo {

    private String name;
    private long balance;
    private String avatarLink;
    private String lastLogindate;

    public String getName() {
        return name;
    }

    public long getBalance() {
        return balance;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public String getLastLoginDate() {
        return lastLogindate;
    }
}
