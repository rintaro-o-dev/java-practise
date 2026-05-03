package practise02;

public class WildT<T> {
	private T value;

	public WildT(T value) {
		this.value = value;
	}

	public T getVal() {
		System.out.println("getVal()");
		return this.value;
	}

	public void setVal(T value) {
		System.out.println("setVal()");
		this.value = value;
	}
}
