package com.devghost.malaysianvisastatus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findIds();
        loadHomeFrag();
//        loadBannerAd();
    }

    private void findIds() {

        webView=findViewById(R.id.webview);
        // mAdView = findViewById(R.id.adView);
    }

    private void loadHomeFrag() {
        FragmentManager fragment = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragment.beginTransaction();
        fragmentTransaction.replace(R.id.mainFrag,new Home());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.contactUsId){
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"jyspark668@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, "mail from Malaysian visa");
            i.putExtra(Intent.EXTRA_TEXT   , "body of email");
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        }

        else if (item.getItemId()==R.id.pp){
            webView.loadUrl(getString(R.string.pp_link));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {


        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            //additional code
            AlertDialog.Builder alertdialogbuilder;
            alertdialogbuilder=new AlertDialog.Builder(MainActivity.this);
            alertdialogbuilder.setIcon(R.drawable.visa_check_icon);
            alertdialogbuilder.setTitle("Quit?");
            alertdialogbuilder.setMessage("Do u want to Quit?");
            alertdialogbuilder.setCancelable(false);

            alertdialogbuilder.setPositiveButton("yes", (dialogInterface, i) -> finish());

            alertdialogbuilder.setNegativeButton("no", (dialogInterface, i) -> dialogInterface.cancel());

            AlertDialog alertDialog = alertdialogbuilder.create();
            alertDialog.show();
        } else {
            getSupportFragmentManager().popBackStack();
        }


    }
}