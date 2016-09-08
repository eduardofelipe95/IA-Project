package ic.ufal.br.logicalConsequence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyzer {
	private String sentence;
	private int currentLine, currentColumn, lastLine, lastColumn;
	private String line;
	private String filePath;
	
	private final char LINE_BREAK = '\n';
	
	public LexicalAnalyzer(String filePath) throws IOException{
		this.filePath = filePath;
		this.currentLine = 0;
		this.currentColumn = 0;
		this.readFile();
		this.normalize();
	}
	
	private void readFile() throws IOException{
    	sentence = Files.readAllLines(Paths.get(filePath)).get(0);
	}
	
	private void normalize(){
		sentence = sentence.replace("{", "(");
		sentence = sentence.replace("}", ")");
		sentence = sentence.replace(",", "^");
		sentence = sentence.replace("|=", "->");
	}
	
	private Character nextChar(){
		if (currentColumn < sentence.length()) {
			lastColumn = currentColumn;
			return sentence.charAt(currentColumn++);
		}
		
		return '\0';	
	}
	
	public Token nextToken(){
		char currentChar;
		String token = "";
		Token nextToken = null;
		Categories categ = null;
		boolean flag = false;
		int column = 0;
		
		do{
			currentChar = nextChar();
			
			if(currentChar == '|' && token.length() == 0){
				token += currentChar;
				currentChar = nextChar();
				
				if(currentChar == '='){
					token += currentChar;
					categ = tokenCateg(token);
					break;
				}else{
					categ = Categories.unknown;
					break;
				}
			}
			else if(!(currentChar == '\0' || currentChar == '\n' || currentChar == ' ' || currentChar == '	')){
				token += currentChar;
				categ = tokenCateg(token);
				//System.out.println(token + " " + categ);
				if(categ != Categories.unknown){
					break;
				}
			}
		}
		while(currentChar != '\0');
		
		if(!token.equals(""))
			nextToken = new Token(token, categ, lastLine, lastColumn);
		else
			nextToken = new Token(token, Categories.EOF, lastLine, lastColumn);
		
		return nextToken;
		
	}
	
	private Categories tokenCateg(String token){
			if(token.equals("(")){
				return Categories.abPar;
			}else if(token.equals(")")){
				return Categories.fcPar;
			}else if(token.equals("~")){
				return Categories.opNeg;
			}else if(token.equals("{")){
				return Categories.abCh;
			}else if(token.equals("}")){
				return Categories.fcCh;
			}else if(token.equals("^")){
				return Categories.opConj;
			}else if(token.equals("v")){
				return Categories.opDisj;
			}else if(token.equals("->")){
				return Categories.opImp;
			}else if(token.equals("<->")){
				return Categories.opBiImp;
			}else if(token.matches("[a-zA-Z]")){
				return Categories.id;
			}
			else{
				//System.out.println("Token não reconhecido.");
				return Categories.unknown;
			}
		}
}
