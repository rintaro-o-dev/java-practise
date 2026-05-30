package practise15;

import java.io.Serializable;

public class Sample implements Serializable {
	private String name;
	private int old;

	public Sample(String name, int old) {
		this.name = name;
		this.old = old;
	}

	@Override
	public String toString() {
		return "Sample [ name = " + name + ", old = " + old + " ]";
	}
}
