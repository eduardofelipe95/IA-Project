package ic.ufal.br.logicalConsequence;

public class Conjunction extends LogicalExpression {
	
	Conjunction(String token, Categories categ, LogicalExpression left, LogicalExpression right){
		super(token, categ, left, right);
	}
	
	LogicalExpression solve() {
		return this;
	}

}
