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

public class Detalles extends Activity {
	
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
        setContentView(R.layout.detalles);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy); 
        
        
        
        /// dimensiones de la pantalla
        Display display = getWindowManager().getDefaultDisplay();       
        
        widthScreen = display.getWidth(); 
        
        widthColumn = (widthScreen/cantidadColumnas);        
        ///
        
        
         
        
        
    
    }

   
	
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
}
