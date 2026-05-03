package practise02;

public class XT<T extends XB> {

	private T value;

	public XT(T value) {
		this.value = value;
	}

	public void show() {
		System.out.println("show() : " + this.value);
	}

}
