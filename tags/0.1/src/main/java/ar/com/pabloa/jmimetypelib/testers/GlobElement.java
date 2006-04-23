/**
 * jMimetypeLib: a java implementation of Free Desktop Shared MIME-info 
 * Database specification.
 * Copyright (C) 2006 Pablo Alcaraz - http://www.dreamsoft.com.ar.
 * Contact: pabloa@laotraesquina.com.ar
 * 
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation; either version 2.1 of the License, or any later
 * version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 *
 * Initial developer(s): Pablo Alcaraz.
 * Contributor(s): 
 *
 */
package ar.com.pabloa.jmimetypelib.testers;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import ar.com.pabloa.jmimetypelib.Utils;
import ar.com.pabloa.jmimetypelib.exceptions.ElementCreationException;

/**
 * @author pabloa
 * 
 */
public class GlobElement {
	private String pattern;

	private String mimetype;

	private boolean discard;

	private Pattern _pattern;

	public GlobElement(String globline) throws ElementCreationException {
		if (globline.startsWith("#")) {
			discard = true;
			return;
		}

		String[] a = globline.split(":");
		if (a.length < 2) {
			throw new ElementCreationException("Invalid line '" + globline
					+ "'", null);
		}
		this.mimetype = a[0];
		this.pattern = a[1];

		try {
			String p = this.pattern;
			p = Utils.replace(p, ".", "\\.");
			p = Utils.replace(p, "*", ".+");
			this._pattern = Pattern.compile(p);
		} catch (PatternSyntaxException pse) {
			throw new ElementCreationException("Pattern error '" + this.pattern
					+ "'", pse);
		}
	}

	public boolean test(File file, boolean caseinsensitive) {
		String s = file.getName();
		if (caseinsensitive) {
			s = s.toLowerCase();
		}
		Matcher m = this._pattern.matcher(s);
		return m.matches();
	}

	public String getMimetype() {
		return mimetype;
	}

	public String getPattern() {
		return pattern;
	}

	boolean isDiscard() {
		return discard;
	}

}
