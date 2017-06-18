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


    public static class Data implements Serializable {
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

        public void setName(String name) {
            this.name = name;
        }

        public void setJackpot(long jackpot) {
            this.jackpot = jackpot;
        }

        public void setDate(String date) {
            this.date = date;
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

    public void setResponse(String response) {
        this.response = response;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
