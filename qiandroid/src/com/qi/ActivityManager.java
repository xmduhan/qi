package com.qi;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;

public class ActivityManager {
	static private List<Activity> activityList = new LinkedList<Activity>();

	static public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// 遍历所有Activity并finish
	static public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
	}
}
