package expressivo;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.gui.TreeViewer;

import expressivo.parser.ExpressionLexer;
import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser;

public class GrammarRun {
    public static void main(String args[]) throws IOException {
        String a1 = "1 * 2 * 3"; parse(a1);
        // String a2 = "x * y + z"; parse(a2);
        // String a3 = "(x) * (y + z)"; parse(a3);
        // String a4 = "(x) + (y * 3)"; parse(a4);
        // String a5 = "(x) + (y * 3.1)"; parse(a5);
    }

    public static void parse(String string) throws IOException {
        try {
            CharStream cs = new ANTLRInputStream(string);
            ExpressionLexer lexer = new ExpressionLexer(cs);
            lexer.removeErrorListeners();
            lexer.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                        int line, int charPositionInLine, String msg, RecognitionException e) {
                    throw new IllegalArgumentException("Invalid expression: " + msg);
                }
            });

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExpressionParser parser = new ExpressionParser(tokens);

            parser.removeErrorListeners();
            parser.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                        int line, int charPositionInLine, String msg, RecognitionException e) {
                    throw new IllegalArgumentException("Invalid expression: " + msg);
                }
            });

            ExpressionParser.SumContext sumContext = parser.sum();

                // Print the parse tree in LISP-style notation
        System.out.println(sumContext.toStringTree(parser));

        // ParseTree tree = parser.sum();

        // display(tree, parser);
        // Display the parse tree graphically
    } catch (Exception e) {
    throw new IllegalArgumentException("Failed to parse expression", e);
    }
}

    public static void display(ParseTree tree, Parser parser) {
        // Create a JFrame to hold the TreeViewer
        JFrame frame = new JFrame("Parse Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
    
        // Create the TreeViewer component
        TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
        viewer.setScale(1.5); // Adjust the scale as needed
    
        // Add the TreeViewer to a JScrollPane for better viewing
        JScrollPane scrollPane = new JScrollPane(viewer);
        frame.add(scrollPane);
    
        // Display the JFrame
        frame.setVisible(true);
    }
    
}
