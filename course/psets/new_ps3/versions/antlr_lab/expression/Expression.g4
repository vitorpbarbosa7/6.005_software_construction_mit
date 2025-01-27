grammar Expression;

// Sum rule: allows products separated by '+'
sum: product ('+' product)* EOF;

// Product rule: allows primitives separated by '*'
product: primitive ('*' primitive)*;

// Primitive rule: variable, number, or parentheses wrapping sum
primitive: VAR | NUMBER | '(' sum ')';

// Lexer rules
NUMBER: [0-9]+ ('.' [0-9]+)?;      // Matches integers or floating-point numbers
VAR : [a-zA-Z];

// Ignore whitespace globally
WHITESPACE: [ \t\r\n]+ -> skip;
// SPACES: [ ]+ -> skip;
