package com.donkamillo.gamedetails.data.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DonKamillo on 15.06.2017.
 */

public class GameData implements Serializable {

    private String response;
    private String currency;
    private List<Data> data;


    public class Data implements Serializable {
        private String name;
        private long jackpot;
        private String date;

        public String getName() {
            return name;
        }

        public long getJackpot() {
            return jackpot;
        }

        public String getDate() {
            return date;
        }
    }

    public String getResponse() {
        return response;
    }

    public String getCurrency() {
        return currency;
    }

    public List<Data> getData() {
        return data;
    }
}
