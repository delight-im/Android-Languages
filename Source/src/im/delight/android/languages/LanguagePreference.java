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

import android.content.Context;
import android.os.Build;
import android.preference.ListPreference;
import android.util.AttributeSet;

/** Preference that extends ListPreference and can be used in XML (or Java) to offer a custom language selection */
public class LanguagePreference extends ListPreference {

	public LanguagePreference(Context context) {
		super(context);
		init();
	}

	public LanguagePreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	protected void init() {
		// use the list of human-readable language names for the displayed list
		setEntries(LanguageList.getHumanReadable());
		// use the list of machine-readable language names for the saved values
		setEntryValues(LanguageList.getMachineReadable());
		// use an empty language code (no custom language) as the default
		setDefaultValue("");
		// set the summary to be auto-updated to the selected value
		setSummary("%s");
	}

	@Override
	public void setValue(String value) {
		// if the API level is 19 or above
		if (Build.VERSION.SDK_INT >= 19) {
			// we can just call the default implementation
			super.setValue(value);
		}
		// if the API level is below 19
		else {
			// get the old value first
			String oldValue = getValue();
			// call the default implementation
			super.setValue(value);
			// if the new and old value differ
			if (!value.equals(oldValue)) {
				// notify the super class of the change
				notifyChanged();
			}
		}
	}

}
