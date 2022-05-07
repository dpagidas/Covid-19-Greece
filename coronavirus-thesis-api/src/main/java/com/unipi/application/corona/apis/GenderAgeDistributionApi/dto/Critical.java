package com.unipi.application.corona.apis.GenderAgeDistributionApi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Critical {

    @JsonProperty("0-17")
    public int _017;

    @JsonProperty("18-39")
    public int _1839;

    @JsonProperty("40-64")
    public int _4064;

    @JsonProperty("65+")
    public int _65;

    public int get_017() {
        return _017;
    }

    public void set_017(int _017) {
        this._017 = _017;
    }

    public int get_1839() {
        return _1839;
    }

    public void set_1839(int _1839) {
        this._1839 = _1839;
    }

    public int get_4064() {
        return _4064;
    }

    public void set_4064(int _4064) {
        this._4064 = _4064;
    }

    public int get_65() {
        return _65;
    }

    public void set_65(int _65) {
        this._65 = _65;
    }
}
