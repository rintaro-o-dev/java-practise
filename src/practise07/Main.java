package practise07;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

	public static void main(String[] args) {

		// Function<T(引数), R(戻り値)>
		// ↓
		// @FunctionalInterface
		// public interface Function<T, R> {
		// R apply(T t);
		// --- default メソッド ---
		// default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) { ... }
		// default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {... }
		// --- static メソッド ---
		// static <T> Function<T, T> identity() { ... }
		// }
		// 1. apply メソッドしかない
		// 2. 返り値がある実装に使う
		System.out.println("・ Function<T, R>");
		Function<Integer, String> func1 = num -> {
			int ans;
			ans = num * 2;
			return "anser = " + ans;
		};
		// 使うときは function.apply(x);
		System.out.println("Function : " + func1.apply(3));
		// 値の変換
		Function<String, Integer> func2 = str -> str.length();
		System.out.println(func2.apply("ABCDE"));
		// フィールド取り出し
		Function<Sample1, String> func3 = sample1 -> sample1.getName();
		Sample1 s1 = new Sample1("user");
		System.out.println(func3.apply(s1));
		// default メソッド
		// A.andThen(B)
		// A のあとに B を実行する (after)(出力を次のメソッドへ)
		System.out.println("-- A.andThen(B)");
		Function<Integer, String> funcA = num -> num + "+AAA";
		Function<String, String> funcB = str -> str + "+BBB";
		Function<Integer, String> funcAndthen = funcA.andThen(funcB); // A(入口) と B(出口) と同じ引数型にする！<Integer, String>
		System.out.println("res : " + funcAndthen.apply(123));
		// A.compose(B)
		// A の前に B を実行する (before)(出力を次のメソッドへ)
		System.out.println("-- A.compose(B)");
		Function<String, String> funcC = str -> str + "+CCC";
		Function<String, String> funcD = str -> str + "+DDD";
		Function<String, String> funcCompose = funcC.compose(funcD);
		System.out.println("res : " + funcCompose.apply("AB"));
		// static メソッド
		// A.identity()
		// A の入力をそのまま返す
		System.out.println("-- Function.identity()");
		Function<Integer, Integer> funcE = Function.identity(); // そのまま返す
		System.out.println("res : " + funcE.apply(1000));

		// Predicate<T(引数)>
		// ↓
		// @FunctionalInterface
		// public interface Predicate<T> {
		// boolean test(T t);
		// // デフォルトメソッド（抽象ではない）
		// default Predicate<T> and(Predicate<? super T> other) { ... }
		// default Predicate<T> or(Predicate<? super T> other) { ... }
		// default Predicate<T> negate() { ... }
		// // static メソッド (抽象メソッドはこれだけ)
		// static <T> Predicate<T> isEqual(Object targetRef) { ... }
		// }
		// testメソッド しかない
		System.out.println("\n・ Predicate<T>");
		Predicate<Integer> pred1 = num -> {
			Integer ans = 10;
			return ans.equals(num);
		};
		// 使うときは predicate.test(x);
		System.out.println("Predicate : " + pred1.test(5));
		System.out.println("Predicate : " + pred1.test(10));
		// default メソッド
		// A.and(B)
		// A && B (A かつ B)
		System.out.println("-- A.and(B)");
		Predicate<Integer> predA = num -> num > 0;
		Predicate<Integer> predB = num -> num < 10;
		Predicate<Integer> predAnd = predA.and(predB);
		System.out.println("res : " + predAnd.test(10));
		System.out.println("res : " + predAnd.test(5));
		// A.or(B)
		// A || B (A または B)
		System.out.println("-- A.or(C)");
		Predicate<Integer> predC = num -> num > 10;
		Predicate<Integer> predOr = predA.or(predC);
		System.out.println("res : " + predOr.test(100));
		System.out.println("res : " + predOr.test(5));
		System.out.println("res : " + predOr.test(-1));
		// A.negate()
		// A の結果を反転させる
		System.out.println("-- A.negate()");
		Predicate<Integer> predNegate = predOr.negate();
		System.out.println("res : " + predNegate.test(5));
		System.out.println("res : " + predNegate.test(-1));
		// static メソッド
		// Predicate.isEqual(X)
		// 入力と x が同じがそのまま比較するだけ
		System.out.println("-- Predicate.isEqual()");
		Predicate<String> predIsEqual = Predicate.isEqual("B"); // 入力が同じか見るだけ
		System.out.println("res : " + predIsEqual.test("A"));
		System.out.println("res : " + predIsEqual.test("B"));

		// Consumer<T(引数)>
		// @FunctionalInterface
		// public interface Consumer<T> {
		// // 抽象メソッド
		// void accept(T t);
		// // デフォルトメソッド
		// default Consumer<T> andThen(Consumer<? super T> after) { ... }
		// }
		System.out.println("\n・ Consumer<T>");
		Consumer<String> cons1 = str -> {
			String str2 = "String value = ";
			System.out.println(str2 + str);
		};
		System.out.print("Consumer : ");
		// 使うときは consumer.accept(x);
		cons1.accept("AABB");
		// default メソッド
		// A.andThen(B)
		// A の後に B を実行 (引数を使いまわし)
		System.out.println("-- A.andThen(B)");
		Consumer<Integer> consA = num -> System.out.println("res1 : " + num);
		Consumer<Integer> consB = num -> System.out.println("res2 : " + num);
		Consumer<Integer> consAndThen = consA.andThen(consB); // 同じ引数を使いまわし再実行
		consAndThen.accept(200);

		// Supplier<T(引数)>
		// ↓
		// @FunctionalInterface
		// public interface Supplier<T> {
		// // 抽象メソッド
		// T get();
		// }
		//
		System.out.println("\n・ Supplier<T>");
		Supplier<String> supp1 = () -> {
			String res = "response";
			return res;
		};
		// 使うときは supplier.get(x);
		System.out.println("Supplier : " + supp1.get());

	}

}
