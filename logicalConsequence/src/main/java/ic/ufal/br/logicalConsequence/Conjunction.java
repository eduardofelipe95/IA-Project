package ic.ufal.br.logicalConsequence;

public class Conjunction extends LogicalExpression {
	
	Conjunction(String token, Categories categ, LogicalExpression left, LogicalExpression right){
		super(token, categ, left, right);
	}
	
	LogicalExpression solve() {
		if(this.left != null)
			this.left = this.left.solve();
		if(this.right != null)
			this.right = this.right.solve();
		
		if(this.right.categ == Categories.id && this.left.categ == Categories.id){
			if(this.right.nid == true ^ this.left.nid == true){
				if(this.right.token.equals(this.left.token)){
					this.token = "false";
					this.categ = Categories.prFalse;
					this.left = null;
					this.right = null;
				}
			}
		}
		
		return this;
	}

}
