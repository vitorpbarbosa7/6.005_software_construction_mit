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
 * Test program that adds 100 beans to a context,
 * and calls size() to report the number of beans
 * currently nested. Finally,
 * this test calls toArray() to get references to
 * all child beans. 
 */
public class Example2 {
    public static void main(String[] args) {

        // A BeanContext 
        BeanContextSupport context = new BeanContextSupport(); 

        // Many JavaBeans
        BeanContextChildSupport[] beans = new BeanContextChildSupport[100];

        System.out.println("Number of children in the context: " + context.size());

        // Create the beans and add them to the context
        for (int i = 0; i < beans.length; i++) {
            beans[i] = new BeanContextSupport();
            context.add(beans[i]);
        }
        System.out.println("Number of children in the context: " + context.size());

        // Context now has 100 beans in it, get references to them all
        Object[] children = context.toArray();
        System.out.println("Number of objects retrieved from the context: " + children.length);
    }
}
