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
    
	$RCSfile$ 
	Created on $Date$ 
*/
package net.sf.statcvs.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Encapsulates a commit, which may consist of several {@link CvsRevision}
 * objects (several files committed at once by the same author with the same
 * message).
 * 
 * @author Richard Cyganiak
 * @version $Id$
 */
public class Commit implements Comparable {
	private final Set revisions = new HashSet();
	private final CvsRevision aRevision;
	
	/**
	 * Creates a new instance which consists of the given revision.
	 * @param revision the single revision out of which the commit will be created
	 */
	public Commit(CvsRevision revision) {
		revisions.add(revision);
		aRevision = revision;
	}

	/**
	 * Adds a revision to the commit. The revision must be part of the commit.
	 * 
	 * @param revision the <code>CvsRevision</code> to add.
	 */
	public void addRevision(CvsRevision revision) {
		revisions.add(revision);
	}

	/**
	 * Returns a Set of {@link CvsRevision} objects, which make up this commit.
	 * May be used to access the individual files which were changed by the commit.
	 * 
	 * @return a set of changes
	 */
	public Set getRevisions() {
		return revisions;
	}

	/**
	 * Returns the author of the commit
	 * @return the author
	 */
	public Author getAuthor() {
		return aRevision.getAuthor();
	}

	/**
	 * Returns the comment of the commit
	 * 
	 * @return the comment
	 */
	public String getComment() {
		return aRevision.getComment();
	}
	
	/**
	 * Returns the date when the commit took place. The implementation
	 * simply returns the timestamp of the first change of the commit.
	 * 
	 * @return a date within the timeframe of the commit
	 */
	public Date getDate() {
		return aRevision.getDate();
	}

	/**
	 * Returns a <code>String</code> <code>Set</code> containing all filenames
	 * which were affected by this <code>Commit</code>.
	 * 
	 * @return a <code>Set</code> of <code>String</code>s
	 */
	public Set getAffectedFiles() {
		Set result = new HashSet();
		Iterator it = revisions.iterator();
		while (it.hasNext()) {
			CvsRevision element = (CvsRevision) it.next();
			result.add(element.getFile().getFilenameWithPath());
		}
		return result;
	}
	
	/**
	 * Returns the number of individual changes which make up this commit.
	 * 
	 * @return the number of changes
	 */
	public int getChangeCount() {
		return revisions.size();
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object other) {
		Commit otherCommit = (Commit) other;
		return getDate().compareTo(otherCommit.getDate());
	}
}
