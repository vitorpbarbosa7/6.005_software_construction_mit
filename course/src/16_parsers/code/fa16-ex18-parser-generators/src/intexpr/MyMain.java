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

    
}
