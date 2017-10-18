package com.lol.common.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * EasyUI树形结构
 * 
 * @author cunxing
 *
 */
public class EasyUITree implements Serializable {

    /**
     * 序列ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 节点id
     */
    private long id;

    /**
     * 节点文本
     */
    private String text;

    /**
     * 菜单图标
     */
    private String iconCls;

    /**
     * 节点状态open、closed
     */
    private String state = "open";

    /**
     * 是否选中
     */
    private boolean checked;

    /**
     * 自定义属性
     */
    private Map<String, Object> attributes;

    /**
     * 递归子节点
     */
    private List<EasyUITree> children;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public List<EasyUITree> getChildren() {
        return children;
    }

    public void setChildren(List<EasyUITree> children) {
        this.children = children;
    }
}
