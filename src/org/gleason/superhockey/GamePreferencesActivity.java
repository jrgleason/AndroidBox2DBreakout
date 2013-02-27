package org.gleason.superhockey;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

public class GamePreferencesActivity extends PreferenceActivity {

	int tintColor = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new PrefsFragment()).commit();

	}

	public static class PrefsFragment extends PreferenceFragment implements
			OnSharedPreferenceChangeListener, OnPreferenceClickListener {

		@Override
		public void onCreate(Bundle savedInstanceState) {

			super.onCreate(savedInstanceState);
			PreferenceManager pm = this.getPreferenceManager();
			// Load the preferences from an XML resource
			addPreferencesFromResource(R.xml.preferences);
			Log.v("SettingsFragment", "addPrefs from Resource");
		}

		@Override
		public void onStart() {
			super.onStart();
			Log.d("SettingsFragment", "Starting()");
			PreferenceManager pm = getPreferenceManager();
			SharedPreferences sp = pm.getSharedPreferences();
			Log.d("SettingsFragment",
					"Preference name: " + pm.getSharedPreferencesName());
			sp.registerOnSharedPreferenceChangeListener(this);

			/*
			 * ONCLICK LISTENER TODO Preference cP = (Preference)
			 * findPreference("colorPicker");
			 * cP.setOnPreferenceClickListener(this);
			 */
		}

		@Override
		public void onSharedPreferenceChanged(
				SharedPreferences sharedPreferences, String key) {
//			Preferences prefs = Gdx.app.getPreferences("breakout-preferences");
//			prefs.putString(key, sharedPreferences.getString());
//			Log.d("SettingsFragment", "key changed: " + key);
//			// Log.d("SettingsFragment"," new value:   "+sharedPreferences.getString(key,"a"));
//
		}

		@Override
		public void onStop() {
			SharedPreferences sp = this.getPreferenceManager()
					.getSharedPreferences();
			sp.unregisterOnSharedPreferenceChangeListener(this);
			Log.d("SettingsFragment", "Stopping()");
			super.onStop();

		}

		@Override
		public boolean onPreferenceClick(Preference preference) {
			Log.d("SettingsFragment",
					"onPreferenceCLick()  " + preference.getKey());
			return true;
		}

	}

}
