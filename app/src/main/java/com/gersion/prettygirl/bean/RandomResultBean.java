package com.gersion.prettygirl.bean;

import java.util.List;

/**
 * Created by aa326 on 2017/12/17.
 */

public class RandomResultBean extends BaseResultBean {


    private List<GirlCategory> data;

    public List<GirlCategory> getData() {
        return data;
    }

    public void setData(List<GirlCategory> data) {
        this.data = data;
    }

}
