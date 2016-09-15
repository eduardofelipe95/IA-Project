package ic.ufal.br.logicalConsequence;

public class Negation extends LogicalExpression {
	
	Negation(String token, Categories categ, LogicalExpression left, LogicalExpression right){
		super(token, categ, left, right);
	}

	LogicalExpression solve() {
		if(this.left != null)
			this.left = this.left.solve();
		if(this.right != null)
			this.right = this.right.solve();
		
		return deMorgan(this.left);
	}
	
	LogicalExpression deMorgan(LogicalExpression node){
		if(node.categ == Categories.opConj){
			node.token = "v";
			node.categ = Categories.opDisj;
		}
		else if(node.categ == Categories.opDisj){
			node.token = "^";
			node.categ = Categories.opConj;
		}
		else if(node.categ == Categories.id){
			node.nid = true;
		}
		
		if(node.left != null)
			node.left = deMorgan(node.left);
		if(node.right != null)
			node.right = deMorgan(node.right);
		
		return node;
	}
	
}
