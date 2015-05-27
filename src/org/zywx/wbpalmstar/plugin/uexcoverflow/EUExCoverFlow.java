package org.zywx.wbpalmstar.plugin.uexcoverflow;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;

import org.zywx.wbpalmstar.engine.DataHelper;
import org.zywx.wbpalmstar.engine.EBrowserView;
import org.zywx.wbpalmstar.engine.universalex.EUExBase;
import org.zywx.wbpalmstar.plugin.uexcoverflow.vo.CoverFlowParamVO;

import java.util.HashMap;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class EUExCoverFlow extends EUExBase {

    private static final String BUNDLE_DATA = "data";
    private static final int MSG_CREATE_NEW = 1;
    private static final int MSG_REMOVE = 2;

    public EUExCoverFlow(Context context, EBrowserView eBrowserView) {
        super(context, eBrowserView);
    }

    @Override
    protected boolean clean() {
        return false;
    }


    public void createNew(String[] params) {
        if (params == null || params.length < 1) {
            errorCallback(0, 0, "error params!");
            return;
        }
        Message msg = new Message();
        msg.obj = this;
        msg.what = MSG_CREATE_NEW;
        Bundle bd = new Bundle();
        bd.putStringArray(BUNDLE_DATA, params);
        msg.setData(bd);
        mHandler.sendMessage(msg);
    }

    private void createNewMsg(String[] params) {
        String json = params[0];
        final CoverFlowParamVO paramVO= DataHelper.gson.fromJson(json,CoverFlowParamVO.class);
        FeatureCoverFlow coverFlow=new FeatureCoverFlow(mContext);
        CoverFlowAdapter adapter=new CoverFlowAdapter(mContext,paramVO.getUrls());
        coverFlow.setAdapter(adapter);
        coverFlow.setCoverHeight(paramVO.getCoverHeight());
        coverFlow.setCoverWidth(paramVO.getCoverWidth());
        coverFlow.setMaxScaleFactor(1.2f);
        coverFlow.setRotationTreshold(0.5f);
        coverFlow.setScalingThreshold(0.5f);
        coverFlow.setReflectionGap(0);
        coverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, Object> result = new HashMap<String, Object>();
                result.put("position", position);
                result.put("viewId", paramVO.getViewId());
                callBackPluginJs(JsConst.ON_ITEM_PIC_CLICK, DataHelper.gson.toJson(result));
            }
        });
        coverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                HashMap<String, Object> result = new HashMap<String, Object>();
                result.put("position", position);
                result.put("viewId", paramVO.getViewId());
                callBackPluginJs(JsConst.ON_SCROLL_TO_POSITION, DataHelper.gson.toJson(result));
            }

            @Override
            public void onScrolling() {

            }
        });
        AbsoluteLayout.LayoutParams layoutParams=new AbsoluteLayout.LayoutParams(paramVO.getWidth(),
                paramVO.getHeight(),paramVO.getX(),paramVO.getY());
        addViewToWebView(coverFlow, layoutParams, paramVO.getViewId());
    }

    public void remove(String[] params) {
        if (params == null || params.length < 1) {
            errorCallback(0, 0, "error params!");
            return;
        }
        Message msg = new Message();
        msg.obj = this;
        msg.what = MSG_REMOVE;
        Bundle bd = new Bundle();
        bd.putStringArray(BUNDLE_DATA, params);
        msg.setData(bd);
        mHandler.sendMessage(msg);
    }

    private void removeMsg(String[] params) {
        String json = params[0];
        final CoverFlowParamVO paramVO= DataHelper.gson.fromJson(json,CoverFlowParamVO.class);
        if (paramVO!=null){
            removeViewFromWebView(paramVO.getViewId());
        }
     }

    @Override
    public void onHandleMessage(Message message) {
        if(message == null){
            return;
        }
        Bundle bundle=message.getData();
        switch (message.what) {

            case MSG_CREATE_NEW:
                createNewMsg(bundle.getStringArray(BUNDLE_DATA));
                break;
            case MSG_REMOVE:
                removeMsg(bundle.getStringArray(BUNDLE_DATA));
                break;
            default:
                super.onHandleMessage(message);
        }
    }

    private void callBackPluginJs(String methodName, String jsonData){
        String js = SCRIPT_HEADER + "if(" + methodName + "){"
                + methodName + "('" + jsonData + "');}";
        onCallback(js);
    }

}
