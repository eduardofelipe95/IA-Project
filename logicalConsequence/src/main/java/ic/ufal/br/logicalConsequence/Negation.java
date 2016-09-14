package ic.ufal.br.logicalConsequence;

public class Negation extends LogicalExpression {
	
	Negation(String token, Categories categ, LogicalExpression left, LogicalExpression right){
		super(token, categ, left, right);
	}

	LogicalExpression solve() {
		return this;
	}
	
}
