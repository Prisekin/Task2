package com.Prisekin.Task2;

/**
 * Created with IntelliJ IDEA.
 * User: prisekin
 * Date: 16.11.13
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.view.View;
import android.graphics.Bitmap;

public class WebPage extends Activity{
    WebView web_page; ProgressBar load_status;
    @Override
    public void onCreate(Bundle bund){
        super.onCreate(bund);
        setContentView(R.layout.web_page);
        web_page=(WebView)findViewById(R.id.web_page);
        load_status=(ProgressBar)findViewById(R.id.load_status);
        web_page.setWebViewClient(new WebClient());
        web_page.loadUrl(getIntent().getStringExtra("page_url"));
    }
class WebClient extends WebViewClient{
@Override
public void onPageStarted(WebView v,String url, Bitmap bmp){
load_status.setVisibility(View.VISIBLE);
}
@Override
public void onPageFinished(WebView v,String url){
load_status.setVisibility(View.GONE);
}
}
}
