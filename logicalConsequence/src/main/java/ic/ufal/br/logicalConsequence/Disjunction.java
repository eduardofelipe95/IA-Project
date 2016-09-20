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
		
		LogicalExpression newRoot;
		if(this.right.categ == Categories.id && this.left.categ == Categories.id){
			if(this.right.token.equals(this.left.token)){
				if(this.right.nid == true ^ this.left.nid == true){
//					this.token = "true";
//					this.categ = Categories.prTrue;
//					this.left = null;
//					this.right = null;
					
					return new Atom("true", Categories.prTrue, null, null);
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
		else if(this.right.categ == Categories.prTrue || this.left.categ == Categories.prTrue){
//			this.token = "true";
//			this.categ = Categories.prTrue;
//			this.left = null;
//			this.right = null;
			
			return new Atom("true", Categories.prTrue, null, null);
		}
		else if(this.right.categ == Categories.prFalse && this.left.categ == Categories.prFalse){
//			this.token = "false";
//			this.categ = Categories.prFalse;
//			this.left = null;
//			this.right = null;
			
			return new Atom("false", Categories.prFalse, null, null);
		}
		else if(this.right.categ == Categories.prFalse && this.left.categ != Categories.prFalse){
			return this.left;
		}
		else if(this.right.categ != Categories.prFalse && this.left.categ == Categories.prFalse){
			return this.right;
		}
		else if((this.left.categ == Categories.opDisj || this.left.categ == Categories.opConj) 
				|| (this.right.categ == Categories.opDisj || this.right.categ == Categories.opConj)){
			LogicalExpression newLeft;
			LogicalExpression newRight;
			LogicalExpression leftOp;
			LogicalExpression rightOp;
			
			if(this.left.categ == Categories.opConj || this.left.categ == Categories.opDisj){
				leftOp = this.left;
				newRight = new Disjunction("v", Categories.opDisj, leftOp.right, this.right);
				newLeft = new Disjunction("v", Categories.opDisj, leftOp.left, this.right);
			}
			else{
				rightOp = this.right;
				newLeft = new Disjunction("v", Categories.opDisj, this.left, rightOp.left);
				newRight = new Disjunction("v", Categories.opDisj, this.left, rightOp.right);
			}
			if(this.left.categ == Categories.opDisj || this.right.categ == Categories.opDisj){
				this.left = newLeft;
				this.right = newRight;
				this.solve();
			}
			else{
				newRoot = new Conjunction("^", Categories.opConj, newLeft, newRight);
				newRoot.solve();
				
				return newRoot;
			}
		}
//		else if((this.left.categ == Categories.opDisj || this.left.categ == Categories.opConj) && (this.right.categ != Categories.opDisj && this.right.categ != Categories.opConj)){
//			LogicalExpression leftOp = this.left;
//			LogicalExpression rightDisj = new Disjunction("v", Categories.opDisj, leftOp.right, this.right);
//			
//			
//			this.left = leftOp.left;
//			leftOp.left = this;
//			leftOp.right = rightDisj;
//			
//			leftOp.solve();
//			
//			return leftOp;
//		}
//		else if((this.left.categ != Categories.opDisj && this.left.categ != Categories.opConj) && (this.right.categ == Categories.opDisj || this.right.categ == Categories.opConj)){
//			LogicalExpression rightOp = this.right;
//			LogicalExpression leftDisj = new Disjunction("v", Categories.opDisj, this.left, rightOp.right);
//			
//			
//			this.right = rightOp.left;
//			rightOp.right = this;
//			rightOp.left = leftDisj;
//			
//			rightOp.solve();
//			
//			return rightOp;
//		}
		
		return this;
	}

}
