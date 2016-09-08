package ic.ufal.br.logicalConsequence.LexicalAnalizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyzer {
	private List<String> linesList;
	private int currentLine, currentColumn, lastColumn;
	private String line;
	private String filePath;
	
	private final char LINE_BREAK = '\n';
	
	public LexicalAnalyzer(String filePath){
		linesList = new ArrayList<String>();
		this.filePath = filePath;
		this.currentLine = 0;
		this.currentColumn = 0;
	}
	
	public void readFile() throws IOException{
    	for (String line : Files.readAllLines(Paths.get(filePath))) {
    		linesList.add(line);
    	}
	}
	
	private Character nextChar(){
		
		if(currentLine < linesList.size()){
			line = linesList.get(currentLine);
			
			if (currentColumn < line.length()) {
				return line.charAt(currentColumn++);
			}
			else {
				
				lastColumn = currentColumn;
				currentColumn = 0;
				currentLine++;
				return LINE_BREAK;
			}
		}
		
		return '\0';	
	}
	
	public Token nextToken(){
		char currentChar;
		String token = "";
		Token nextToken = null;
		CategoriesTokens categ = null;
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
					categ = CategoriesTokens.unknown;
					break;
				}
			}else if(!(currentChar == '\0' || currentChar == '\n' || currentChar == ' ' || currentChar == '	')){
				token += currentChar;
				categ = tokenCateg(token);
				System.out.println(token + " " + categ);
				if(categ != CategoriesTokens.unknown){
					break;
				}
			}
			
		}
		while(currentChar != '\0');
		
		
		if(!token.equals(""))
			nextToken = new Token(token, categ, currentLine, lastColumn);
		else{
			nextToken = null;
		}
		
			
		return nextToken;
		
	}
	
	
	private CategoriesTokens tokenCateg(String token){
			
			if(token.equals("(")){
				return CategoriesTokens.abParent;
			}else if(token.equals(")")){
				return CategoriesTokens.fcParent;
			}else if(token.equals("{")){
				return CategoriesTokens.abCh;
			}else if(token.equals("}")){
				return CategoriesTokens.fcCh;
			}else if(token.equals("^")){
				return CategoriesTokens.opConj;
			}else if(token.equals("v")){
				return CategoriesTokens.opDisj;
			}else if(token.equals("->")){
				return CategoriesTokens.opImp;
			}else if(token.equals("~")){
				return CategoriesTokens.opNeg;
			}else if(token.equals("<->")){
				return CategoriesTokens.opBiImp;
			}else if(token.equals(token.equals("A"))){
				return CategoriesTokens.opBiImp;
			}
				
			else{
				
				//System.out.println("Token não reconhecido.");
				return CategoriesTokens.unknown;
			}
		}
}
