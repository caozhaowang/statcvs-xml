/*
    StatCvs - CVS statistics generation 
    Copyright (C) 2002  Lukasz Pekacki <lukasz@pekacki.de>
    http://statcvs.sf.net/
    
    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
    
	$RCSfile: StatCvsDocument.java,v $
	$Date: 2004-02-15 18:56:13 $ 
*/
package de.berlios.statcvs.xml.output;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.jdom.Document;
import org.jdom.Element;

/**
 * Represents a document.
 *  
 * @author Steffen Pingel
 */
public class StatCvsDocument extends Document {

	private static int documentNumber = 0;
	private String filename;
	private ReportSettings settings;

	public StatCvsDocument(ReportSettings settings, String defaultFilename, String defaultTitle)
	{
		this.settings = settings;
		this.filename = settings.getString("filename", (defaultFilename == null) ? "document_" + ++documentNumber : defaultFilename);

		Element root = new Element("document");
		root.setAttribute("title", settings.getString("title", (defaultTitle == null) ? "" : defaultTitle));
		root.setAttribute("name", this.filename);
		setRootElement(root);
	}

	/**
	 * 
	 */
	public void saveResources() throws IOException
	{
		for (Iterator it = getRootElement().getChildren().iterator(); it.hasNext();) {
			Object o = it.next();
			if (o instanceof ReportElement) {
				((ReportElement)o).saveResources();
			}
		}
	}

	public String getFilename()
	{
		return filename;
	}

	public ReportSettings getSettings()
	{
		return settings;
	}

}

