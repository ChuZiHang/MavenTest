package com.lol.common.bean;

import java.util.Date;


public class OrderfinishBean {

    private Long id;

    private Date payTime;

    private String linkMan;

    private String linkPhone;

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public Date getPayTime() {
        return payTime;
    }

    
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    
    public String getLinkMan() {
        return linkMan;
    }

    
    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    
    public String getLinkPhone() {
        return linkPhone;
    }

    
    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((linkMan == null) ? 0 : linkMan.hashCode());
        result = prime * result + ((linkPhone == null) ? 0 : linkPhone.hashCode());
        result = prime * result + ((payTime == null) ? 0 : payTime.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        OrderfinishBean other = (OrderfinishBean)obj;
        if(id == null) {
            if(other.id != null)
                return false;
        } else if(!id.equals(other.id))
            return false;
        if(linkMan == null) {
            if(other.linkMan != null)
                return false;
        } else if(!linkMan.equals(other.linkMan))
            return false;
        if(linkPhone == null) {
            if(other.linkPhone != null)
                return false;
        } else if(!linkPhone.equals(other.linkPhone))
            return false;
        if(payTime == null) {
            if(other.payTime != null)
                return false;
        } else if(!payTime.equals(other.payTime))
            return false;
        return true;
    }

}
