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
    	
    	RemImp parser = new RemImp(lexicalAnalyzer);
    	parser.start();
    	
 
		for(String token : parser.tokens){
    		System.out.print(token);
    	}
    }
}
