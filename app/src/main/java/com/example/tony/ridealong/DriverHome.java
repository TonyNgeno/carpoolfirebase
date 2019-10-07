package com.example.tony.ridealong;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tony.ridealong.Fragments.About;
import com.example.tony.ridealong.Fragments.Home;
import com.example.tony.ridealong.Fragments.Profile;
import com.example.tony.ridealong.Fragments.Rides;
import com.example.tony.ridealong.Fragments.Settings;
import com.google.android.material.navigation.NavigationView;

import java.util.logging.Handler;

public class DriverHome extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;


    //index to identify current nav menu item
    public static int navItemIndex = 0;

    //tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_RIDER = "myriders";
    private static final String TAG_SETTINGS = "settings";
    private static final String TAG_PROFILE = "profile";
    private static final String TAG_ABOUT = "about";
    public static String CURRENT_TAG = TAG_HOME;

    //toolbar titles respected to selected nav menu item
    private String[] activityTitles;


    //flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPres = true;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_driver_home);
        toolbar = findViewById(R.id.toolbar);
        /*setSupportActionBar(toolbar);*/

       /* mHandler = new Handler();*/

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);


        //load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);


        //initializing navigation menu
        setUpNavigationView();


        if (savedInstanceState == null){
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }

    }

    private void loadHomeFragment() {
        //selecting appropriate nav menu item
        selectNavMenu();


        //set toolbar title
        setToolbarTitle();

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null){
            drawerLayout.closeDrawers();
            return;
        }


        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                //update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame,fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        /*//if mpendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null){
            mHandler.post(mPendingRunnable);
        }*/

        //closing drawer on item click
        drawerLayout.closeDrawers();


        //refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment(){
        switch (navItemIndex){
            case 0:
                //home
                Home home = new Home();
                return home;
            case 1:
                //rides
                Rides rides = new Rides();
                return rides;
            case 2:
                //settings
                Settings settings = new Settings();
                return settings;
            case 3:
                //profile
                Profile profile = new Profile();
                return profile;
            case 4:
                //about
                About about = new About();
                return about;
             default:
                 return new Home();

        }
    }

    private void setToolbarTitle(){
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu(){
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView(){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_my_riders:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_RIDER;
                        break;
                    case R.id.nav_Settings:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;
                    case R.id.nav_profile:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_PROFILE;
                        break;
                    case R.id.nav_about:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_ABOUT;
                        break;
                    default:
                        navItemIndex = 0;
                }

                if (menuItem.isChecked()){
                    menuItem.setChecked(false);
                }else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });

        /*ActionBarDrawerToggle actionBarDrawerToggle  = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer){


            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };*/
/*
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();*/
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawers();
            return;
        }

        if (shouldLoadHomeFragOnBackPres){
            if (navItemIndex != 0){
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }
        super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.driver_home, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings){
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
