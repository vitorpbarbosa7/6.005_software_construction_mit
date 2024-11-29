/* Copyright (c) 2015-2017 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */

// grammar Expression;

/*
 *
 * You should make sure you have one rule that describes the entire input.
 * This is the "start rule". Below, "root" is the start rule.
 *
 * For more information, see the parsers reading.
 */
root ::= operation;
@skip whitespace{
	operation ::= primitive ('+'|'*' primitive)*;
	primitive ::= number | '(' operation ')';
}
// must consider floatings
// [0-9]+ :: one more ocurrences of this
// (\.[0-9]+)? :: this can occur or not, note that the point is not 
// followerd by +, only the [0-9]
number ::= [0-9]+(\.[0-9]+)?;
whitespace ::= [ \t\r\n]+;
