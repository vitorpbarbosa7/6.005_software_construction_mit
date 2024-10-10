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

/**
 * A test program that adds a bean to a beancontext, and
 * reports on various aspects of the context's membership state. 
 * This program also shows that a bean's getBeanContext() method 
 * can be called to get a reference to its enclosing context.
 */
public class Example1 {
    private static BeanContextSupport context = new BeanContextSupport(); // The BeanContext
    private static BeanContextChildSupport bean = new BeanContextChildSupport(); // The JavaBean
  
    public static void main(String[] args) {
        report();  

        // Add the bean to the context
        System.out.println("Adding bean to context...");
        context.add(bean);

        report();
    }

    private static void report() {
        // Print out a report of the context's membership state.
        System.out.println("=============================================");

        // Is the context empty?
        System.out.println("Is the context empty? " + context.isEmpty());

        // Has the context been set for the child bean?
        boolean result = (bean.getBeanContext()!=null);
        System.out.println("Does the bean have a context yet? " + result);

        // Number of children in the context
        System.out.println("Number of children in the context: " + context.size());

        // Is the specific bean a member of the context?
        System.out.println("Is the bean a member of the context? " + context.contains(bean));

        // Equality test
        if (bean.getBeanContext() != null) {
            boolean isEqual = (bean.getBeanContext()==context); // true means both references point to the same object
            System.out.println("Contexts are the same? " + isEqual);
        }
        System.out.println("=============================================");   
    }
}
