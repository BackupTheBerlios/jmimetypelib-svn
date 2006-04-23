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

import junit.framework.TestCase;

public class JMimetypeLibTest extends TestCase {

	/*
	 * Test method for 'ar.com.pabloa.jmimetypelib.JMimetypeLib.getMimeType(File)'
	 */
	public void testGetMimeType() {
		JMimetypeLib mimetypeLib = new JMimetypeLib();
		assertEquals( "application/octet-stream", mimetypeLib.getMimeType( new File( "/usr/lib/perl5/vendor_perl/5.8.6/i386-linux/Unicode/Map8/maps/cp850.bin")));
		assertEquals( "video/x-ms-asf", mimetypeLib.getMimeType( new File( "videotest.wmv")));
		assertEquals( "video/x-ms-asf", mimetypeLib.getMimeType( new File( "videotest.WMV")));
		assertEquals( "video/x-ms-asf", mimetypeLib.getMimeType( new File( "videotest.WmV")));
		assertEquals( "application/octet-stream", mimetypeLib.getMimeType( new File( "videotest.WMVsdsd")));
//		assertEquals( "text/x-c++src", mimetypeLib.getMimeType( new File( "cpluplutest.c++")));
//		assertEquals( "text/x-csrc", mimetypeLib.getMimeType( new File( "ctest.c")));
//		assertEquals( "text/x-readme", mimetypeLib.getMimeType( new File( "README")));
		assertEquals( "application/x-compressed-tar", mimetypeLib.getMimeType( new File( "targztest.tar.gz")));
		assertEquals( "application/x-gzip", mimetypeLib.getMimeType( new File( "gztest.gz")));

	}

}
