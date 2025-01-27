/* Copyright (c) 2016-2017 MIT 6.005/6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package lib6005.parser;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This data structure represents a parse tree in a way that allows for easy traversal through all its 
 * different nodes. You will generally want to translate this data-structure right away into a
 * data-structure that is tailored to your application.
 * 
 * 
 * @author asolar
 *
 * @param <Symbols> Is an Enum type of all the non-terminals in the parse tree.
 */
public class ParseTree<Symbols extends Enum<Symbols>> implements Iterable<ParseTree<Symbols>>{
    private final Symbols UNASSIGNED = null;
    
    /**
     * During the process of building the ParseTree, the name will sometimes be UNASSIGNED, 
     * but once the ParseTree is completed, the name should always be set to a Symbol. 
     */
    private Symbols name;
    private String contents;
    private Map<Symbols, List<ParseTree<Symbols>>> children;
    private List<ParseTree<Symbols>> inOrderChildren;
    
    
    
    public ParseTree(String contents, Map<Symbols, List<ParseTree<Symbols>>> children, List<ParseTree<Symbols>> inOrderChildren) {
        super();
        this.name = UNASSIGNED;
        this.contents = contents;
        this.children = children;
        this.inOrderChildren = inOrderChildren;
    }
    
    
    /**
     * Filter away the symbol toExclude from the parseTree. Returns a brand new parseTree.
     * @param toExclude should not be equal to the name of the current tree.
     * @return new ParseTree that does not include the symbol to exclude.
     */
    public ParseTree<Symbols> filter(Symbols toExclude){
        if(toExclude == name){ throw new RuntimeException("You cannot filter " + name + " from a " + name + " node.");}
        
        if(isTerminal()){
            return this;
        }
        
        Map<Symbols, List<ParseTree<Symbols>>> newchildren = new HashMap<>();
        
        Map<ParseTree<Symbols>, ParseTree<Symbols>> oldToNew = new HashMap<>();
        
        for(Entry<Symbols, List<ParseTree<Symbols>>> entry : children.entrySet()){
            if(entry.getKey()!= toExclude){
                List<ParseTree<Symbols>> newList = new ArrayList<ParseTree<Symbols>>(entry.getValue().size());
                for(ParseTree<Symbols> pt : entry.getValue()){
                    ParseTree<Symbols> newpt = pt.filter(toExclude);
                    oldToNew.put(pt, newpt);
                    newList.add(newpt);
                }
                newchildren.put(entry.getKey(), newList);
            }
        }
        List<ParseTree<Symbols>> newInOrderChildren = new ArrayList<>();
        for(ParseTree<Symbols> pt : inOrderChildren ){
            if(pt.getName() != toExclude){
                if(oldToNew.containsKey(pt)){
                    newInOrderChildren.add(oldToNew.get(pt));
                }else{
                    newInOrderChildren.add(pt.filter(toExclude));
                }
            }
        }
        
        ParseTree<Symbols> newpt =  new ParseTree<Symbols>(contents, newchildren, newInOrderChildren);
        newpt.setName(name);
        return newpt;
    }
    
    
    
    public ParseTree(Symbols nonterminal, String contents, Map<Symbols, List<ParseTree<Symbols>>> children, List<ParseTree<Symbols>> inOrderChildren) {
        super();       
        assert nonterminal != null;
        this.name = nonterminal;
        this.contents = contents;
        this.children = children;
        this.inOrderChildren = inOrderChildren;
    }

    
    public boolean assignedNT(){
        return name != UNASSIGNED;
    }
    
    
    public static <Symbols extends Enum<Symbols> > ParseTree<Symbols> concat(ParseTree<Symbols> p1, ParseTree<Symbols> p2){
        String newcontents = p1.contents + p2.contents;
        if(p1.isPlainString() && p2.isPlainString()){
            return new ParseTree<Symbols>(newcontents);
        }
        
        if(p1.assignedNT() && p2.assignedNT()){
            Map<Symbols, List<ParseTree<Symbols>>> newch = new HashMap<>();
            List<ParseTree<Symbols>> ch1 = new ArrayList<>();
            ch1.add(p1);
            newch.put(p1.name, ch1);
            if(p2.name.equals(p1.name)){
                ch1.add(p2);
            }else{
                List<ParseTree<Symbols>> ch2 = new ArrayList<>();
                ch2.add(p2);
                newch.put(p2.name, ch2);
            }     
            List<ParseTree<Symbols>> inorder = new ArrayList<>();
            inorder.add(p1);
            inorder.add(p2);
            return new ParseTree<Symbols>(newcontents, newch, inorder);
        }
        if(!p1.assignedNT() && p2.assignedNT()){
            if(p1.isTerminal()){
                Map<Symbols, List<ParseTree<Symbols>>> newch = new HashMap<>();
                List<ParseTree<Symbols>> ch1 = new ArrayList<>();
                ch1.add(p2);
                newch.put(p2.name, ch1);
                return new ParseTree<Symbols>(newcontents, newch, new ArrayList<>(ch1));
            }else{
                List<ParseTree<Symbols>> ch1;
                if(p1.children.containsKey(p2.name)){
                    ch1 = p1.children.get(p2.name);
                    ch1.add(p2);
                }else{
                    ch1 = new ArrayList<>();
                    ch1.add(p2);
                    p1.children.put(p2.name, ch1);                    
                }      
                p1.inOrderChildren.add(p2);
                return new ParseTree<Symbols>(newcontents, p1.children, p1.inOrderChildren);
            }
        }
        
        if(p1.assignedNT() && !p2.assignedNT()){
            if(p2.isTerminal()){
                Map<Symbols, List<ParseTree<Symbols>>> newch = new HashMap<>();
                List<ParseTree<Symbols>> ch1 = new ArrayList<>();
                ch1.add(p1);
                newch.put(p1.name, ch1);
                return new ParseTree<Symbols>(newcontents, newch, new ArrayList<>(ch1));
            }else{
                List<ParseTree<Symbols>> ch2;
                if(p2.children.containsKey(p1.name)){
                    ch2 = p2.children.get(p1.name);
                    ch2.add(0, p1);
                }else{
                    ch2 = new ArrayList<>();
                    ch2.add(p1);
                    p2.children.put(p1.name, ch2);                    
                }          
                p2.inOrderChildren.add(0, p1);
                return new ParseTree<Symbols>(newcontents, p2.children, p2.inOrderChildren);
            }
        }
        
        if(!p1.assignedNT() && !p2.assignedNT()){
            if(p2.isTerminal()){
                assert !p1.isTerminal();                
                return new ParseTree<Symbols>(newcontents, p1.children, p1.inOrderChildren);
            }
            if(p1.isTerminal()){
                assert !p2.isTerminal();                
                return new ParseTree<Symbols>(newcontents, p2.children, p2.inOrderChildren);
            }
            
            for(Entry<Symbols, List<ParseTree<Symbols>>> ent : p1.children.entrySet()){
                if(p2.children.containsKey(ent.getKey())){
                    ent.getValue().addAll(p2.children.remove(ent.getKey()));                    
                }
            }
            for(Entry<Symbols, List<ParseTree<Symbols>>> ent : p2.children.entrySet()){
                p1.children.put(ent.getKey(), ent.getValue());
            }
            p1.inOrderChildren.addAll(p2.inOrderChildren);
            return new ParseTree<Symbols>(newcontents, p1.children, p1.inOrderChildren);
        }
       
        throw new RuntimeException("unreachable");
    }
    
    
    public ParseTree(String contents){
        name = UNASSIGNED;
        this.contents = contents;
    }
    
    /**
     * Tells you whether a node corresponds to a terminal or a non-terminal. 
     * If it is terminal, it won't have any children.
     * @return true if it is a terminal value.
     */
    public boolean isTerminal(){
        return children == null;
    }
    
    
    private boolean isPlainString(){
        return name == UNASSIGNED && children == null;
    }
    
    
    /**
     * Ordered list of all the children nodes of this ParseTree node.
     * @return a List of all children of this ParseTree node, ordered by position in input
     */
    public List<ParseTree<Symbols>> children(){
        if(inOrderChildren != null){
            return Collections.unmodifiableList(inOrderChildren);
        }
        return Collections.emptyList();
    }
    
    public Iterator<ParseTree<Symbols>> iterator(){
        if(inOrderChildren != null){
            return inOrderChildren.iterator();
        }
        List<ParseTree<Symbols>> empty = Collections.emptyList();
        return empty.iterator();
    }
    
    /**
     * Get all the children of this PareseTree node corresponding to a particular production rule 
     * @param name 
     * Name of the non-terminal corresponding to the desired production rule.
     * @return 
     * List of children ParseTree objects that match that name.
     */
    public List<ParseTree<Symbols>> childrenByName(Symbols name){
        if(children != null && children.containsKey(name)){
            return Collections.unmodifiableList(children.get(name));
        }
        return Collections.emptyList();
    }
    
    
    /**
     * Returns the substring of the original string that corresponds to this parse tree.
     * @return String containing the contents of this parse tree.
     */
    public String getContents(){
        return contents;
    }
    
    @Override
    public String toString(){
        String s =  name + ":\"" + contents + "\"";
        if(!isTerminal()){            
            String t = "";
            for(ParseTree<Symbols> pt : this){
                    t += "," + pt;
            }            
            s += "{" + (t.length() > 0 ? t.substring(1) : "") + "}";
        }
        return s;
    }        
    
    
    
    /**
     * Generate an HTML visualization of the parse tree and write it to a file named 'filename'
     * @param filename name of the output file. It is advisable that this be an html file.
     * @throws IOException If it is not able to write to 'filename'.
     */
    public void displayToFile(String filename) throws IOException{
        try {
            PrintWriter out = new PrintWriter(filename, "UTF-8");
            InputStream in = getClass().getResourceAsStream("/visualizer.html"); 
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = reader.readLine();
            while(line != null){
                if(!line.equals("//CODEHERE")){
                    out.println(line);
                }else{
                    List<ParseTree<Symbols>> empty = Collections.emptyList();
                    String code = forDisplay(empty.iterator());
                    out.println("return " + code);
                }
                line = reader.readLine();
            }
            out.flush();
            out.close();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Tried using UTF-8 encoding but it is not supported.");
        }
    }
    
    
    
    /**
     * This method attempts to show you a visualization of the tree in your bowser. 
     * If you are not connected to the internet, or if the method cannot launch the browser, 
     * it will print a very long URL to your console which you can then copy to your browser to 
     * see the visualization.
     */
    public void display(){
        String base = "http://people.csail.mit.edu/asolar/visualizer.html?code=";
        List<ParseTree<Symbols>> empty = Collections.emptyList();
        String code = forDisplay(empty.iterator());
        try {
            String url = base  + URLEncoder.encode(code, "UTF-8");
            if(Desktop.isDesktopSupported() && url.length() < 2020)
            {
                Desktop desktop = Desktop.getDesktop();
                if(desktop.isSupported(Desktop.Action.BROWSE)){
                    try{
                        URI uri = new URI(url);
                        desktop.browse(uri);
                        return;
                    }catch(IOException e){
                        System.out.println("If you don't see a browser window, copy and paste this url to your browser " + url);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }
            }
            System.out.println("Copy and paste this url to your browser " + url);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }        
    }
    
    private String cleanString(String s){
        String rvalue = s.replace("\\", "\\\\");
        rvalue = rvalue.replace("\"", "\\\"");
        rvalue = rvalue.replace("\n", " ");
        rvalue = rvalue.replace("\r", " ");
        rvalue = rvalue.replaceAll("[ ]+", " ");
        return rvalue;
    }
    
    private String forDisplay(Iterator<ParseTree<Symbols>> sibilings){
        String s = "nd(";
        if(inOrderChildren != null){
            s += "\"" + name.toString().toLowerCase() + "\",";
            Iterator<ParseTree<Symbols>> it = inOrderChildren.iterator();
            s += it.next().forDisplay(it);
            s += ", ";
        }else{
            s += "\"" + name.toString().toLowerCase() + ":"+ cleanString(contents) + "\", uu, ";
        }
        if(sibilings.hasNext()){
            s += sibilings.next().forDisplay(sibilings) + ")";
        }else{
            s += "uu)";
        }    
        return s;
    }
    


    /**
     * Get the symbol for the terminal or non-terminal corresponding to this
     * parse tree.
     * 
     * @return T will generally be an Enum representing the different symbols in
     *         the grammar, so the return value will be one of those.
     * 
     */
    public Symbols getName() {
        assert name != UNASSIGNED;
        return name;
    }

    /**
     * Set the name of the parse tree. You can only set the name if the name was previously unassigned. 
     * If it has already been set, you cannot change it.
     *  
     * @param nonterminal
     */
    public void setName(Symbols nonterminal) {
        assert nonterminal != UNASSIGNED;
        assert this.name == UNASSIGNED;
        this.name = nonterminal;
    }
    
    

}
