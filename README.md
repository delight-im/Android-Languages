# Android-Languages

Custom language selection and access to language names for Android

## Requirements

 * Android 2.2+

## Installation

 * Add this library to your project
   * Declare the Gradle repository in your root `build.gradle`

     ```gradle
     allprojects {
         repositories {
             maven { url "https://jitpack.io" }
         }
     }
     ```

   * Declare the Gradle dependency in your app module's `build.gradle`

     ```gradle
     dependencies {
         compile 'com.github.delight-im:Android-Languages:v1.0.1'
     }
     ```

## Usage

### Custom language preference in your `PreferenceActivity`

Add the following code to your preferences definitions in XML. The class `LanguagePreference` extends `ListPreference` and has the same appearance. It ignores the attributes `android:entries`, `android:entryValues`, `android:defaultValue` and `android:summary` because it comes with its own implementation for those.

Be sure to set `android:key` to the preference key that you want to store the language code in. For `android:title`, provide a value from your string resources, which will be used as the preference's caption.

```xml
<im.delight.android.languages.LanguagePreference
    android:key="myPreferenceKey"
    android:title="@string/myPreferenceTitle"
    android:enabled="true"
    android:selectable="true" />
```

### Extend the `Application` class

In your `AndroidManifest.xml`, modify the `<application>` tag to include `android:name="com.my.package.App"`. Be sure to change `com.my.package` to your package name.

In the package that you specified there, create a new class `App` that extends `Application`:

```java
public class App extends Application { }
```

### Have your app show up in the custom language

Always make sure two include the correct preference key for the language setting instead of `myPreferenceKey`.

Modify your `Application` subclass as follows:

```java
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Language.setFromPreference(this, "myPreferenceKey");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Language.setFromPreference(this, "myPreferenceKey");
    }

}
```

In your `PreferenceFragment` or `PreferenceActivity` subclass that contains the language preference, override `onPause` as follows:

```java
@Override
protected void onPause() {
    Language.setFromPreference(this, "myPreferenceKey", true);

    super.onPause();
}
```

## Setting the default option's label

Optionally, you may set a localized label for the default option. The default is `Standard (recommended)`.

To change the label, go to your `PreferenceFragment` or `PreferenceActivity` subclass that contains the language preference. Modify `onCreate` as follows:

```java
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    LanguageList.setStandardOptionLabel(getString(R.string.default_locale));
}
```

### Access to the language names and keys

Optionally, you may retrieve a string array containing the list of all languages, when needed. Call `LanguageList.getHumanReadable` for the human-readable names and `LanguageList.getMachineReadable` for the machine-readable codes.

## Contributing

All contributions are welcome! If you wish to contribute, please create an issue first so that your feature, problem or question can be discussed.

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
