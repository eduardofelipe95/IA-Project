package ic.ufal.br.logicalConsequence;


import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	static void printAST(LogicalExpression node){
		if(node.left != null)
			printAST(node.left);
		if(node.right != null)
			printAST(node.right);
		System.out.println(node.token);
	}
	
	
    public static void main( String[] args ) throws IOException{
    	LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer("teste.txt");
    	
//    	Token token = lexicalAnalyzer.nextToken();
//    	//System.out.println(token.toString();
//    	while(token.categ != Categories.EOF){
//    		System.out.println(token.toString());
//        	token = lexicalAnalyzer.nextToken();
//    	}
    	
    	Parser parser = new Parser(lexicalAnalyzer);
    	LogicalExpression AST;
    	
    	AST = parser.start();
    	
    	printAST(AST);
    	
    	System.out.println("AFTER:");
    	
    	AST = AST.solve();
    	
    	printAST(AST);
    	
//		for(String token : parser.tokens){
//    		System.out.print(token);
//    	}
    }
    
}
