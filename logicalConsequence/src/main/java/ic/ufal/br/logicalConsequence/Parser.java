package ic.ufal.br.logicalConsequence;

import java.util.ArrayList;

public class Parser {
	
	private Token token;
	private LexicalAnalyzer la;
	ArrayList<String> tokens = new ArrayList<String>();
	
	public Parser(LexicalAnalyzer la){
		this.la = la;
	}
	
	Node start(){
		nextToken();
		return E();
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

	Node E(){
		Node CNode = C();
		return Er(CNode);
	}
	
	Node Er(Node ErhNode){
		if(token.categ == Categories.opBiImp){
			String lastToken = token.lexVal();
			Categories lastCateg = token.categ;
			nextToken();
			Node CNode = C();
			Node Er1hNode = new Node(lastToken, lastCateg, ErhNode, CNode);
			return Er(Er1hNode);
		}
		else
			return ErhNode;
	}
	
	Node C(){
		Node DNode = D();
		return Cr(DNode);
	}
	
	Node Cr(Node CrhNode){
		if(token.categ == Categories.opImp){
			String lastToken = token.lexVal();
			Categories lastCateg = token.categ;
			nextToken();
			Node DNode = D();
			Node Cr1hNode = new Node(lastToken, lastCateg, CrhNode, DNode);
			return Cr(Cr1hNode);
		}
		else
			return CrhNode;
	}
	
	Node D(){
		Node FNode = F();
		return Dr(FNode);
	}
	
	Node Dr(Node DrhNode){
		if(token.categ == Categories.opDisj){
			String lastToken = token.lexVal();
			Categories lastCateg = token.categ;
			nextToken();
			Node FNode = F();
			Node Dr1hNode = new Node(lastToken, lastCateg, DrhNode, FNode);
			return Dr(Dr1hNode);
		}
		else
			return DrhNode;
	}
	
	Node F(){
		Node GNode = G();
		return Fr(GNode);
	}
	
	Node Fr(Node FrhNode){
		if(token.categ == Categories.opConj){
			String lastToken = token.lexVal();
			Categories lastCateg = token.categ;
			nextToken();
			Node GNode = G();
			Node Fr1hNode = new Node(lastToken, lastCateg, FrhNode, GNode);
			return Fr(Fr1hNode);
		}
		else
			return FrhNode;
	}
	
	Node G(){
		Node GNode = null;
		if(token.categ == Categories.opNeg){
			String lastToken = token.lexVal();
			Categories lastCateg = token.categ;
			nextToken();
			Node HNode = H();
			GNode = new Node(lastToken, lastCateg, HNode, null);
		}
		else
			GNode = H();
		
		return GNode;
	}
	
	Node H(){
		Node HNode = null;
		if(token.categ == Categories.abPar){
			nextToken();
			HNode = E();
			if(token.categ != Categories.fcPar)
				error();
			nextToken();
		}
		else if(token.categ == Categories.id){
			String lastToken = token.lexVal();
			Categories lastCateg = token.categ;
			nextToken();
			HNode = new Node(lastToken, lastCateg, null, null);
		}
		else if(token.categ != Categories.EOF)
			error();

		return HNode;
	}
	
}
