package ic.ufal.br.logicalConsequence;

public class Atom extends LogicalExpression {
	
	public Atom(String token, Categories categ, LogicalExpression left, LogicalExpression right) {
		super(token, categ, left, right);
	}

	LogicalExpression solve() {
		return this;
	}

}
