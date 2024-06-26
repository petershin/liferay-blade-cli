/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blade.cli.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Gregory Amerson
 */
public class StringUtil {

	public static boolean contains(String s1, String s2) {
		if ((s1 == null) || (s2 == null)) {
			return false;
		}

		return s1.contains(s2);
	}

	public static boolean equals(String s1, Object o) {
		if ((s1 == null) || (o == null)) {
			return false;
		}

		return s1.equals(o.toString());
	}

	public static boolean isNullOrEmpty(String arg) {
		boolean hasContent = Optional.ofNullable(
			arg
		).map(
			String::trim
		).filter(
			s -> s.length() != 0
		).isPresent();

		if (hasContent) {
			return false;
		}

		return true;
	}

	public static String replace(String s, char oldSub, char newSub) {
		if (s == null) {
			return null;
		}

		return s.replace(oldSub, newSub);
	}

	public static String[] split(String s, char delimiter) {
		if (s == null) {
			return _emptyStringArray;
		}

		s = s.trim();

		if (s.length() == 0) {
			return _emptyStringArray;
		}

		List<String> nodeValues = new ArrayList<>();

		_split(nodeValues, s, 0, delimiter);

		return nodeValues.toArray(new String[0]);
	}

	public static String[] split(String s, String delimiter) {
		if ((s == null) || (delimiter == null) || delimiter.equals(StringPool.BLANK)) {
			return _emptyStringArray;
		}

		s = s.trim();

		if (s.equals(delimiter)) {
			return _emptyStringArray;
		}

		if (delimiter.length() == 1) {
			return split(s, delimiter.charAt(0));
		}

		List<String> nodeValues = new ArrayList<>();

		int offset = 0;

		int pos = s.indexOf(delimiter, offset);

		while (pos != -1) {
			nodeValues.add(s.substring(offset, pos));

			offset = pos + delimiter.length();

			pos = s.indexOf(delimiter, offset);
		}

		if (offset < s.length()) {
			nodeValues.add(s.substring(offset));
		}

		return nodeValues.toArray(new String[0]);
	}

	private static void _split(Collection<String> values, String s, int offset, char delimiter) {
		int pos = s.indexOf(delimiter, offset);

		while (pos != -1) {
			values.add(s.substring(offset, pos));

			offset = pos + 1;

			pos = s.indexOf(delimiter, offset);
		}

		if (offset < s.length()) {
			values.add(s.substring(offset));
		}
	}

	private static String[] _emptyStringArray = new String[0];

}