package Practise08;

import java.util.Optional;

public class Sample1 {

	private Optional<Integer> op;

	public Sample1(Integer num) {
		this.op = Optional.ofNullable(num);
	}

	public Optional<Integer> getOp() {
		return this.op;
	}

}
