package ic.ufal.br.logicalConsequence;


import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main( String[] args ) throws IOException{
    	LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer("teste.txt");
    	
//    	Token token = lexicalAnalyzer.nextToken();
//    	//System.out.println(token.toString();
//    	while(token.categ != Categories.EOF){
//    		System.out.println(token.toString());
//        	token = lexicalAnalyzer.nextToken();
//    	}
    	
//    	Parser parser = new Parser(lexicalAnalyzer);
//    	parser.start();
    	
    	RemImp remImp = new RemImp(lexicalAnalyzer);
    	remImp.start();
    	
		for(String token : remImp.tokens){
    		System.out.print(token);
    	}
    }
}
