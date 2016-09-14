package ic.ufal.br.logicalConsequence;

public class Node {
	Token token;
	Node left;
	Node right;
	
	public Node(Token token, Node left, Node right) {
		this.token = token;
		this.left = left;
		this.right = right;
	}

}
