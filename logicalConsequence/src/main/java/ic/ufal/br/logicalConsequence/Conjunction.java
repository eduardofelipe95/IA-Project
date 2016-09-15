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
			if(this.right.token.equals(this.left.token)){
				if(this.right.nid == true ^ this.left.nid == true){
//					this.token = "false";
//					this.categ = Categories.prFalse;
//					this.left = null;
//					this.right = null;
					
					return new Atom("false", Categories.prFalse, null, null);
				}
				else{
//					this.nid = this.right.nid;
//					this.token = this.right.token;
//					this.categ = Categories.id;
//					this.left = null;
//					this.right = null;
					
					LogicalExpression atom = new Atom(this.right.token, Categories.id, null, null);
					atom.nid = this.right.nid;
					
					return atom;
					
				}
			}
		}
		else if(this.right.categ == Categories.prFalse || this.left.categ == Categories.prFalse){
//			this.token = "false";
//			this.categ = Categories.prFalse;
//			this.left = null;
//			this.right = null;
			
			return new Atom("false", Categories.prFalse, null, null);
		}
		else if(this.right.categ == Categories.prTrue && this.left.categ == Categories.prTrue){
//			this.token = "true";
//			this.categ = Categories.prTrue;
//			this.left = null;
//			this.right = null;
			
			return new Atom("true", Categories.prTrue, null, null);
		}
		else if(this.right.categ == Categories.prTrue && this.left.categ != Categories.prTrue){
			return this.left;
		}
		else if(this.right.categ != Categories.prTrue && this.left.categ == Categories.prTrue){
			return this.right;
		}
		else if((this.left.categ == Categories.opDisj || this.left.categ == Categories.opConj) && (this.right.categ != Categories.opDisj && this.right.categ != Categories.opConj)){
			LogicalExpression leftOp = this.left;
			LogicalExpression rightConj = new Conjunction("^", Categories.opConj, leftOp.right, this.right);
			
			
			this.left = leftOp.left;
			leftOp.left = this;
			leftOp.right = rightConj;
			
			leftOp.solve();
			
			return leftOp;
		}
		else if((this.left.categ != Categories.opDisj && this.left.categ != Categories.opConj) && (this.right.categ == Categories.opDisj || this.right.categ == Categories.opConj)){
			LogicalExpression rightOp = this.right;
			LogicalExpression leftConj = new Conjunction("^", Categories.opConj, this.left, rightOp.right);
			
			
			this.right = rightOp.left;
			rightOp.right = this;
			rightOp.left = leftConj;
			
			rightOp.solve();
			
			return rightOp;
		}
		
		return this;
	}

}
