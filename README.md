# Android-Languages

Custom language selection and access to language names for Android

## Installation

 * Include one of the [JARs](JARs) in your `libs` folder
 * or
 * Copy the Java package to your project's source folder
 * or
 * Create a new library project from this repository and reference it in your project

## Usage

### Custom language preference in your `PreferenceActivity`

Add the following code to your XML for the preferences. The class `LanguagePreference` extends `ListPreference` and has the same appearance. It ignores the attributes `android:entries`, `android:entryValues`, `android:defaultValue` and `android:summary` because it comes with its own implementation for those.

Be sure to set `android:key` to the preference key that you want to store the language code in. For `android:title`, provide a value from your string resources, for example, which will be used as the preference's caption.

```
<im.delight.android.languages.LanguagePreference
	android:key="language"
	android:title="..."
	android:enabled="true"
	android:selectable="true" />
```

### Have your app show up in the custom language

In your `AndroidManifest.xml`, modify the `<application>` tag to include `android:name="com.my.package.App"`. Be sure to change `com.my.package` to your package name.

In the package that you specified there, create a new class `App` with the following content. Make sure to use the correct preference key in `prefs.getString(...)`, i.e. the one you also used in the preference XML above.

```
public class App extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		// ...
		CustomLanguage.init(this, "language");
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// ...
		CustomLanguage.init(this, "language");
	}

}
```

In your `PreferenceActivity`, override `onPause()` like this (use the correct preference key again):

```
@Override
protected void onPause() {
	super.onPause();
	
	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
	CustomLanguage.setLanguage(this, prefs.getString("language", ""), true);
}
```

### Access to the language names and keys

While you don't need this, usually, you may retrieve a string array containing the list of languages. Use `LanguageList.getHumanReadable(...)` for the human-readable names and `LanguageList.getMachineReadable(...)` for the machine-readable codes.

## License

```
Copyright (c) delight.im <info@delight.im>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```