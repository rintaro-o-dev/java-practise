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

		System.out.println("・ ジェネリクスで同じメソッドを呼び出し ↓");
		System.out.println("1: " + sT1.getValue());
		System.out.println("2: " + sT2.getValue());
		System.out.println("3: " + sT3.getValue());
		System.out.println("4: " + sT4.getValue());

		// // ジェネリクスクラスの代入可能性 (1. ラッパークラス等、継承もtおメソッドに重点)
		// Number を extends したジェネリクスクラスでは、上限が Number に設定される
		SampleTExtNum<Integer> sTEN1 = new SampleTExtNum<>(123); // 数値型なので可能
		SampleTExtNum<Double> sTEN2 = new SampleTExtNum<>(123.456); // 数値型なので可能
		// SampleTExtNum<String> sTEN3 = new SampleTExtNum<>("sample-sample");
		// Number型で担保されていないStringなのでこればできない
		// SampleTExtNum<Boolean> sTEN4 = new SampleTExtNum<>(true);
		// Number型で担保されていないboolean型なのでこれはできない

		System.out.println("\n・ ジェネリクスに継承させた範囲を上限とすることができる ↓");
		System.out.println("1: " + sTEN1.getValue());
		System.out.println("2: " + sTEN2.getValue());
		System.out.println("String・Boolean は Number 型で取り扱っていないため上限外");

		System.out.println("\n・ ジェネリクスに継承させておくと、継承元のメソッドも使える ↓");
		// Number型を継承したTなので、Number型のメソッドが使える (intValue())
		// ↓
		// public void show() {
		// System.out.println(this.value.intValue());
		// System.out.println(this.value.doubleValue());
		// }
		sTEN1.show();
		sTEN2.show();

		// ジェネリクスの型推論が決定するまではあくまでも Object 型でしか扱えない
		Object catA = sTEN1; // 全てのスーパークラスの Object型であれば受け取れる
		// Number catB = sTEN1; <- これはできない
		// Integer catC = sTEN1; <- これもできない
		// SampleTExtNum<Number> catD = sTEN1; <- これもできない
		SampleTExtNum<Integer> catE = sTEN1; // Integer は Number のサブクラスでかつ型が一致しているので代入できる
		System.out.println("\n・ ジェネリクスが確定していない場合は基本Object型でしか受け取れない ↓");
		System.out.println("Object catA: " + catA.toString());
		System.out.println("Number catB : NG　(SampleTExtNum は Number ではない => 型の不一致)");
		System.out.println("Integer catC : NG　(SampleTExtNum は Number ではない => 型の不一致))");
		System.out.println("SampleTExtNum<Number> catD : NG　(SampleTExtNum<Integer> は SampleTExtNum<Number> のサブタイプではない => ジェネリクスは invariant)");
		System.out.println("SampleTExtNum<Integer> catE : " + catE.toString());

		// ジェネリクスクラスの代入可能性 (2. 継承関係に重点)
		System.out.println("\n・ 継承関係 : XA <- XB <- XC で、XT<T extends XB>の場合 ↓");
		// 継承関係 : XA <- XB <- XC
		// C は B のサブクラス（extends）
		// C は B のサブタイプ（代入可能性）
		// しかしジェネリクスは不変（invariant）なので、
		// X<C> は X<B> のサブタイプにはならない（完全に別の型）
		XA xa = new XA(); // 親
		XB xb = new XB(); // 子
		XC xc = new XC(); // 孫
		// XT<T extends XB>
		// XT<XB> xt1 = new XT<>(xa); <- XA は XB　のサブクラスではない(superクラス)のため、そもそも上限外となり new できない
		XT<XB> xt2 = new XT<>(xb); // 上限
		XT<XB> xt3 = new XT<>(xc); // XC は XB のサブクラスなので引数として渡せるが、型パラメータは左側の XT<XB> で決まるため xt3 の型は XT<XB> になる!!
		XT<XC> xt4 = new XT<>(xc); // XC は XB のサブクラスなので new できる

		System.out.println("1. XT<XB> xt2 = new XT<>(xb);");
		// XT<XB> の受け取り
		Object catXt21 = xt2;
		XT<XB> catXt22 = xt2;
		// XT<XC> catXt23 = xt3; <- XC は XB のサブタイプであっても、ジェネリクスは不変(invariant)のため、まったく別の型とみなされNG
		System.out.println("Object catXt2 : " + catXt21.toString());
		System.out.println("XT<XB> catXt2 : " + catXt22.toString());
		System.out.println("XT<XC> catXt2 : NG　=> X<C> は X<B> のサブタイプではない　型の不一致)");

		System.out.println("\n2. XT<XB> xt3 = new XT<>(xc);");
		Object catXt31 = xt3;
		XT<XB> catXt32 = xt3;
		// XT<XC> catXt33 = xt3; <- xc は XB のサブタイプ XC の型であるためnewまではできても、ジェネリクスは不変(invariant)のため、まったく別の型とみなされNG
		System.out.println("Object catXt3 : " + catXt31.toString());
		System.out.println("XT<XB> catXt3 : " + catXt32.toString());
		System.out.println("XT<XC> catXt3 : NG　=> X<C> は X<B> のサブタイプではない　型の不一致)");

		System.out.println("\n3. XT<XC> xt4 = new XT<>(xc);");
		Object catXt41 = xt4;
		// XT<XB> catxt42 = xt4; <- ジェネリクスは不変(invariant)のため、まったく別の型とみなされNG
		XT<XC> catXt43 = xt4;
		System.out.println("Object catXt4 : " + catXt41.toString());
		System.out.println("XT<XB> catXt4 : NG　=> X<B> は X<C> のサブタイプではない　型の不一致)");
		System.out.println("XT<XC> catXt4 : " + catXt43.toString());



	}

}
