package ic.ufal.br.logicalConsequence;

public abstract class LogicalExpression {
	Boolean nid;
	String token;
	Categories categ;
	LogicalExpression father;
	LogicalExpression left;
	LogicalExpression right;
	
	LogicalExpression(String token, Categories categ, LogicalExpression left, LogicalExpression right){
		this.nid = false;
		this.token = token;
		this.categ = categ;
		this.right = right;
		this.left = left;
	}
	
	abstract LogicalExpression solve();
}
