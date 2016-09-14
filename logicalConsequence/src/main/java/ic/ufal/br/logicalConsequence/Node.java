package ic.ufal.br.logicalConsequence;

public class Node {
	String token;
	Categories categ;
	Node left;
	Node right;
	
	public Node(String token, Categories categ, Node left, Node right) {
		this.token = token;
		this.left = left;
		this.right = right;
	}

}
