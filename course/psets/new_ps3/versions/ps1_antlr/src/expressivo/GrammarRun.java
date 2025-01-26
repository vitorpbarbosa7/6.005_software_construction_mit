package expressivo;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import javax.swing.*;

import expressivo.parser.ExpressionLexer;
import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser;

public class GrammarRun {
    public static void main(String args[]) throws IOException {
        String a1 = "1 * 2 * 3"; parse(a1);
        String a2 = "x * y + z"; parse(a2);
        String a3 = "(x) * (y + z)"; parse(a3);
        String a4 = "(x) + (y * 3)"; parse(a4);
        String a5 = "(x) + (y * 3.1)"; parse(a5);
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

        // Display the parse tree graphically
        JFrame frame = new JFrame("ANTLR Parse Tree");
        JPanel panel = new JPanel();
        TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
        viewer.setScale(1.5); // Adjust the scale as needed
        panel.add(viewer);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Set the desired size
        frame.setVisible(true);
    } catch (Exception e) {
    throw new IllegalArgumentException("Failed to parse expression", e);
    }
    
    }

}