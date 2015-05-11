package com.example.jinux.thirtydays.common;

import android.preference.PreferenceManager;

import com.example.jinux.thirtydays.MyApp;

/**
 * sp文件工具类
 * @author JD
 *
 */
public class SharedPreferenceUtil {

	public static boolean getPreferenceBoolean(String key) {
		return getPreferenceBoolean(key, false);
	}

	public static boolean getPreferenceBoolean(String key, boolean defValue) {
		return PreferenceManager.getDefaultSharedPreferences(MyApp.mApplication)
				.getBoolean(key, defValue);
	}

	public static String getPreferenceString(String key) {
		return getPreferenceString(key, "");
	}

	public static String getPreferenceString(String key, String defValue) {
		return PreferenceManager.getDefaultSharedPreferences(MyApp.mApplication)
				.getString(key, defValue);
	}

	public static int getPreferenceInt(String key) {
		return getPreferenceInt(key, 0);
	}

	public static int getPreferenceInt(String key, int defValue) {
		return PreferenceManager.getDefaultSharedPreferences(MyApp.mApplication).getInt(
				key, defValue);
	}

	public static long getPreferenceLong(String key) {
		return getPreferenceLong(key, 0);
	}

	public static long getPreferenceLong(String key, long defValue) {
		return PreferenceManager.getDefaultSharedPreferences(MyApp.mApplication).getLong(
				key, defValue);
	}

	public static synchronized void putPreferenceString(String key, String value) {
		PreferenceManager.getDefaultSharedPreferences(MyApp.mApplication).edit()
				.putString(key, value).commit();
	}

	public static synchronized void putPreferenceBoolean(String key,
			Boolean value) {
		PreferenceManager.getDefaultSharedPreferences(MyApp.mApplication).edit()
				.putBoolean(key, value).commit();
	}

	public static synchronized void putPreferenceInt(String key, int value) {
		PreferenceManager.getDefaultSharedPreferences(MyApp.mApplication).edit()
				.putInt(key, value).commit();
	}

	public static void putPreferenceLong(String key, long value) {
		PreferenceManager.getDefaultSharedPreferences(MyApp.mApplication).edit()
				.putLong(key, value).commit();
	}

	public static boolean preferenceContains(String key) {
		return PreferenceManager.getDefaultSharedPreferences(MyApp.mApplication)
				.contains(key);
	}
}
