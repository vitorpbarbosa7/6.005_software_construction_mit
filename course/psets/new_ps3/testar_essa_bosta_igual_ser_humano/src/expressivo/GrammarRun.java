package expressivo;

import java.io.File;
import java.io.IOException;

import lib6005.parser.GrammarCompiler;
import lib6005.parser.UnableToParseException;
import lib6005.parser.ParseTree;
import lib6005.parser.Parser;

public class GrammarRun {

    public static void main (String args) throws UnableToParseException, IOException{

        String a1 = "1 + 2 + 3";

        parse(a1);

    }

    public enum ElementsGrammar {
        ROOT, OPERATION, PRIMITIVE, NUMBER, WHITESPACE
    }


    public static void parse(String string) throws UnableToParseException, IOException {

        Parser<ElementsGrammar> parser = GrammarCompiler.compile(new File("Expression.g"), ElementsGrammar.ROOT);

        ParseTree<ElementsGrammar> tree = parser.parse(string);
        tree.display();
    }
    
}
