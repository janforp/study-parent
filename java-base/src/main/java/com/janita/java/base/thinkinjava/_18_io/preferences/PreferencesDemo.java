package com.janita.java.base.thinkinjava._18_io.preferences;

import java.util.prefs.Preferences;

import static com.janita.java.base.thinkinjava.util.Print.print;

/**
 * PreferencesDemo
 *
 * @author zhucj
 * @since 20200528
 */

public class PreferencesDemo {

    public static void main(String[] args) throws Exception {
        Preferences prefs = Preferences.userNodeForPackage(PreferencesDemo.class);
        prefs.put("Location", "Oz");
        prefs.put("Footwear", "Ruby Slippers");
        prefs.putInt("Companions", 4);
        prefs.putBoolean("Are there witches?", true);
        int usageCount = prefs.getInt("UsageCount", 0);
        usageCount++;
        prefs.putInt("UsageCount", usageCount);
        for (String key : prefs.keys()) {
            print(key + ": " + prefs.get(key, null));
        }
        // You must always provide a default value:
        print("How many companions does Dorothy have? " + prefs.getInt("Companions", 0));
    }
} /* Output: (Sample)
Location: Oz
Footwear: Ruby Slippers
Companions: 4
Are there witches?: true
UsageCount: 53
How many companions does Dorothy have? 4
*///:~

class AfterDemo {

    public static void main(String[] args) {
        Preferences prefs = Preferences.userNodeForPackage(PreferencesDemo.class);
        System.out.println(prefs.getBoolean("Are there witches?", false));
        System.out.println(prefs.get("Location", null));
    }
}
