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

import junit.framework.TestCase;

public class UtilsTest extends TestCase {

	/*
	 * Test method for 'ar.com.pabloa.jmimetypelib.Utils.replace(String, String, String)'
	 */
	public final void testReplace() {
		String s = "*.jpg";
		s = Utils.replace( s, ".", "\\.");
		s = Utils.replace( s, "*", ".+");
		assertEquals( ".+\\.jpg", s);
	}

}
