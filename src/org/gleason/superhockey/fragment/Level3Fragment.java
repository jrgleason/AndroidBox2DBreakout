package org.gleason.superhockey.fragment;

import org.gleason.superhockey.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Level3Fragment extends Fragment {
	public static Level3Fragment newInstance() {
		return new Level3Fragment();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_level3, container, false);
	}
}
