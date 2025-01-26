grammar Expression;
import Configuration;

// Root rule (start rule) for the entire input
root: sum EOF;

// Sum rule: allows products separated by '+'
sum: product ('+' product)*;

// Product rule: allows primitives separated by '*'
product: primitive ('*' primitive)*;

// Primitive rule: variable, number, or parentheses wrapping sum
primitive: VAR | NUMBER | '(' sum ')';

// Lexer rules
NUMBER: [0-9]+ ('.' [0-9]+)?;      // Matches integers or floating-point numbers
VAR : [a-zA-Z];

// Ignore whitespace globally
SPACES: [ ]+ -> skip;