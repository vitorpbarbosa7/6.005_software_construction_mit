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

import java.beans.beancontext.*;
import java.io.*;
import java.util.*;

/**
 * A JavaBean that encapsulates a text file. When added to a bean context,
 * this bean listens for a WordCount service to become available. When
 * the service does become available, the DocumentBean requests an 
 * instance of the service. The service then counts the number of words in the file,
 * and prints a report to standard output.
 */
public final class DocumentBean extends BeanContextChildSupport {

    private File document; 
    private BeanContextServices context;

    /**
     * Creates a new DocumentBean given the name of the file to read from.
     * @param fileName the name of the file to read from
     */
    public DocumentBean(String fileName) {
        document = new File(fileName);
    }

    /**
     * Called when this bean detects that a new service
     * has been registered with its context.
     *
     * @param bcsae the BeanContextServiceAvailableEvent
     */
    public void serviceAvailable(BeanContextServiceAvailableEvent bcsae) {
        System.out.println("[Detected a service being added to the context]");

        // Get a reference to the context
        BeanContextServices context = bcsae.getSourceAsBeanContextServices();
        System.out.println("Is the context offering a WordCount service? "
                           + context.hasService(WordCount.class)); 

        // Use the service, if it's available
        if (context.hasService(WordCount.class)) {        
            System.out.println("Attempting to use the service...");
            try {
                WordCount service = (WordCount)context.getService(this, this,
		                                           WordCount.class, document, this);
                System.out.println("Got the service!");
                service.countWords();
            } catch(Exception e) { }
        }        
    }

    /**
     * Called when this bean detects that a service 
     * has been revoked from the context.
     *
     * @param bcsre the BeanContextServiceRevokedEvent
     */
    public void serviceRevoked(BeanContextServiceRevokedEvent bcsre) {
        System.out.println("[Detected a service being revoked from the context]");
    }
}
