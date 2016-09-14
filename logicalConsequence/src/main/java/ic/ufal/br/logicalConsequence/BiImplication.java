package ic.ufal.br.logicalConsequence;

public class BiImplication extends LogicalExpression {
	
	BiImplication(String token, Categories categ, LogicalExpression left, LogicalExpression right){
		super(token, categ, left, right);
	}
	
	void solve(LogicalExpression node){
		if(node.categ == Categories.opImp){
			
		}
	}
}
