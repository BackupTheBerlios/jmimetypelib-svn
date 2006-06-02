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
 */
package ar.com.pabloa.jmimetypelib;

import java.io.File;

import ar.com.pabloa.jmimetypelib.testers.GlobTester;
import ar.com.pabloa.jmimetypelib.testers.MagicTester;

/**
 * @author pabloa
 * 
 */
public class JMimetypeLib {
	private GlobTester globTester;
	private MagicTester magicTester;

	public JMimetypeLib() {
		globTester = new GlobTester();
		magicTester = new MagicTester();
	}
	
	public String getMimeType( File file) {
		String mimeType = globTester.test( file);
		return mimeType;
	}
	
}
