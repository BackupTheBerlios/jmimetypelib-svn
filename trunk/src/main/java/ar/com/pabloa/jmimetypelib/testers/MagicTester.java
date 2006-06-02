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
package ar.com.pabloa.jmimetypelib.testers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ar.com.pabloa.jmimetypelib.exceptions.ElementCreationException;

/**
 * @author pabloa
 *
 */
public class MagicTester {
	
	private final String MIME_Magic = "MIME-Magic";
	
	private List magic;

	public MagicTester() {
		try {
			List magicPaths = getPaths( "mime/magic");
			this.magic = loadMagicFiles( magicPaths);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String test(File file) {
		String mimetype = "application/octet-stream";
		boolean findit = false;
		for (Iterator iter = this.magic.iterator(); iter.hasNext();) {
			MagicElement magicElement = (MagicElement) iter.next();
			findit = magicElement.test( file, false);
			if( findit) {
				mimetype = magicElement.getMimetype();
				break;
			}
		}
		
		if( !findit) {
			for (Iterator iter = this.magic.iterator(); iter.hasNext();) {
				MagicElement magicElement = (MagicElement) iter.next();
				findit = magicElement.test( file, true);
				if( findit) {
					mimetype = magicElement.getMimetype();
					break;
				}
			}
		}
		return mimetype;
	}
	
	private List getPaths( String path) throws IOException {
		String xdg_data_home = System.getenv( "XDG_DATA_HOME");
		String xdg_data_dirs = System.getenv( "XDG_DATA_DIRS");
		
		List ret = new LinkedList();
		// URL u = getClass().getResource( "/META-INF/globs.txt");
		// InputStream in = getClass().getResourceAsStream( "/META-INF/globs.txt");
		// System.out.println( "* * * * * * * *");
		// System.out.println( "u: " + u);
		// System.out.println( "in: " + in);
		// if( u != null) {
		//	ret.add( u.getFile());
		// }
		// System.out.println( "* * * * * * * *");
		// Put a signal to read the globs inside the jar.
		ret.add( "!");

		String[] paths = (xdg_data_dirs + ":" + xdg_data_home).split( ":");
		for (int i = 0; i < paths.length; i++) {
			String s = paths[i];
			if( s.length() > 0) {
				File f = new File( s + File.separator + path);
				if( f.exists()) {
					ret.add( f.getCanonicalPath());
				}
				
			}
		}

		return ret;
	}

	// private Map loadGlobFiles(List globPaths) throws IOException {
	private List loadMagicFiles(List globPaths) throws IOException {
		List ret = new LinkedList();
		File fin;
		// URL u = getClass().getResource( "/META-INF/globs");

		for (Iterator iter = globPaths.iterator(); iter.hasNext();) {
			String path = (String) iter.next();
			BufferedReader in;
			if( path.indexOf( "!") == -1) {
				if( false) {
					System.out.print( "reading " + path + "...   ");
				}
				fin = new File( path);
				in = new BufferedReader( new FileReader( fin));
				if( false) {
					System.out.println( "ok");
				}
			} else {
				// System.out.println( "Voy por JarInputStream");
				// System.out.println( "----> " + fin.getPath());
				// in = new BufferedReader( new InputStreamReader( new JarInputStream( new URL( "jar:" + fin.getPath()).openStream())));
				// fin = new File( /*"jar:" +*/ path);
				// in = new BufferedReader( new InputStreamReader( new JarInputStream( new URL( fin.getPath()).openStream())));
				if( false) {
					System.out.print( "reading default magic ...   ");
				}
				try {
					in = new BufferedReader( new InputStreamReader( getClass().getResourceAsStream( "/META-INF/magic")));
					if( false) System.out.println( "ok");
				} catch( NullPointerException npe) {
					if( false) System.out.println( "null error");
					continue;
				} catch( Exception e) {
					if( false) System.out.println( "error");
					continue;
				}

			}
			String line;
			boolean isMime_Magic = false;
			if( ( line = in.readLine()) != null) {
				isMime_Magic = line.startsWith( MIME_Magic);
			}
			if( isMime_Magic) {
		        while (( line = in.readLine()) != null) {
		        	try {
						MagicElement magicElement = new MagicElement( line);
						if( !magicElement.isDiscard()) {
							ret.add( magicElement);
						}
					} catch (ElementCreationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
			}
	        in.close();
		}
		return ret;
	}

}
