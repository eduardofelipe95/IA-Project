package ic.ufal.br.logicalConsequence;

public class Disjunction extends LogicalExpression {
	
	Disjunction(String token, Categories categ, LogicalExpression left, LogicalExpression right){
		super(token, categ, left, right);
	}

	LogicalExpression solve() {
		if(this.left != null)
			this.left = this.left.solve();
		if(this.right != null)
			this.right = this.right.solve();
		
		if(this.right.categ == Categories.id && this.left.categ == Categories.id){
			if(this.right.token.equals(this.left.token)){
				if(this.right.nid == true ^ this.left.nid == true){
					this.token = "true";
					this.categ = Categories.prFalse;
					this.left = null;
					this.right = null;
				}
				else{
					this.token = this.right.token;
					this.categ = Categories.id;
					this.left = null;
					this.right = null;
				}
			}
		}
		else if(this.right.categ == Categories.prTrue || this.left.categ == Categories.prTrue){
			this.token = "true";
			this.categ = Categories.prTrue;
			this.left = null;
			this.right = null;
		}
		else if(this.right.categ == Categories.prFalse && this.left.categ == Categories.prFalse){
			this.token = "false";
			this.categ = Categories.prFalse;
			this.left = null;
			this.right = null;
		}
		else if(this.right.categ == Categories.prFalse && this.left.categ != Categories.prFalse){
			return this.left;
		}
		else if(this.right.categ != Categories.prFalse && this.left.categ == Categories.prFalse){
			return this.right;
		}
		
		return this;
	}

}
