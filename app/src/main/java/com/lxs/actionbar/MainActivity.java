package com.lxs.actionbar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab tab;
        tab = actionBar.newTab().setText(R.string.tab_1).setTabListener(new TabListener<Tab1Fragment>(getApplicationContext(), "体育新闻", Tab1Fragment.class));
        actionBar.addTab(tab);
        tab = actionBar.newTab().setText(R.string.tab_2).setTabListener(new TabListener<Tab2Fragment>(getApplicationContext(), "体育新闻", Tab2Fragment.class));
        actionBar.addTab(tab);
        tab = actionBar.newTab().setText(R.string.tab_3).setTabListener(new TabListener<Tab3Fragment>(getApplicationContext(), "体育新闻", Tab3Fragment.class));
        actionBar.addTab(tab);
    }

    public void hideOrShow(View view) {
        if (actionBar.isShowing()) {
            actionBar.hide();
        } else {
            actionBar.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem;
        SearchView searchView;

        getMenuInflater().inflate(R.menu.menu, menu);
        menuItem = menu.findItem(R.id.menu_search);

        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        //可以针对searchView设置相应的监听函数
        //....searchView

        //设置折叠监听函数
        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Log.d("CESHI", "ActionView onMenuItemActionExpand");
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Log.d("CESHI", "ActionView onMenuItemActionCollapse");
                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("CESHI", "SearchView Submit:" + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("CESHI", "SearchView Change:" + newText);
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Log.d("CESHI", "SearchView onClose");
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                //Do something
                return true;
            case R.id.menu_camera:
                //Do something
                return true;
            case R.id.menu_delete:
                //Do something
                return true;
            case R.id.menu_search:
                //Do something
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    static class TabListener<T extends Fragment> implements ActionBar.TabListener {

        private Fragment mFragment;
        private Context mContext;
        private final String mTag;
        private final Class<T> mClass;


        public TabListener(Context context, String tag, Class<T> clazz) {
            mContext = context;
            mTag = tag;
            mClass = clazz;
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

            if (mFragment == null) {
                mFragment = Fragment.instantiate(mContext, mClass.getName());
                ft.add(R.id.fragment_content, mFragment, mTag);
            } else {
                ft.attach(mFragment);
            }

        }


        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            if (mFragment != null) {
                ft.detach(mFragment);
            }
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    }
}
