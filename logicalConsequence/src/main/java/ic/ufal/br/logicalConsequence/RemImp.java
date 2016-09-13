package ic.ufal.br.logicalConsequence;

import java.util.ArrayList;

public class RemImp {
	
	private Token token;
	private LexicalAnalyzer la;
	ArrayList<String> tokens = new ArrayList<String>();
	
	public RemImp(LexicalAnalyzer la){
		this.la = la;
	}
	
	void start(){
		nextToken();
		E();
	}
	
	void nextToken(){
		token = la.nextToken();
		tokens.add(token.lexVal());
		System.out.println(token.toString());
	}
	
	void error(){
		System.out.println("Entrada inválida!");
		System.exit(0);
	}
	
	void E(){
		System.out.println("E");
		C();
		Er();
	}
	
	void Er(){
		System.out.println("Er");
		if(token.categ == Categories.opBiImp){
			//tokens.add(token);
			nextToken();
			C();
			Er();
		}
		else
			return;
	}
	
	void C(){
		System.out.println("C");
		D();
		Cr(true);
	}
	
	void Cr(boolean flag){
		System.out.println("Cr");
		if(token.categ == Categories.opImp){
			//System.out.println("entrou");
			//if(flag)
				//tokens.set(tokens.size() - 1, "v");
			nextToken();
			//tokens.add(token);
			D();
			Cr(false);
		}
		else
			return;
	}
	
	void D(){
		System.out.println("D");
		F();
		Dr();
	}
	
	void Dr(){
		System.out.println("Dr");
		if(token.categ == Categories.opDisj){
			//tokens.add(token);
			nextToken();
			F();
			Dr();
		}
		else
			return;
	}
	
	void F(){
		System.out.println("F");
		G();
		Fr();
	}
	
	void Fr(){
		System.out.println("Fr");
		if(token.categ == Categories.opConj){
			//tokens.add(token);
			nextToken();
			G();
			Fr();
		}
		else
			return;
	}
	
	void G(){
		System.out.println("G");
		if(token.categ == Categories.opNeg){
			//tokens.add(token);
			nextToken();
			H();
		}
		else
			H();
	}
	
	void H(){
		System.out.println("H");
		if(token.categ == Categories.abPar){
			nextToken();
			E();
			if(token.categ != Categories.fcPar)
				error();
			nextToken();
		}
		else if(token.categ == Categories.id)
			nextToken();
		else if(token.categ != Categories.EOF)
			error();
	}
	
}
