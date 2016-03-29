package im.delight.android.example.languages;

import im.delight.android.languages.LanguageList;
import android.os.Bundle;
import android.app.Activity;
import java.util.Arrays;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		System.out.println("LanguageList.getHumanReadable() = "+Arrays.toString(LanguageList.getHumanReadable()));
		System.out.println("LanguageList.getMachineReadable() = "+Arrays.toString(LanguageList.getMachineReadable()));
	}

}
