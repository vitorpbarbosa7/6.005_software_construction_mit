/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 


/**
 * This is a demonstration JDBC applet.
 * It displays some simple standard output from the Coffee database.
 */

import java.applet.Applet;
import java.awt.Graphics;
import java.util.Vector;
import java.sql.*;

public class OutputApplet extends Applet implements Runnable {
    private Thread worker;
    private Vector queryResults;
    private String message = "Initializing";

    public synchronized void start() {
	// Every time "start" is called we create a worker thread to
	// re-evaluate the database query.
	if (worker == null) {	
	    message = "Connecting to database";
            worker = new Thread(this);
	    worker.start();
	}
    }

    /**
     * The "run" method is called from the worker thread.  Notice that
     * because this method is doing potentially slow databases accesses
     * we avoid making it a synchronized method.
     */

    public void run() {
	String url = "jdbc:mySubprotocol:myDataSource";
	String query = "select COF_NAME, PRICE from COFFEES";
	
	try {
	    Class.forName("myDriver.ClassName");
	} catch(Exception ex) {
	    setError("Can't find Database driver class: " + ex);
	    return;
	}

	try {
	    Vector results = new Vector();
	    Connection con = DriverManager.getConnection(url,
						"myLogin", "myPassword");
	    Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    while (rs.next()) {
		String s = rs.getString("COF_NAME");
		float f = rs.getFloat("PRICE");
		String text = s + "     " + f;
		results.addElement(text);
	    }

	    stmt.close();
	    con.close();

	    setResults(results);

	} catch(SQLException ex) {
	    setError("SQLException: " + ex);
	}
    }

    /**
     * The "paint" method is called by AWT when it wants us to
     * display our current state on the screen.
     */

    public synchronized void paint(Graphics g) {
	// If there are no results available, display the current message.
	if (queryResults == null) {
	    g.drawString(message, 5, 50);
	    return;
	}

	// Display the results.
	g.drawString("Prices of coffee per pound:  ", 5, 10);
	int y = 30;
	java.util.Enumeration enum = queryResults.elements();
	while (enum.hasMoreElements()) {
	    String text = (String)enum.nextElement();
	    g.drawString(text, 5, y);
	    y = y + 15;
	}
    }

    /**
     * This private method is used to record an error message for
     * later display.
     */

    private synchronized void setError(String mess) {
	queryResults = null;	
	message = mess;	
	worker = null;
	// And ask AWT to repaint this applet.
	repaint();
    }

    /**
     * This private method is used to record the results of a query, for
     * later display.
     */

    private synchronized void setResults(Vector results) {
	queryResults = results;
	worker = null;
	// And ask AWT to repaint this applet.
	repaint();
    }
}
