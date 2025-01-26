grammar Expression;

// Root rule (start rule) for the entire input
root: sum EOF;

// Sum rule: allows products separated by '+'
sum: product ('+' product)*;

// Product rule: allows primitives separated by '*'
product: primitive ('*' primitive)*;

// Primitive rule: variable, number, or parentheses wrapping sum/product
primitive: variable 
         | number 
         | '(' sum ')' 
         | '(' product ')';

// Lexer rules
number: [0-9]+ ('.' [0-9]+)?;      // Matches integers or floating-point numbers
variable: [a-zA-Z]+;               // Matches variable names made of letters

WHITESPACE: [ ]+ -> channel(HIDDEN);  // Skip whitespace globally
