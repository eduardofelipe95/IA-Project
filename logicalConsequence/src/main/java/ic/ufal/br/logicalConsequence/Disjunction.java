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
		
		LogicalExpression root = this;
		
		if((root.left.categ == Categories.opDisj || root.left.categ == Categories.opConj) 
				|| (root.right.categ == Categories.opDisj || root.right.categ == Categories.opConj)){
			LogicalExpression newLeft;
			LogicalExpression newRight;
			LogicalExpression leftOp;
			LogicalExpression rightOp;
			
			if(root.left.categ == Categories.opConj || root.left.categ == Categories.opDisj){
				leftOp = root.left;
				newRight = new Disjunction("v", Categories.opDisj, leftOp.right, root.right);
				newLeft = new Disjunction("v", Categories.opDisj, leftOp.left, root.right);
			}
			else{
				rightOp = root.right;
				newLeft = new Disjunction("v", Categories.opDisj, root.left, rightOp.left);
				newRight = new Disjunction("v", Categories.opDisj, root.left, rightOp.right);
			}
			if(root.left.categ == Categories.opDisj || root.right.categ == Categories.opDisj){
				root.left = newLeft;
				root.right = newRight;
				root.solve();
			}
			else{
				root = new Conjunction("^", Categories.opConj, newLeft, newRight);
				root.solve();
				
				//return newRoot;
			}
		}

		if(root.right.categ == Categories.id && root.left.categ == Categories.id){
			if(root.right.token.equals(root.left.token)){
				if(root.right.nid == true ^ root.left.nid == true){					
					return new Atom("true", Categories.prTrue, null, null);
				}
				else{
					LogicalExpression atom = new Atom(root.right.token, Categories.id, null, null);
					atom.nid = root.right.nid;
					
					return atom;			
				}
			}
		}
		else if(root.right.categ == Categories.prTrue || root.left.categ == Categories.prTrue){
			return new Atom("true", Categories.prTrue, null, null);
		}
		else if(root.right.categ == Categories.prFalse && root.left.categ == Categories.prFalse){		
			return new Atom("false", Categories.prFalse, null, null);
		}
		else if(root.right.categ == Categories.prFalse && root.left.categ != Categories.prFalse){
			return root.left;
		}
		else if(root.right.categ != Categories.prFalse && root.left.categ == Categories.prFalse){
			return root.right;
		}
		
		return root;
	}

}
