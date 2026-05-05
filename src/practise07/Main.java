package practise07;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

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
		// チェーン
		System.out.println("res : " + funcA.andThen(funcB).andThen(funcC.compose(funcD)).apply(1000));

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
		// チェーン
		Predicate<Integer> predD = num -> num > 5;
		predA.and(predC.or(predD)).test(5);
		predA.and(predC.or(predD)).test(7);

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
		// チェーン
		consA.andThen(consB).accept(500);

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

		//
		// Bi 版
		// 引数を渡すインタフェース Function Predicate Consumer に関しては、
		// 引数が2つあるバージョン "Bi版" が存在する
		// 引数が2つに対応しているだけで、デフォルトメソッド等は変わらない
		System.out.println("\n・ Bi 版");
		// BiFunction<T,U,R>
		BiFunction<Integer,String,String> biFunc = (num, str) -> { // () 必須!
			num++;
			return num + str ;
		};
		System.out.println("BiFunction : " + biFunc.apply(100," 人"));
		// BiPredicate<T,U>
		BiPredicate<Integer,Integer> biPred = (num1, num2) -> { // () 必須!
			return num1.equals(num2);
		};
		System.out.println("BiPredicate : " + biPred.test(100,200));
		System.out.println("BiPredicate : " + biPred.test(100,100));
		// BiConsumer<T,U>
		BiConsumer<Integer,Integer> biCons = (num1, num2) -> { // () 必須!
			System.out.println("BiConsumer : " + num1 + " + " + num2 + " = "+ (num1 + num2));
		};
		biCons.accept(2, 3);

		//
		// UnaryOperator<T>
		// @FunctionalInterface
		// public interface UnaryOperator<T> extends Function<T, T> {
		//       // 抽象メソッドは Function と同じ
		// 		T apply(T t)
		// }
		// 引数と戻り値が "同じ型" のときに使う
		System.out.println("\n・ UnaryOperator<T>");
		UnaryOperator<Integer> uO1 = num -> num * 2;
		System.out.println("res : " + uO1.apply(10));
		System.out.println("res : " + uO1.andThen(uO1).apply(10));

		// Bi 版
		//　BinaryOperator<T>
		// 引数1、引数2、返り値 全て同じ型なので、型宣言時も "1種類だけ" でいい！！
		BinaryOperator<Integer> biUO1 = (num1,num2) -> num1 * num2;
		System.out.println("BiUnaryOperator : " + biUO1.apply(5, 10));
		System.out.println("BiUnaryOperator : " + biUO1.andThen(uO1).apply(5, 10)); // andthen(biUO1)はできない
		// BinaryOperator.andThen(biUO1) ができない理由：
		// after は Function<T,V> である必要があるが、
		// BinaryOperator は BiFunction<T,T,T> なので型が合わない。
		// BinaryOperator.andThen(UnaryOperator) は OK。( = Function<T,T>)
		// BinaryOperator.andThen(BinaryOperator) は NG。
	}

}
