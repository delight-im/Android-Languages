package im.delight.android.languages;

/*
 * Copyright (c) delight.im <info@delight.im>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.Locale;
import android.content.ContextWrapper;
import android.content.res.Resources;

/** Helper methods for setting a custom language for the process your application is running in */
public class CustomLanguage {
	
	protected static Locale mOriginalLocale;
	
	static {
		// save the original default locale so that we can reference it later
		mOriginalLocale = Locale.getDefault();
	}

	/**
	 * Initializes the application with the custom language that is defined in the given preference
	 * 
	 * @param context the Application instance that you call this from
	 * @param langPrefName the name of the string preference that contains the desired language's key
	 */
	public static void init(final ContextWrapper context, final String langPrefName) {
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		init(context, langPrefName, prefs);
	}

	/**
	 * Initializes the application with the custom language that is defined in the given preference
	 * 
	 * @param context the Application instance that you call this from
	 * @param langPrefName the name of the string preference that contains the desired language's key
	 * @param prefs a SharedPreferences instance that should be re-used 
	 */
	public static void init(final ContextWrapper context, final String langPrefName, SharedPreferences prefs) {
		CustomLanguage.setLanguage(context, prefs.getString(langPrefName, ""));
	}

	/**
	 * Updates the Locale for the current process (application) to the given language code
	 * 
	 * @param context the ContextWrapper instance to get the Resources from
	 * @param languageCode the language code in the form of <xx> (e.g. <es>) or <xx-(r)XX> (e.g. <pt-rBR>)
	 */
	public static void setLanguage(final ContextWrapper context, final String languageCode) {
		setLanguage(context, languageCode, false);
	}
	
	/**
	 * Updates the Locale for the current process (application) to the given language code
	 * 
	 * @param context the ContextWrapper instance to get the Resources from
	 * @param languageCode the language code in the form of <xx> (e.g. <es>) or <xx-(r)XX> (e.g. <pt-rBR>)
	 * @param forceUpdate whether to force an update when the default language (empty language code) is requested
	 */
	public static void setLanguage(final ContextWrapper context, final String languageCode, final boolean forceUpdate) {
		// if a custom language is requested (non-empty language code) or a forced update is requested
		if (!languageCode.equals("") || forceUpdate) {
			try {
				// create a new Locale instance
				final Locale newLocale;
				
				// if the default language is requested (empty language code)
				if (languageCode.equals("")) {
					// set the new Locale instance to the default language
					newLocale = mOriginalLocale;
				}
				// if a custom language is requested (non-empty language code)
				else {
					// if the language code does also contain a region
					if (languageCode.contains("-r") || languageCode.contains("-")) {
						// split the language code into language and region
						final String[] language_region = languageCode.split("\\-(r)?");
						// construct a new Locale object with the specified language and region
						newLocale = new Locale(language_region[0], language_region[1]);
					}
					// if the language code does not contain a region
					else {
						// simply construct a new Locale object from the given language code
						newLocale = new Locale(languageCode);
					}
				}
				
				if (newLocale != null) {
					// update the app's configuration to use the new Locale
					final Resources resources = context.getBaseContext().getResources();
					final android.content.res.Configuration conf = resources.getConfiguration();
					conf.locale = newLocale;
					resources.updateConfiguration(conf, resources.getDisplayMetrics());
					
					// overwrite the default Locale
					Locale.setDefault(newLocale);
				}
			}
			catch (Exception e) { }
		}
	}
	
	/**
	 * Returns the original Locale instance that was in use before any custom selection may have been applied
	 * 
	 * @return the original Locale instance
	 */
	public static Locale getOriginalLocale() {
		return mOriginalLocale;
	}

}
