package im.delight.android.languages;

/**
 * Copyright 2014 www.delight.im <info@delight.im>
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

import java.util.Locale;
import android.content.ContextWrapper;
import android.content.res.Resources;

/** Helper methods for setting a custom language for the process your application is running in */
public class CustomLanguage {
	
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
					newLocale = Locale.getDefault();
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
				
				// update the app's configuration to use the new Locale
				final Resources resources = context.getBaseContext().getResources();
				final android.content.res.Configuration conf = resources.getConfiguration();
				conf.locale = newLocale;
				resources.updateConfiguration(conf, resources.getDisplayMetrics());
			}
			catch (Exception e) { }
		}
	}

}
