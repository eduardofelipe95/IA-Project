package ic.ufal.br.logicalConsequence;

public class Implication extends LogicalExpression {
	
	Implication(String token, Categories categ, LogicalExpression left, LogicalExpression right){
		super(token, categ, left, right);
	}
	
	void solve(LogicalExpression node){
		if(node.categ == Categories.opImp){
			LogicalExpression neg = new Negation("~", Categories.opNeg, node.left, null);
			LogicalExpression disj = new Disjunction("v", Categories.opDisj, neg, node.right);
			node = disj;
		}
		else{
			if(node.right != null)
				solve(node.right);
			if(node.left != null)
				solve(node.left);
		}
	}

}