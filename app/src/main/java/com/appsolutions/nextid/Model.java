package com.appsolutions.nextid;

import com.parse.ParseObject;

/**
 * Created by macuser on 8/3/15.
 */
public class Model {

    public String compNames;

    public Model(ParseObject object){
        this.compNames = object.getString("compName");
    }

    public String getCompNames() {
        return compNames;
    }
}
