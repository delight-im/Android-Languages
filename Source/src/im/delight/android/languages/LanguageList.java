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

import java.io.UnsupportedEncodingException;
import android.util.Base64;

public class LanguageList {

	private static final String CHARSET_DEFAULT = "UTF-8";
	private static final String DEFAULT_LANGUAGE_LABEL_DEFAULT = "Standard (recommended)";
	private static String sDefaultLanguageLabel = DEFAULT_LANGUAGE_LABEL_DEFAULT;
	private static final String[] HUMAN_RAW = {
		null,
		"QWZyaWthYW5zIChBZnJpa2FhbnMp",
		"QWxiYW5pYW4gKFNocWlwKQ==",
		"QW1oYXJpYyAo4Yqg4Yib4Yit4YqbKQ==",
		"QXJhYmljICjYp9mE2LnYsdio2YrYqSk=",
		"QXJhZ29uZXNlIChBcmFnb27DqXMp",
		"QXJtZW5pYW4gKNWA1aHVtdWl1oDVpdW2KQ==",
		"QXplcmJhaWphbmkgKEF6yZlyYmF5Y2FuKQ==",
		"QmFzaGtpciAo0JHQsNGI0qHQvtGA0YLRgdCwKQ==",
		"QmFzcXVlIChFdXNrYXJhKQ==",
		"QmVsYXJ1c2lhbiAo0LHQtdC70LDRgNGD0YHQutCw0Y8g0LzQvtCy0LAp",
		"QmVuZ2FsaSAo4Kas4Ka+4KaC4Kay4Ka+KQ==",
		"Qm9zbmlhbiAoQm9zYW5za2kp",
		"QnJldG9uIChCcmV6aG9uZWcp",
		"QnVsZ2FyaWFuICjQsdGK0LvQs9Cw0YDRgdC60Lgp",
		"Q2F0YWxhbiAoQ2F0YWzDoCk=",
		"Q2hpbmVzZSAoU2ltcGxpZmllZCkgKOS4reaWhyk=",
		"Q2hpbmVzZSAoVHJhZGl0aW9uYWwpICjkuK3mlocp",
		"Q2h1dmFzaCAo0KfTkdCy0LDRiNC70LAp",
		"Q3JvYXRpYW4gKEhydmF0c2tpKQ==",
		"Q3plY2ggKMSMZXNreSk=",
		"RGFuaXNoIChEYW5zayk=",
		"RHV0Y2ggKE5lZGVybGFuZHMp",
		"RW5nbGlzaCAoRW5nbGlzaCk=",
		"RXN0b25pYW4gKEVlc3RpKQ==",
		"RmlubmlzaCAoU3VvbWkp",
		"RnJlbmNoIChGcmFuw6dhaXMp",
		"R2FsaWNpYW4gKEdhbGVnbyk=",
		"R2VvcmdpYW4gKOGDpeGDkOGDoOGDl+GDo+GDmuGDmCk=",
		"R2VybWFuIChEZXV0c2NoKQ==",
		"R3JlZWsgKM61zrvOu863zr3Ouc66zqwp",
		"R3VqYXJhdGkgKOCql+CrgeCqnOCqsOCqvuCqpOCrgCk=",
		"SGFpdGlhbiAoS3JlecOybCBBeWlzeWVuKQ==",
		"SGF1c2EgKEhhdXNhKQ==",
		"SGVicmV3ICjXoteR16jXmdeqKQ==",
		"SGluZGkgKOCkueCkv+CkqOCljeCkpuClgCk=",
		"SHVuZ2FyaWFuIChNYWd5YXIp",
		"SWNlbGFuZGljICjDjXNsZW5za2Ep",
		"SWdibyAoQXPhu6Vz4bulIElnYm8p",
		"SW5kb25lc2lhbiAoQmFoYXNhIEluZG9uZXNpYSk=",
		"SXJpc2ggKEdhZWlsZ2Up",
		"SXRhbGlhbiAoSXRhbGlhbm8p",
		"SmFwYW5lc2UgKOaXpeacrOiqnik=",
		"SmF2YW5lc2UgKEJhc2EgSmF3YSk=",
		"S2FubmFkYSAo4LKV4LKo4LON4LKo4LKhKQ==",
		"S2F6YWtoICjSmtCw0LfQsNKbINGC0ZbQu9GWKQ==",
		"S2htZXIgKOGel+GetuGen+GetuGegeGfkuGemOGfguGemik=",
		"S2lyZ2hpeiAo0JrRi9GA0LPRi9C30YfQsCk=",
		"S29yZWFuICjtlZzqta3slrQp",
		"S3VyZGlzaCAoS3VyZMOuKQ==",
		"TGFvICjgup7gurLguqrgurLguqXgurLguqcp",
		"TGF0dmlhbiAoTGF0dmllxaF1KQ==",
		"TGl0aHVhbmlhbiAoTGlldHV2acWzKQ==",
		"THV4ZW1ib3VyZ2lzaCAoTMOrdHplYnVlcmdlc2NoKQ==",
		"TWFjZWRvbmlhbiAo0JzQsNC60LXQtNC+0L3RgdC60Lgp",
		"TWFsYWdhc3kgKE1hbGFnYXN5KQ==",
		"TWFsYXkgKEJhaGFzYSBNZWxheXUp",
		"TWFsYXlhbGFtICjgtK7gtLLgtK/gtL7gtLPgtIIp",
		"TWFsdGVzZSAoTWFsdGkp",
		"TWFvcmkgKE3EgW9yaSk=",
		"TWFyYXRoaSAo4KSu4KSw4KS+4KSg4KWAKQ==",
		"TmVwYWxpICjgpKjgpYfgpKrgpL7gpLLgpYAp",
		"Tm9yd2VnaWFuIEJva23DpWwgKE5vcnNrIGJva23DpWwp",
		"Tm9yd2VnaWFuIE55bm9yc2sgKE5vcnNrIG55bm9yc2sp",
		"T2NjaXRhbiAoT2NjaXRhbik=",
		"UGVyc2lhbiAo2YHYp9ix2LPbjCk=",
		"UG9saXNoIChQb2xza2kp",
		"UG9ydHVndWVzZSAoQnJhemlsKSAoUG9ydHVndcOqcyk=",
		"UG9ydHVndWVzZSAoUG9ydHVnYWwpIChQb3J0dWd1w6pzKQ==",
		"UHVuamFiaSAo4Kiq4Kmw4Kic4Ki+4Kis4KmAKQ==",
		"Um9tYW5pYW4gKFJvbcOibsSDKQ==",
		"UnVzc2lhbiAo0KDRg9GB0YHQutC40Lkp",
		"U2VyYmlhbiAo0KHRgNC/0YHQutC4KQ==",
		"U2xvdmFrIChTbG92ZW7EjWluYSk=",
		"U2xvdmVuZSAoU2xvdmVuxaHEjWluYSk=",
		"U29tYWxpIChBZi1Tb29tYWFsaSk=",
		"U3BhbmlzaCAoRXNwYcOxb2wp",
		"U3VuZGFuZXNlIChCYXNhIFN1bmRhKQ==",
		"U3dhaGlsaSAoS2lzd2FoaWxpKQ==",
		"U3dlZGlzaCAoU3ZlbnNrYSk=",
		"VGFnYWxvZyAoVGFnYWxvZyk=",
		"VGFqaWsgKNCi0L7St9C40LrToyk=",
		"VGFtaWwgKOCupOCuruCuv+CutOCvjSk=",
		"VGF0YXIgKNCi0LDRgtCw0YDRh9CwKQ==",
		"VGVsdWd1ICjgsKTgsYbgsLLgsYHgsJfgsYEp",
		"VGhhaSAo4LmE4LiX4LiiKQ==",
		"VHVya2lzaCAoVMO8cmvDp2Up",
		"VWtyYWluaWFuICjQo9C60YDQsNGX0L3RgdGM0LrQsCk=",
		"VXJkdSAoVXJkxasp",
		"VXpiZWsgKE/Ku3piZWtjaGEp",
		"VmlldG5hbWVzZSAoVGnhur9uZyBWaeG7h3Qp",
		"V2FsbG9vbiAoV2Fsb24p",
		"V2Vsc2ggKEN5bXJhZWcp",
		"V2VzdGVybiBGcmlzaWFuIChGcnlzayk=",
		"WWlkZGlzaCAo15nXmda015PXmdepKQ==",
		"WW9ydWJhIChZb3LDuWLDoSk=",
		"WnVsdSAoaXNpWnVsdSk="
	};
	private static final String[] MACHINE = {
		"",
		"af",
		"sq",
		"am",
		"ar",
		"an",
		"hy",
		"az",
		"ba",
		"eu",
		"be",
		"bn",
		"bs",
		"br",
		"bg",
		"ca",
		"zh-rCN",
		"zh-rTW",
		"cv",
		"hr",
		"cs",
		"da",
		"nl",
		"en",
		"et",
		"fi",
		"fr",
		"gl",
		"ka",
		"de",
		"el",
		"gu",
		"ht",
		"ha",
		"iw",
		"hi",
		"hu",
		"is",
		"ig",
		"id",
		"ga",
		"it",
		"ja",
		"jv",
		"kn",
		"kk",
		"km",
		"ky",
		"ko",
		"ku",
		"lo",
		"lv",
		"lt",
		"lb",
		"mk",
		"mg",
		"ms",
		"ml",
		"mt",
		"mi",
		"mr",
		"ne",
		"nb",
		"nn",
		"oc",
		"fa",
		"pl",
		"pt-rBR",
		"pt-rPT",
		"pa",
		"ro",
		"ru",
		"sr",
		"sk",
		"sl",
		"so",
		"es",
		"su",
		"sw",
		"sv",
		"tl",
		"tg",
		"ta",
		"tt",
		"te",
		"th",
		"tr",
		"uk",
		"ur",
		"uz",
		"vi",
		"wa",
		"cy",
		"fy",
		"yi",
		"yo",
		"zu"
	};
	private static String[] mHuman;

	public static String[] getHumanReadable() {
		if (mHuman == null) {
			mHuman = new String[HUMAN_RAW.length];

			mHuman[0] = getDefaultLanguageLabel();

			for (int i = 1; i < mHuman.length; i++) {
				try {
					mHuman[i] = decodeBase64(HUMAN_RAW[i]);
				}
				catch (Exception e) {
					mHuman[i] = MACHINE[i];
				}
			}
		}

		return mHuman;
	}

	public static String[] getMachineReadable() {
		return MACHINE;
	}

	public static String getDefaultLanguageLabel() {
		return sDefaultLanguageLabel;
	}

	public static void setDefaultLanguageLabel(final String label) {
		sDefaultLanguageLabel = label;
	}

	private static String decodeBase64(final String base64) throws IllegalArgumentException, UnsupportedEncodingException {
		final byte[] bytes = Base64.decode(base64, Base64.DEFAULT);

		return new String(bytes, CHARSET_DEFAULT);
	}

}
