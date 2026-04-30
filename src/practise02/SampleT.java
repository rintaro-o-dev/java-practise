package practise02;

public class SampleT<T> {

	private T value;

	public SampleT(T value) {
		this.value = value;
	}

	public T getValue() {
		return this.value;
	}
}
