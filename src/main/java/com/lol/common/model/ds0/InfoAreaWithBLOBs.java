package com.lol.common.model.ds0;

import java.io.Serializable;

public class InfoAreaWithBLOBs extends InfoArea implements Serializable {
    private String mapcontent;

    private String engdesc;

    private String description;

    private static final long serialVersionUID = 1L;

    public String getMapcontent() {
        return mapcontent;
    }

    public void setMapcontent(String mapcontent) {
        this.mapcontent = mapcontent;
    }

    public String getEngdesc() {
        return engdesc;
    }

    public void setEngdesc(String engdesc) {
        this.engdesc = engdesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}