package com.example.e_catalogo;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }
    
    public void addImagen (Bitmap bitmap) {
    	 bitmapArray.add(bitmap); // Add a bitmap
    }
    
    public ArrayList getBitmapArray () {
    	    	
		return bitmapArray;
    }
    public HashMap getImagenViews () {
		return imageViewArray;
    	
    	
    }
    
    

    public int getCount() {
        return bitmapArray.size();
    }

    public Object getItem(int position) {
        return imageViewArray.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) { // if it's not recycled, initialize some
                                    // attributes
            imageView = new ImageView(mContext);
        
            imageView.setPadding(0, 0, 0, 0);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER );
            
        } else {
        	
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(bitmapArray.get(position));
        
        imageViewArray.put(position,imageView);
        
        return imageView;
    }

    // references to our images
   // private Bitmap[] mThumbIds = {};
    
    private ArrayList<Bitmap> bitmapArray = new ArrayList<Bitmap>();
    private HashMap<Integer,ImageView> imageViewArray = new HashMap<Integer,ImageView>();
   
}