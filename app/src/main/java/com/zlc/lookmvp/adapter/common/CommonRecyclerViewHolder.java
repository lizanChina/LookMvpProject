package com.zlc.lookmvp.adapter.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlc.lookmvp.util.ImageUtil;


/**
 * Created by zlc on 2017/2/8.
 */

public class CommonRecyclerViewHolder extends RecyclerView.ViewHolder{

    private final SparseArray<View> mViews;
    private View mConvertView;
    private int mPosition;
    private Context mContext;
    public CommonRecyclerViewHolder(Context context,View itemView) {
        super(itemView);
        this.mConvertView = itemView;
        this.mContext = context;
        this.mViews = new SparseArray<>();
    }

    /**
     * 通过控件的Id获取对应的控件，如果没有则加入views
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId){

        View view = mViews.get(viewId);
        if(view==null && mConvertView!=null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public CommonRecyclerViewHolder setText(int viewId, String text)
    {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     */
    public CommonRecyclerViewHolder setImageResource(int viewId,int resId){

        ImageView imageView = getView(viewId);
        imageView.setImageResource(resId);
        return this;
    }

    /**
     * 为ImageView设置图片
     */
    public CommonRecyclerViewHolder setImageBitmap(int viewId,Bitmap bitmap){

        ImageView imageView = getView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }


    public void showImage(int viewId,String imageUrl){
        ImageView view = getView(viewId);
        ImageUtil.show(view,imageUrl);
    }

}
