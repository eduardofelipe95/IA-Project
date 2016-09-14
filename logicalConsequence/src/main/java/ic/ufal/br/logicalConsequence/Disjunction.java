package ic.ufal.br.logicalConsequence;

public class Disjunction extends LogicalExpression {
	
	Disjunction(String token, Categories categ, LogicalExpression left, LogicalExpression right){
		super(token, categ, left, right);
	}

	LogicalExpression solve() {
		
		
		return this;
	}

}
