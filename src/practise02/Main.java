package practise02;

public class Main {

	public static void main(String[] args) {

		/*
		 * ジェネリクスのデモ
		 */

		// ジェネリクス (SampleT<T>)
		SampleT<Integer> sT1 = new SampleT<>(123);
		SampleT<Double> sT2 = new SampleT<>(123.456);
		SampleT<String> sT3 = new SampleT<>("sample-sample");
		SampleT<Boolean> sT4 = new SampleT<>(true);

		System.out.println("ジェネリクスで同じメソッドを呼び出し ↓");
		System.out.println("1: " + sT1.getValue());
		System.out.println("2: " + sT2.getValue());
		System.out.println("3: " + sT3.getValue());
		System.out.println("4: " + sT4.getValue());

	}

}
