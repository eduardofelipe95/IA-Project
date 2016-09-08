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
		E();
	}
	
	void nextToken(){
		this.token = la.nextToken();
		tokens.add(token.lexVal());
		//System.out.println(token.toString());
	}

	void E(){
		C();
		Er();
	}
	
	void Er(){
		nextToken();
		if(token.categ == Categories.opBiImp){
			//tokens.add(token);
			C();
			Er();
		}
		else
			return;
	}
	
	void C(){
		D();
		Cr(true);
	}
	
	void Cr(boolean flag){
		nextToken();
		if(token.categ == Categories.opImp){
			System.out.println("entrou");
			if(flag)
				tokens.set(tokens.size() - 1, "v");
			System.out.println(tokens.get(tokens.size()-1));
			//tokens.add(token);
			D();
			Cr(false);
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
			//tokens.add(token);
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
			//tokens.add(token);
			G();
			Fr();
		}
		else
			return;
	}
	
	void G(){
		nextToken();
		if(token.categ == Categories.opDisj){
			//tokens.add(token);
			H();
		}
		else if(token.categ == Categories.abPar){
			//tokens.add(token);
			E();
		}
		else if(token.categ != Categories.id)
			System.out.println("Entrada inválida!");
	}
	
	void H(){
		nextToken();
		if(token.categ == Categories.abPar){
			//tokens.add(token);
			E();
		}
		else if(token.categ != Categories.id && token.categ != Categories.fcPar)
			System.out.println("Entrada inválida!");
	}
	
}
