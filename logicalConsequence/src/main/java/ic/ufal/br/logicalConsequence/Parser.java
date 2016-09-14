package ic.ufal.br.logicalConsequence;

public class Parser {
	
	private Token token;
	private LexicalAnalyzer la;
	
	public Parser(LexicalAnalyzer la){
		this.la = la;
	}
	
	Node start(){
		nextToken();
		return E();
	}
	
	void nextToken(){
		this.token = la.nextToken();
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
			Token lastToken = token;
			nextToken();
			Node CNode = C();
			Node Er1hNode = new Node(lastToken, ErhNode, CNode);
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
			Token lastToken = token;
			nextToken();
			Node DNode = D();
			Node Cr1hNode = new Node(lastToken, CrhNode, DNode);
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
			Token lastToken = token;
			nextToken();
			Node FNode = F();
			Node Dr1hNode = new Node(lastToken, DrhNode, FNode);
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
			Token lastToken = token;
			nextToken();
			Node GNode = G();
			Node Fr1hNode = new Node(lastToken, FrhNode, GNode);
			return Fr(Fr1hNode);
		}
		else
			return FrhNode;
	}
	
	Node G(){
		Node GNode = null;
		if(token.categ == Categories.opNeg){
			Token lastToken = token;
			nextToken();
			Node HNode = H();
			GNode = new Node(lastToken, HNode, null);
		}
		else
			GNode = H();
		
		return GNode;
	}
	
	Node H(){
		Node HNode = null;
		if(token.categ == Categories.abPar){
			nextToken();
			E();
			if(token.categ != Categories.fcPar)
				error();
			nextToken();
		}
		else if(token.categ == Categories.id){
			Token lastToken = token;
			nextToken();
			HNode = new Node(lastToken, null, null);
		}
		else if(token.categ != Categories.EOF)
			error();

		return HNode;
	}
	
}
