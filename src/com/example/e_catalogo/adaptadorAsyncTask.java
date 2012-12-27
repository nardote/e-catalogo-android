package com.example.e_catalogo;

import android.widget.ImageView;
import android.widget.RelativeLayout;

public class adaptadorAsyncTask {

	private String url = null;
	private ImageView iv = null;
	private RelativeLayout ly = null;
	
	public RelativeLayout getLy() {
		return ly;
	}
	public void setLy(RelativeLayout ly) {
		this.ly = ly;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ImageView getIv() {
		return iv;
	}
	public void setIv(ImageView iv) {
		this.iv = iv;
	}	
	

}