// Generated from /Users/vpb/Documents/Documents - bitedFruit/01_ds/MIT/6.005/course/project/abcplayer/src/abc/parser/Abc.g4 by ANTLR 4.13.1

package abc.parser;
// Do not edit this .java file! Edit the .g4 file and re-run Antlr.

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AbcParser}.
 */
public interface AbcListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AbcParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(AbcParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link AbcParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(AbcParser.LineContext ctx);
}