package com.example.androidtermproject;


import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ClothAdapter extends BaseAdapter{

    private Context mContext;

    public ClothAdapter(Context c) {
        mContext = c;

    }

    ///// 이미지 추가 부분    ///여기를 이미지 어레이로 가져와서 넣어주자, 그러고 cody activity에서 onitemclick 에서 position을 읽어오면 이미지 어레이[포지션] 으로 셋 해주자
    private Integer[] mThumbIds = {

            R.drawable.cloth_pants,R.drawable.cloth_pants2,R.drawable.cloth_pants3,R.drawable.cloth_pants4,R.drawable.cloth_pants5,R.drawable.cloth_pants6,R.drawable.cloth_pants7,
            R.drawable.cloth_pants8

    };

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(200,200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }else{
            imageView = (ImageView)convertView;
        }

        imageView.setImageResource(mThumbIds[position]);

        return imageView;
    }

}