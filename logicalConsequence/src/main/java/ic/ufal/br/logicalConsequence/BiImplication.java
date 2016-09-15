package ic.ufal.br.logicalConsequence;

import java.beans.Expression;

public class BiImplication extends LogicalExpression {
	
	BiImplication(String token, Categories categ, LogicalExpression left, LogicalExpression right){
		super(token, categ, left, right);
	}
	
	LogicalExpression solve() {
		LogicalExpression leftChild, rightChild;
		
		leftChild = this.left;
		rightChild = this.right;
		
		this.token = "v";
		this.categ = Categories.opDisj;
		this.left = new Implication("->", Categories.opImp, leftChild, rightChild);
		this.right = new Implication("->", Categories.opImp, null, null);
		
		this.copyBranchAST(this.right, rightChild, leftChild);
		
		this.solve();
		
		return this;
	}
	
	void copyBranchAST(LogicalExpression node, LogicalExpression subTreeLeft,
			LogicalExpression subTreeRight){
		
		if(subTreeLeft != null){
			node.left = this.copy(subTreeLeft);
			this.copyBranchAST(node.left, subTreeLeft, subTreeRight);
		}
		if(subTreeRight != null){
			node.right = this.copy(subTreeRight);
			this.copyBranchAST(node.right, subTreeLeft, subTreeRight);
		}
		
	}
	
	LogicalExpression copy(LogicalExpression node){
		if(node.categ == Categories.opConj){
			return new Conjunction("^", Categories.opConj, null, null);
		}else if(node.categ == Categories.opDisj){
			return new Disjunction("v", Categories.opDisj, null, null);
		}else if(node.categ == Categories.opImp){
			return new Implication("->", Categories.opImp, null, null);
		}else if(node.categ == Categories.opBiImp){
			return new BiImplication("<->", Categories.opBiImp, null, null);
		}else if(node.categ == Categories.opNeg){
			return new Negation("~", Categories.opNeg, null, null);
		}else{
			return new Atom(node.token,Categories.id, null, null);
		}
	}
}
