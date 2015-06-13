package com.teamveryniceah.ping;

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

import com.skyfishjy.library.RippleBackground;

import at.markushi.ui.CircleButton;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class MainActivity extends ActionBarActivity implements MaterialTabListener, View.OnClickListener {

    private Toolbar mToolbar;
    private ViewPager mPager;
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

        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mPager = (ViewPager) findViewById(R.id.pager);
        mTabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        mPager.setAdapter(pageAdapter);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mTabHost.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < pageAdapter.getCount(); i++) {
            mTabHost.addTab(
                    mTabHost.newTab()
                            .setText(pageAdapter.getPageTitle(i))
                            .setTabListener(this)
            );
        }



    }

    public void fireButtonClick(View view) {
        final RippleBackground rippleBackground = (RippleBackground) findViewById(R.id.fireContent);
        if (rippleBackground.isRippleAnimationRunning()) {
            rippleBackground.stopRippleAnimation();
        } else {
            rippleBackground.startRippleAnimation();
        }
    }

    public void policeButtonClick(View view) {
        final RippleBackground rippleBackground = (RippleBackground) findViewById(R.id.policeContent);
        if (rippleBackground.isRippleAnimationRunning()) {
            rippleBackground.stopRippleAnimation();
        } else {
            rippleBackground.startRippleAnimation();
        }
    }

    public void ambButtonClick(View view) {
        final RippleBackground rippleBackground = (RippleBackground) findViewById(R.id.ambContent);
        if (rippleBackground.isRippleAnimationRunning()) {
            rippleBackground.stopRippleAnimation();
        } else {
            rippleBackground.startRippleAnimation();
        }
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
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


