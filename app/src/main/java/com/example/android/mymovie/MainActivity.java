package com.example.android.mymovie;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.i(TAG,"navigation fragment has been changed");
                switch (item.getItemId()){
                    case R.id.navigation_movie:
                        switchFragment(NavigationFragment.newInstance(getString(R.string.title_movie)));
                        return true;
                    case R.id.navigation_tv:
                        switchFragment(NavigationFragment.newInstance(getString(R.string.title_tv)));
                        return true;
                    case R.id.navigation_my_list:
                        switchFragment(NavigationFragment.newInstance(getString(R.string.title_my_list)));
                        return true;
                }
                return false;
            }
        });
        //switchFragment(new NavigationFragment());
        switchFragment(NavigationFragment.newInstance(getString(R.string.title_movie)));
        Log.i(TAG,"the first fragment is added");
    }

    private void switchFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.navigation_fragment_container,fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_menu_action).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        //searchView.setIconifiedByDefault(false);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search_menu_action:
                //todo:figure out how to realize search func
                onSearchRequested();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

}
