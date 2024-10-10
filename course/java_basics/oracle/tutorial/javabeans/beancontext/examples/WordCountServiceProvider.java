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
import java.util.*;
import java.io.*;

/**
 * This class is the factory that delivers a word counting service.
 * The 3 methods defined in this class are the concrete implementations
 * of the BeanContextServiceProvider interface. For this demonstration, the primary
 * method of interest is getService(). The getService() methods returns a new
 * WordCount instance. It is called by the bean context when a nested
 * JavaBean requests the service.
 */
public final class WordCountServiceProvider implements BeanContextServiceProvider {

    public Object getService(BeanContextServices bcs, 
                             Object requestor,
                             Class serviceClass,
                             Object serviceSelector) {

        // For this demo, we know that the cast from serviceSelector
        // to File will always work.
        final File document = (File)serviceSelector;

        /*  Return an instance of the service. The service itself is 
         *  the WordCount interface, which is implemented here using 
         *  an anonymous inner class.
         */
        return new WordCount() {
            public void countWords() {
                try {
                    // Create a Reader to the DocumentBean's File
                    BufferedReader br = new BufferedReader(new FileReader(document));
                    String line = null;
                    int wordCount = 0;
                    while ((line = br.readLine()) != null) {
                        StringTokenizer st = new StringTokenizer(line);
                        while (st.hasMoreTokens()) {
                            System.out.println("Word " + (++wordCount)
                                               + " is: " + st.nextToken());
                        }
                    }
                    System.out.println("Total number of words in the document: "
                                       + wordCount);
                    System.out.println("[WordCount service brought to you by WordCountServiceProvider]");                
                    br.close();
                 } catch(Exception e) { }
            }
        };
    }

    public void releaseService(BeanContextServices bcs,
                               Object requestor,
                               Object service) {
        // do nothing
    }

    public Iterator getCurrentServiceSelectors(BeanContextServices bcs, Class serviceClass) {
        return null; // do nothing
    }
}
