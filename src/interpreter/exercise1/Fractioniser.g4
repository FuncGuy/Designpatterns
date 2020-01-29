grammar Fractioniser;

expression : sum ;
sum : product (PLUS product)* ;
product : value (TIMES value)* ;
value : NEG? INT+ (OVER NEG? INT+)? | OPEN_PAREN expression CLOSE_PAREN ;

PLUS : ' + ' ;
TIMES : ' * ' ;
NEG : '-' ;
INT : [0-9]+ ;
OVER : '/' ;
OPEN_PAREN : '(' ;
CLOSE_PAREN : ')' ;
