package expressivo;

import java.io.File;
import java.io.IOException;

import lib6005.parser.GrammarCompiler;
import lib6005.parser.UnableToParseException;
import lib6005.parser.ParseTree;
import lib6005.parser.Parser;

public class GrammarRun {
    public static void main(String args[]) throws UnableToParseException, IOException {
        String a1 = "1 * 2 * 3"; parse(a1);
        String a2 = "x * y + z"; parse(a2);
        String a3 = "(x) * (y + z)"; parse(a3);
        String a4 = "(x) + (y * 3)"; parse(a4);
        String a5 = "(x) + (y * 3.1)"; parse(a5);
    }

    public static void parse(String string) throws UnableToParseException, IOException {
        Parser<ElementsGrammar> parser = GrammarCompiler.compile(new File("Expression.g"), ElementsGrammar.ROOT);
        ParseTree<ElementsGrammar> tree = parser.parse(string);
        // tree.display();
    }

    public enum ElementsGrammar {
        ROOT, SUM, PRODUCT, PRIMITIVE, WHITESPACE, NUMBER, VARIABLE
    }

    // public enum ElementsGrammar {
    //     ROOT, OPERATION, SUM, PRODUCT, PRIMITIVE, WHITESPACE, NUMBER, VARIABLE
    // }

}