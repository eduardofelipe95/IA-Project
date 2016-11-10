package ic.ufal.br.logicalConsequence;


import java.io.IOException;

public class Main {
	
	static void printAST(LogicalExpression node){
		if(node.left != null)
			printAST(node.left);
		if(node.right != null)
			printAST(node.right);
		if(node.nid == true)
			System.out.println("~" + node.token);
		else
			System.out.println(node.token);
	}
	
	
    public static void main( String[] args ) throws IOException{
    	
    	LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer("teste.txt");
    	
    	Parser parser = new Parser(lexicalAnalyzer);
    	LogicalExpression AST;
    	
    	AST = parser.start();
    	AST = AST.solve();
    	
    	if(AST.categ == Categories.prTrue){
    		System.out.println("É uma tautologia.");
    	}
    	else{
    		System.out.println("Não é uma tautologia.");
    	}
    	
    }
    
}
