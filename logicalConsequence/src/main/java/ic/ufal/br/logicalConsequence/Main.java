package ic.ufal.br.logicalConsequence;


import java.io.IOException;

import ic.ufal.br.logicalConsequence.LexicalAnalizer.LexicalAnalyzer;
import ic.ufal.br.logicalConsequence.LexicalAnalizer.Token;

public class Main {
    public static void main( String[] args ) throws IOException{
    	LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer("teste.txt");
    	lexicalAnalyzer.readFile();
    	
    	Token token = lexicalAnalyzer.nextToken();
    	//System.out.println(token.toString();
    	while(token != null){
    		System.out.println(token.toString());
        	token = lexicalAnalyzer.nextToken();
    	}
    }
}
