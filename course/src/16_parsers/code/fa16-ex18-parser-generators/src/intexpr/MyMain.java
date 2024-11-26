package intexpr;

import java.io.File;
import java.io.IOException;

import lib6005.parser.Parser;
import lib6005.parser.UnableToParseException;
import lib6005.parser.GrammarCompiler;
import lib6005.parser.ParseTree;

public class MyMain {

    public static void main(String[] args) throws UnableToParseException, IOException {
        String input = "54+(2+89)";
        // IntegerExpression expr = parse(input);
        parse(input);
        // int value = expr.value();
        // System.out.println(input + "=" + expr + "=" + value);
    }

    //terminals and nonterminals
    public enum IntegerGrammar {
        ROOT, SUM, PRIMITIVE, NUMBER, WHITESPACE 
    }

    /**
     * Parse a string into an integer arithmetic expression, displaying various
     * debugging output.
     * @throws If the string cannot be parsed, this method throws an UnableToParseException.
     * @throws If the grammar file IntegerExpression.g is not present, this will throw an IOException.
     * @throws If the grammar file is corrupted and cannot be parsed, the method will also throw an UnableToParseException.
     */
    public static void parse(String string) throws UnableToParseException, IOException{
        // takes the grammar rule we defined in IntegerExpression.g
        // the parserlib java (like antlr) will take this grammar rule, the reference root and other names of terminals an non terminals
         Parser<IntegerGrammar> parser = GrammarCompiler.compile(new File("IntegerExpression.g"), IntegerGrammar.ROOT);
         // and finally construct a parsertree
         ParseTree<IntegerGrammar> tree = parser.parse(string);
        //  tree.display();

        //  System.out.println("\nContents: " + tree.getContents());
        //  System.out.println("\nChilden: " + tree.children());
        //  System.out.println("Size List of children: " + tree.children().size());
        //  System.out.println("Is it terminal value? "+  tree.isTerminal());
         
         ParseTree<IntegerGrammar> someNode = tree.children().get(0).children().get(0).children().get(0);
         System.out.println(someNode.getContents());
         System.out.println(someNode.toString());
         System.out.println(someNode.getName());
         System.out.println(someNode.children());
         System.out.println(someNode.isTerminal());

         // return those which correspond to specific production rule
         System.out.println("Some production rule PRIMITIVE: "+ tree.children().get(0).childrenByName(IntegerGrammar.PRIMITIVE).toString());
         System.out.println("Some production rule:NUMBER "+ tree.children().get(0).childrenByName(IntegerGrammar.NUMBER).toString());

         // Recursively visit all node
         visitAll(tree, "  ");

         
         // This tree we can print as a string
         System.out.println(tree.toString());         
        //  IntegerExpression ast = buildAST(tree);
        //  return ast;
    }

    private static void visitAll(ParseTree<IntegerGrammar> node, String indent) {
        // Base case
        if (node.isTerminal()) {
            System.out.println(indent + node.getName() + ":" + node.getContents());
        } else {
            // induction rule
            System.out.println(indent + node.getName());
            // iterable is already implemented over children
            for(ParseTree<IntegerGrammar> child: node){
                visitAll(child, indent + "   ");
            }
        }
    }

    /**
     * Function converts a ParseTree to an IntegerExpression. 
     * @param p
     *  ParseTree<IntegerGrammar> that is assumed to have been constructed by the grammar in IntegerExpression.g
     * @return
     */
    private static IntegerExpression buildAST(ParseTree<IntegerGrammar> p){

        switch(p.getName()){
        /*
         * Since p is a ParseTree parameterized by the type IntegerGrammar, p.getName() 
         * returns an instance of the IntegerGrammar enum. This allows the compiler to check
         * that we have covered all the cases.
         */
        case NUMBER:
            /*
             * A number will be a terminal containing a number.
             */
            return new Number(Integer.parseInt(p.getContents()));
        case PRIMITIVE:
            /*
             * A primitive will have either a number or a sum as child (in addition to some whitespace)
             * By checking which one, we can determine which case we are in.
             */             

            if(p.childrenByName(IntegerGrammar.number).isEmpty()){
                return buildAST(p.childrenByName(IntegerGrammar.sum).get(0));
            }else{
                return buildAST(p.childrenByName(IntegerGrammar.number).get(0));
            }

        case SUM:
            /*
             * A sum will have one or more children that need to be summed together.
             * Note that we only care about the children that are primitive. There may also be 
             * some whitespace children which we want to ignore.
             */
            boolean first = true;
            IntegerExpression result = null;
            for(ParseTree<IntegerGrammar> child : p.childrenByName(IntegerGrammar.PRIMITIVE)){                
                if(first){
                    result = buildAST(child);
                    first = false;
                }else{
                    result = new Plus(result, buildAST(child));
                }
            }
            if (first) {
                throw new RuntimeException("sum must have a non whitespace child:" + p);
            }
            return result;
        case ROOT:
            /*
             * The root has a single sum child, in addition to having potentially some whitespace.
             */
            return buildAST(p.childrenByName(IntegerGrammar.sum).get(0));
        case WHITESPACE:
            /*
             * Since we are always avoiding calling buildAST with whitespace, 
             * the code should never make it here. 
             */
            throw new RuntimeException("You should never reach here:" + p);
        }   
        /*
         * The compiler should be smart enough to tell that this code is unreachable, but it isn't.
         */
        throw new RuntimeException("You should never reach here:" + p);
    }

    
}
