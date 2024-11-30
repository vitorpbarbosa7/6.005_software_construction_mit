package intexpr;

import java.io.File;
import java.io.IOException;

import lib6005.parser.Parser;
import lib6005.parser.UnableToParseException;
import lib6005.parser.GrammarCompiler;
import lib6005.parser.ParseTree;


// The Recursive Data Type IntegerExpression is defined as Interface
// Number implements IntegerExpression
// Plus also implements IntegerExpression

public class MyMain {

    public static void main(String[] args) throws UnableToParseException, IOException {
        // String input = "54+(2+89)";
        // String input = "(19 + 23) + (18)";
        String input = "5";
        // IntegerExpression expr = parse(input);
        IntegerExpression AbstractSyntaxTree = parse(input);

        // Evaluated the result, according to the implementation of Number and Plus

        int result = AbstractSyntaxTree.value();

        System.out.println("\n\n The Abstract Syntax Tree");
        System.out.println("\n" + input + "=" + AbstractSyntaxTree + "=" + result + "\n\n");

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
    public static IntegerExpression parse(String string) throws UnableToParseException, IOException{
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
         System.out.println("Contents of the node: " + someNode.getContents());
         System.out.println("String representation of the node: " + someNode.toString());
         System.out.println("Name of the node: " + someNode.getName());
         System.out.println("Children of the node: " + someNode.children());
         System.out.println("Is the node a terminal? " + someNode.isTerminal());
         

         // return those which correspond to specific production rule
         System.out.println("Some production rule PRIMITIVE: "+ tree.children().get(0).childrenByName(IntegerGrammar.PRIMITIVE).toString());
         System.out.println("Some production rule:NUMBER "+ tree.children().get(0).childrenByName(IntegerGrammar.NUMBER).toString());

         // Recursively visit all node
         visitAll(tree, "  ");

         
         // This tree we can print as a string
         System.out.println(tree.toString());         


         // The Abstract Syntax Tree, generalized, constructed from the ConcretePrsarseTree
         IntegerExpression AbstractSyntaxTree = buildAST(tree);

         return AbstractSyntaxTree;

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

      // which type it is ?
      switch(p.getName()) {
        /*
         * Since P is a ParseTree parametrized by the type IntegerGrammar, p.getName()
         * returns an instance of the IntegerGrammar enum. This allows the compiler to check
         * that we have covered all cases of the type of nonterminals and terminals.
        */
        case NUMBER:
          /*
           * A number will be a terminal containing a number.
           * This is a Base Case
          */
          return new Number(Integer.parseInt(p.getContents()));
        
        case PRIMITIVE:
          /*
           * A primitive will have either a number or a sum as child (in addition to some whitespace)
           * By checking which one, we can determine which case we are in.
           */
            // build Abstract Syntax Tree recursively
          if (p.childrenByName(IntegerGrammar.NUMBER).isEmpty()) {
          // if we have no number, then is not terminal
            return buildAST(p.childrenByName(IntegerGrammar.SUM).get(0));
          } else {
            // if we have a number
            return buildAST(p.childrenByName(IntegerGrammar.NUMBER).get(0));
          }

        case SUM:
          /*
           * A sum will have one or more children that need to be summed together
           * Note that we only care about the children that are primitive. 
           * Because here we are constructing a Abstract Syntax Tree, not a Concrete Parse Tree.
           * And therefore, there may be some whitespace children which we want to ignore
           */

          // track if it is the first operand to the SUM
          boolean first = true;
          IntegerExpression result = null;
          for (ParseTree<IntegerGrammar> child : p.childrenByName(IntegerGrammar.PRIMITIVE)) {
            if (first) {
                // go down with this sum to continue building the AST
                result = buildAST(child);
                // we explore the first one, and then is allowed to go to the second (right?)
                first = false;
            } else {
                // if it is not the first one, the first operand on the left, we go on the next operands
                //using the previous results as left, and the remaining as right
                // we can have like some primitives here for the sum, 
                // and from left to right, we go constructing the final, and recursively down, off course
                result = new Plus(result, buildAST(child));
            }
            }

            if (first){
                // should not have reached here, because whitepaces are to be ignored, and only primitives considered
                throw new RuntimeException("sum must have a non whitespace child:" + p);
            }
            
            // returning our builded one up till this level
            return result;

        case ROOT:
            /* 
            * The root has a single sum child, in addition to having potentialy some whitespace
             */
            return buildAST(p.childrenByName(IntegerGrammar.SUM).get(0));

        case WHITESPACE:
            /* 
             * Since we are always avoiding calling buildAST with whitespace,
             * the code should never make it here
             */
            throw new RuntimeException("You should never reach here:" + p);
          }

          /*
           * The compiler should be smart enough to tell that this code is unreachable, but it isn't.
           */
          throw new RuntimeException("You should never reach here:"+ p); 
    }

}
