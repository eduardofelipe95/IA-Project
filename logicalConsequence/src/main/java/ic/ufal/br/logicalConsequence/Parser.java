package ic.ufal.br.logicalConsequence;

import ic.ufal.br.logicalConsequence.LexicalAnalizer.LexicalAnalyzer;
import ic.ufal.br.logicalConsequence.LexicalAnalizer.Token;

public class Parser {
	
	private Token token;
	private LexicalAnalyzer la;
	
	public Parser(LexicalAnalyzer la){
		this.la = la;
	}
	
	void nextToken(){
		this.token = la.nextToken();
	}

	void E(){
		C();
		Er();
	}
	
	void Er(){
		nextToken();
		if(token.categ == Categories.opBiImp){
			C();
			Er();
		}
		else
			return;
	}
	
	void C(){
		D();
		Cr();
	}
	
	void Cr(){
		nextToken();
		if(token.categ == Categories.opImp){
			D();
			Cr();
		}
		else
			return;
	}
	
	void D(){
		F();
		Dr();
	}
	
	void Dr(){
		nextToken();
		if(token.categ == Categories.opDisj){
			F();
			Dr();
		}
		else
			return;
	}
	
	void F(){
		G();
		Fr();
	}
	
	void Fr(){
		nextToken();
		if(token.categ == Categories.opConj){
			G();
			Fr();
		}
		else
			return;
	}
	
	void G(){
		nextToken();
		if(token.categ == Categories.opDisj)
			H();
		else if(token.categ == Categories.abPar)
			E();
		else if(token.categ != Categories.id)
			System.out.println("Entrada inválida!");
	}
	
	void H(){
		nextToken();
		if(token.categ == Categories.abPar)
			E();
		else if(token.categ != Categories.id && token.categ != Categories.fcPar)
			System.out.println("Entrada inválida!");
	}
	
}
