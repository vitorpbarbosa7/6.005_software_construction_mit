// Generated from Expression.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpressionParser}.
 */
public interface ExpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#sum}.
	 * @param ctx the parse tree
	 */
	void enterSum(ExpressionParser.SumContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#sum}.
	 * @param ctx the parse tree
	 */
	void exitSum(ExpressionParser.SumContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#product}.
	 * @param ctx the parse tree
	 */
	void enterProduct(ExpressionParser.ProductContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#product}.
	 * @param ctx the parse tree
	 */
	void exitProduct(ExpressionParser.ProductContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#primitive}.
	 * @param ctx the parse tree
	 */
	void enterPrimitive(ExpressionParser.PrimitiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#primitive}.
	 * @param ctx the parse tree
	 */
	void exitPrimitive(ExpressionParser.PrimitiveContext ctx);
}