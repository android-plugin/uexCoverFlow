package org.zywx.wbpalmstar.plugin.uexcoverflow.vo;

import java.io.Serializable;

/**
 * Created by ylt on 15/5/26.
 */
public class CoverFlowParamVO implements Serializable {

    private static final long serialVersionUID = -8122944347715336638L;

    private int x;
    private int y;
    private int width;
    private int height;
    private String viewId;
    private String[] urls;
    private int coverWidth=360;
    private int coverHeight=480;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }

    public int getCoverWidth() {
        return coverWidth;
    }

    public void setCoverWidth(int coverWidth) {
        this.coverWidth = coverWidth;
    }

    public int getCoverHeight() {
        return coverHeight;
    }

    public void setCoverHeight(int coverHeight) {
        this.coverHeight = coverHeight;
    }
}
