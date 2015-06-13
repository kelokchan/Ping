package com.teamveryniceah.ping;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.skyfishjy.library.RippleBackground;
import com.teamveryniceah.ping.tabs.SlidingTabLayout;

import at.markushi.ui.CircleButton;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class MainActivity extends ActionBarActivity implements MaterialTabListener, View.OnClickListener {

    private Toolbar mToolbar;
    private ViewPager mPager;
    private SlidingTabLayout mTabs;
    private MaterialTabHost mTabHost;
    private CircleButton mPoliceButton;
    private CircleButton mAmbButton;
    private CircleButton mFireButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPoliceButton = (CircleButton) findViewById(R.id.policeButton);
        mAmbButton = (CircleButton) findViewById(R.id.ambButton);
        mFireButton = (CircleButton) findViewById(R.id.fireButton);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "font/levenim.ttf");
        TextView myTextView = (TextView) findViewById(R.id.toolbar_title);
        myTextView.setTypeface(myTypeface);

        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mTabs.setViewPager(mPager);

        mTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.indicatorColor);
            }
        });

        mTabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mToolbar.setBackgroundColor(getResources().getColor(R.color.blue));
                        mTabs.setBackgroundColor(getResources().getColor(R.color.blue));
                        getWindow().setStatusBarColor(getResources().getColor(R.color.blue_dark));
                        break;
                    case 1:
                        mToolbar.setBackgroundColor(getResources().getColor(R.color.green));
                        mTabs.setBackgroundColor(getResources().getColor(R.color.green));
                        getWindow().setStatusBarColor(getResources().getColor(R.color.green_dark));
                        break;
                    case 2:
                        mToolbar.setBackgroundColor(getResources().getColor(R.color.orange));
                        mTabs.setBackgroundColor(getResources().getColor(R.color.orange));
                        getWindow().setStatusBarColor(getResources().getColor(R.color.orange_dark));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void fireButtonClick(View view) {
        final RippleBackground rippleBackground = (RippleBackground) findViewById(R.id.fireContent);
        showRipple(rippleBackground);


    }

    public void policeButtonClick(View view) {
        final RippleBackground rippleBackground = (RippleBackground) findViewById(R.id.policeContent);
        showRipple(rippleBackground);

    }

    public void ambButtonClick(View view) {
        final RippleBackground rippleBackground = (RippleBackground) findViewById(R.id.ambContent);
        showRipple(rippleBackground);

    }

    public void showRipple(RippleBackground rippleBackground) {
        if (rippleBackground.isRippleAnimationRunning()) {
            rippleBackground.stopRippleAnimation();
        } else {
            rippleBackground.startRippleAnimation();
            notifyUser();
        }
    }

    public void notifyUser() {
        Toast.makeText(MainActivity.this, "Notifying nearest department", Toast.LENGTH_LONG).show();
        Toast.makeText(MainActivity.this, "Request acknowledged", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_nearby) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
////        Window window = this.getWindow();
////        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
////        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//
//        switch (materialTab.getPosition()) {
//            case 0:
//                mToolbar.setBackgroundColor(getResources().getColor(R.color.blue));
//                materialTab.setPrimaryColor(getResources().getColor(R.color.blue));
////                window.setStatusBarColor(this.getResources().getColor(R.color.blue));
//                break;
//            case 1:
//                mToolbar.setBackgroundColor(getResources().getColor(R.color.green));
//                materialTab.setPrimaryColor(getResources().getColor(R.color.green));
////                window.setStatusBarColor(this.getResources().getColor(R.color.green));
//                break;
//            case 2:
//                mToolbar.setBackgroundColor(getResources().getColor(R.color.orange));
//                materialTab.setPrimaryColor(getResources().getColor(R.color.orange));
////                window.setStatusBarColor(this.getResources().getColor(R.color.orange));
//                break;
//        }
        mPager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {
//        switch (materialTab.getPosition()) {
//            case 0:
//                materialTab.setAccentColor(getResources().getColor(R.color.blue));
//                break;
//            case 1:
//                materialTab.setAccentColor(getResources().getColor(R.color.green));
//                break;
//            case 2:
//                materialTab.setAccentColor(getResources().getColor(R.color.orange));
//                break;
//        }
    }

    @Override
    public void onClick(View v) {

    }

    private class PageAdapter extends FragmentStatePagerAdapter {

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.tabs)[position];
        }


        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new PoliceFragment();
                case 1:
                    return new AmbFragment();
                case 2:
                    return new FireFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}


