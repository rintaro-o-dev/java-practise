package practise02;

public class SampleTExtNum<T extends Number> {

	private T value;

	public SampleTExtNum(T value) {
		this.value = value;
	}

	public T getValue() {
		return this.value;
	}

	// Number型を継承したTなので、Number型のメソッドが使える (intValue())
	public void show() {
		System.out.println(this.value.intValue());
		System.out.println(this.value.doubleValue());
	}

}
