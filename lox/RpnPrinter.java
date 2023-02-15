package lox;


import lox.Expr.Binary;
import lox.Expr.Grouping;
import lox.Expr.Literal;
import lox.Expr.Unary;

/*
 * Code for challenge 5.3: A Visitor that prints an expression in reverse polish notation
 */
public class RpnPrinter implements Expr.Visitor<String> {

    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Binary expr) {
        return expr.left.accept(this) + " " + expr.right.accept(this) + " " + expr.operator.lexeme + " ";
    }

    @Override
    public String visitGroupingExpr(Grouping expr) {
        return expr.expression.accept(this);
    }

    @Override
    public String visitLiteralExpr(Literal expr) {
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Unary expr) {
        return expr.operator.lexeme + expr.right;
    }
    
    public static void main(String[] args) {
        Expr expression = new Expr.Binary(
            new Expr.Binary(new Expr.Literal(1), new Token(TokenType.PLUS, "+", null, 1), new Expr.Literal(2)), 
            new Token(TokenType.STAR, "*", null, 1),
            new Expr.Binary(new Expr.Literal(4), new Token(TokenType.MINUS, "-", null, 1), new Expr.Literal(3)) 
            );
    
        System.out.println(new RpnPrinter().print(expression));
    }
}
