package org.gleason.superhockey;

import org.gleason.superhockey.fragment.Level1Fragment;
import org.gleason.superhockey.fragment.Level2Fragment;
import org.gleason.superhockey.fragment.Level3Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
public class LevelSelectorFragmentActivity extends FragmentActivity {

//	private static final int NUMBER_OF_PAGES = 3;
	private static final int NUMBER_OF_PAGES = 2;
	private LevelFragmentPagerAdapter mAdapter;
	private ViewPager viewPager;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level);
		viewPager = (ViewPager) findViewById(R.id.levelViewPager);
		this.mAdapter = new LevelFragmentPagerAdapter(this,viewPager);
	}
	
	private static class LevelFragmentPagerAdapter extends FragmentPagerAdapter{
		
		Fragment[] fragments = new Fragment[NUMBER_OF_PAGES];
		Activity a;
		
		public LevelFragmentPagerAdapter(FragmentActivity a, ViewPager pager) {
			super(a.getSupportFragmentManager());
			this.a = a;
			pager.setAdapter(this);
			fragments[0] = Level1Fragment.newInstance();
			fragments[1] = Level2Fragment.newInstance();
			
		}

		@Override
		public Fragment getItem(int position) {
			return fragments[position];
		}

		@Override
		public int getCount() {
			return NUMBER_OF_PAGES;
		}

		

	}
	
}
