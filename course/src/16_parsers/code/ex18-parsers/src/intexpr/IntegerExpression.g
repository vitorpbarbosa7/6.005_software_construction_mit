@skip whitespace {
    root ::= sum;
    sum ::= primary ('+' primary)*;
    primary ::= number | '(' sum ')';
}
number ::= [0-9]+;
whitespace ::= [ \t\r\n]+;
