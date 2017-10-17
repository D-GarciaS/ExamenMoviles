package com.example.damiangarcia.tareasettings;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.damiangarcia.tareasettings.Beans.ItemProduct;
import com.example.damiangarcia.tareasettings.Constants.Constants;

import java.util.Locale;

public class ActivityMain extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentTechnology fragmentTechnology;
    private FragmentHome fragmentHome;
    private FragmentElectronics fragmentElectronics;


    SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if(fragmentTechnology == null){
                        fragmentTechnology = new FragmentTechnology();
                    }
                    return fragmentTechnology;
                case 1:
                    if(fragmentHome == null){
                        fragmentHome = new FragmentHome();
                    }
                    return fragmentHome;
                case 2:
                    if(fragmentElectronics == null){
                        fragmentElectronics = new FragmentElectronics();
                    }
                    return fragmentElectronics;
                default:
                    return new FragmentTechnology();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu, menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.activity_main_logout:
                clearPreferences();
                return  true;
            case R.id.activity_main_profile:
                return true;
            case R.id.activity_main_privacy:
                Intent intent = new Intent(this, ActivityPrivacyPolicy.class);
                startActivity(intent);
                return true;
            case R.id.action_products:
                Intent products = new Intent(this, ActivityProduct.class);
                startActivityForResult(products, Constants.INTENT_PRODUCTS_NOTIFY);
                break;
        }
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case Constants.INTENT_PRODUCTS_NOTIFY:
                if (resultCode == Activity.RESULT_OK) {
                    if(data != null){
                        ItemProduct itemProduct = data.getParcelableExtra("ITEM");
                        if(itemProduct.getCategory().getName().equalsIgnoreCase("TECHNOLOGY")){
                            fragmentTechnology.notifyDataSetChanged(itemProduct);
                        }else if(itemProduct.getCategory().getName().equalsIgnoreCase("HOME")){
                            fragmentHome.notifyDataSetChanged(itemProduct);
                        }else if(itemProduct.getCategory().getName().equalsIgnoreCase("ELECTRONICS")){
                            fragmentElectronics.notifyDataSetChanged(itemProduct);
                        }
                    }
                }
                break;
        }
    }

    public void clearPreferences(){
        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.tarea2.CACAHUATE",
                        Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();

        Intent intent = new Intent(this, ActivityLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}



