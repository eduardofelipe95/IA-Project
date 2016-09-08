package ic.ufal.br.logicalConsequence;

public class Parser {
	
	private Token token;
	private LexicalAnalyzer la;
	
	public Parser(LexicalAnalyzer la){
		this.la = la;
	}
	
	void start(){
		nextToken();
		E();
	}
	
	void nextToken(){
		this.token = la.nextToken();
	}

	void E(){
		C();
		Er();
	}
	
	void Er(){
		if(token.categ == Categories.opBiImp){
			nextToken();
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
		if(token.categ == Categories.opImp){
			nextToken();
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
		if(token.categ == Categories.opDisj){
			nextToken();
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
		if(token.categ == Categories.opConj){
			nextToken();
			G();
			Fr();
		}
		else
			return;
	}
	
	void G(){
		if(token.categ == Categories.opDisj){
			nextToken();
			H();
		}
		else
			H();
	}
	
	void H(){
		if(token.categ == Categories.abPar){
			nextToken();
			E();
		}
		else if(token.categ == Categories.id)
			nextToken();
		else{
			System.out.println("Entrada inválida!");
			System.exit(0);
		}
	}
	
}
