package com.example.e_catalogo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static final String TAG_LOG = "e-catalogo";
	    
    //url
    //private static String url = "http://mobiletest.redirectme.net/api/BranchOffice/GetAll/peuque/";
	private static String url = "http://192.168.16.4/ndw2013/web/json/clientes.json";
    // JSON Node names
    private static final String TAG_PRODUCTOS = "clientes";
    private static final String TAG_NOMBRE = "nombre";
    private static final String TAG_IMAGEN = "logo";
    private static final String TAG_LINK = "link";
    
    
    
    
    private static RelativeLayout rl;
    
    //pantall dimension
    private int widthScreen = 0;
    private int cantidadColumnas = 3;
    private int widthColumn = 0;
    
    ImageAdapter im;
    
    GridView gridview;
    
    @Override
    public void onCreate(Bundle	savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy); 
        
        
        
        /// dimensiones de la pantalla
        Display display = getWindowManager().getDefaultDisplay();
        
        
        widthScreen = display.getWidth(); 
        
        widthColumn = (widthScreen/cantidadColumnas);        
        ///
        
        
        gridview = (GridView) findViewById(R.id.gridview);
        
        im = new ImageAdapter(getApplicationContext());
        gridview.setPadding(0, 0, 0, 0);
        gridview.setNumColumns(cantidadColumnas);
        gridview.setColumnWidth(widthColumn);
        gridview.setVerticalSpacing(1);
        
        gridview.setAdapter(im);

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	
            	Set set = im.getImagenViews().entrySet();

                Iterator i = set.iterator();

                int time = 0;
                //recorro todos los items
                while(i.hasNext()){
                	
                  final Map.Entry me = (Map.Entry)i.next();

                  
                  	// el item seleccionado no tiene animacion
              	 	if(position != (Integer) me.getKey()) {
              	 	
              	 	  //genero hilo para definirle la animacion a cada item
		              new Handler().postDelayed(new Runnable()
		              {
		              	 @Override
		              	 public void run()
		              	 {
		              	  
		              	 Animation hyperspaceJump = AnimationUtils.loadAnimation(MainActivity.this, R.anim.flyin);
		                  hyperspaceJump.setFillAfter(true);
		              	 ((View) im.getItem((Integer)me.getKey())).startAnimation(hyperspaceJump);
		              	}
		              }, time);
		              
		              time += 20;
              	 	}
                }
                	
                
                new Handler().postDelayed(new Runnable()
	              {
	              	 @Override
	              	 public void run()
	              	 {
	              	  
	              		Intent intent = new Intent(MainActivity.this, Detalles.class);
	                    
	                    startActivity(intent);
	              	}
	              }, 500);
                
                //Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            		
            }
        });
        
        
        JSONClient client = new JSONClient(this, l);
   		
        client.execute(url);     
        
        
    
    }

    GetJSONListener l = new GetJSONListener(){
    
		@Override
		public void onRemoteCallComplete(JSONObject jsonFromNet) {
			
			JSONArray ja = null;
			try {
				ja = jsonFromNet.getJSONArray(TAG_PRODUCTOS);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// add code to act on the JSON object that is returned			
			for(int i=1;i<ja.length();i++){
				
				JSONObject json_data;
				try {
					json_data = ja.getJSONObject(i);
					Log.d(TAG_LOG, json_data.toString());
					
					
					Log.d(TAG_LOG,"http://192.168.16.4/ndw2013/web/img/clientes/"+json_data.getString(TAG_IMAGEN));
				
					adaptadorAsyncTask aAT = new adaptadorAsyncTask();
		
					aAT.setUrl("http://192.168.16.4/ndw2013/web/img/clientes/"+json_data.getString(TAG_IMAGEN));
					
					new NetworkTask().execute(aAT);
					
			
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
			
			
		
			
			
		}
		
		
		
	};
	
	
	
	
	
	private class NetworkTask extends AsyncTask<adaptadorAsyncTask, Void, HttpResponse> {
		
				
		@Override
	    protected HttpResponse doInBackground(adaptadorAsyncTask... params) {
	        String link = params[0].getUrl();
	       
	        final HttpParams httpParameters = new BasicHttpParams();
	        final HttpClient client = new DefaultHttpClient(httpParameters);
	        
	        HttpResponse ret = null;
	        
	        try {
	        	ret =  client.execute(new HttpGet(link));
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ret;
	    }
		

	    @Override
	    protected  void onPostExecute(HttpResponse result) {
	    	
	    	
			
	    	
	    	Bitmap bitmap = null;
	        //Do something with result
	       if (result != null) {
	         
	    	   
	    	  
	            InputStream imageContentInputStream = null;
				try {
					 final HttpEntity entity = result.getEntity();
					imageContentInputStream = entity.getContent();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            //Notice that we are now wrapping the 
	            //imageContentInputStream in FlushedInputStream.
	            final Bitmap imageBitmap = 
	            			BitmapFactory.decodeStream(
	                    		   new BufferedInputStream(imageContentInputStream));
	            actualizarImagen(imageBitmap);
                
           
                
                
                Log.d(TAG_LOG, "pegando imagen");
				
                
	        }
	    }
	    
	    public void actualizarImagen (Bitmap bitmap) {
	    	//Log.d(TAG_LOG, bitmap.toString());
	    	im.addImagen(bitmap);
	    	
	    	im.notifyDataSetChanged();
	    	
	    	
	    }
		
	}
	
	
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
}
