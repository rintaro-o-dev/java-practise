package Practise08;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {

	public static void main(String[] args) {

		// java.util.Optional
		System.out.println("・ Optional Practise");

		// static メソッド
		// Optional.empty()
		// Optional.empty を生成
		Optional<String> opEmpty1 = Optional.empty();
		System.out.println("-- Optional.empty()\n res : " + opEmpty1);
		// Optional.of(value)
		// value を生成 (型はここで宣言しない)
		// null を許容しない !! (実行時エラー)
		Optional<String> opOf1 = Optional.of("ABC");
		System.out.println("\n-- Optional.of(value)\n res : " + opOf1);
		try {
			opOf1 = Optional.of(null);
		} catch (NullPointerException e) {
			System.out.println(" nullを許容しない : NullPointerException が発生");
		}
		// Optional.ofNullable(value)
		// null を許容し、nullだったら empty を返す
		Optional<String> opOfNullable1 = Optional.ofNullable("ABC");
		System.out.println("\n-- Optional.ofNullable(value)\n res(\"ABC\") : " + opOfNullable1);
		Optional<String> opOfNullable2 = Optional.ofNullable(null);
		System.out.println(" res(null) : " + opOfNullable2);

		// インスタンスメソッド
		// A.getT();
		// 値の取得 (非推奨 : 値がなければ除外)
		Optional<String> opA = Optional.ofNullable("ABC");
		Optional<String> opB = Optional.empty();
		Optional<String> opC = Optional.ofNullable(null);
		System.out.println("\n-- A.get()\n res(\"ABC\") : " + opA.get());
		try {
			System.out.println(" res(\"empty\") : " + opB.get()); // empty は許容しない
		} catch (NoSuchElementException e) {
			System.out.println(" empty を許容しない : NoSuchElementException が発生");
		}
		try {
			System.out.println(" res(\"null\") : " + opC.get()); // null は許容しない
		} catch (NoSuchElementException e) {
			System.out.println(" null を許容しない : NoSuchElementException が発生");
		}
		// A.isPresent()
		// 値があるか 真偽値
		System.out.println("\n-- A.isPresent()\n res(\"ABC\") : " + opA.isPresent());
		System.out.println(" res(\"empty\") : " + opB.isPresent());
		System.out.println(" res(\"null\") : " + opC.isPresent()); // null も許容
		// A.isEmpty()
		// 値がないか 真偽値
		System.out.println("\n-- A.isEmpty()\n res(\"ABC\") : " + opA.isEmpty());
		System.out.println(" res(\"empty\") : " + opB.isEmpty());
		System.out.println(" res(\"null\") : " + opC.isEmpty()); // null も許容

		// A.filter(predicate)
		// predicate に真偽値判別の実装を入れる
		// Optional.empty は "空の Optional オブジェクト"
		// filter は値があれば predicate を実行し、false なら empty にする
		// empty に filter をかけても empty のまま
		Optional<Integer> opD = Optional.ofNullable(3);
		Optional<Integer> opE = Optional.ofNullable(10);
		Optional<Integer> opF = Optional.empty();
		Optional<Integer> opG = Optional.ofNullable(null);
		System.out.println("\n-- A.filter(predicate)\n res(\"3\") : " + opD.filter(n -> n > 5));
		System.out.println(" res(\"10\") : " + opE.filter(n -> n > 5));
		System.out.println(" res(\"empty\") : " + opF.filter(n -> n > 5));
		System.out.println(" res(\"null\") : " + opG.filter(n -> n > 5)); // null も許容
		// A.map(function)
		// function に実行処理を入れる (値変換とか)
		// 自動的に Function<Integer, ?> になる
		System.out.println("\n-- A.map(function)\n res(\"3\") : " + opD.map(n -> n * 2));
		System.out.println(" res(\"10\") : " + opE.filter(n -> n > 0).map(n -> n * 2)); // filter とチェーン
		System.out.println(" res(\"10\") : " + opE.filter(n -> n < 0).map(n -> n * 2)); // filter とチェーン
		System.out.println(" res(\"empty\") : " + opF.map(n -> n * 2));
		System.out.println(" res(\"null\") : " + opG.map(n -> n * 2)); // null も許容
		// A.flatMap(function)
		// Optional<Optional<Address>> こうならずに、Optional<Address> こう "平坦化" してくれる
		Sample1 s1 = new Sample1(22);
		Sample1 s2 = new Sample1(null);
		Optional<Sample1> opH = Optional.ofNullable(s1);
		Optional<Sample1> opI = Optional.ofNullable(s2);
		System.out.println("\n-- A.flatMap(function)\n res(map \"Optional.22\") : " + opH.map(n -> n.getOp()));
		// ↑ Optional[Optional.22] になってしまう
		System.out.println(" res(flatmap \"Optional.22\") : " + opH.flatMap(n -> n.getOp()));
		// ↑ Optional.22 に平坦化される！
		System.out.println(" res(\"null\") : " + opI.flatMap(n -> n.getOp()));
		// ↑ flatMap 内で null になっても NPE にはならず empty 化

		// A.orElse(other)
		// 値がない Optional.empty となった場合に (other) を返す
		Supplier<Integer> supp1 = () -> 555;
		System.out.println("\n-- A.orElse(other)\n res(\"3\") : " + opD.filter(n -> n > 5).orElse(403));
		System.out.println(" res(\"3\") : " + opD.filter(n -> n > 5).orElse(supp1.get()));
		System.out.println(" res(\"10\") : " + opE.filter(n -> n > 5).orElse(403));
		// A.orElseGet(supplier)
		// 値がない Optional.empty となった場合に (supplier) を返す
		// Supplier を返せる！！
		System.out.println("\n-- A.orElseGet(supplier)\n res(\"3\") : " + opD.filter(n -> n > 5).orElseGet(supp1));
		System.out.println(" res(\"3\") : " + opD.filter(n -> n > 5).orElseGet(() -> 777));
		System.out.println(" res(\"10\") : " + opE.filter(n -> n > 5).orElseGet(supp1));
		// A.orElseThrow(supplier)
		// 値がない Optional.empty となった場合に (supplier) を返す
		// supplier で "エラー" が返せる！！
		System.out.println("\n-- A.orElseThrow(supplier)");
		Supplier<Exception> supp2 = () -> new IllegalStateException("値がありません");
		try {
			System.out.println(" res(\"3\") : "
					+ opD.filter(n -> n > 5).orElseThrow(() -> new IllegalStateException("value is empty")));

		} catch (IllegalStateException e) {
			System.out.println(" error : " + e);
		}
		try {
			System.out.println(" res(\"3\") : " + opD.filter(n -> n > 5).orElseThrow(supp2));
		} catch (IllegalStateException e) {
			System.out.println(" error(IllegalStateException) : " + e); // 実際に発生しているのはこっちなので、こっちが受け止め
		} catch (Exception e) {
			System.out.println(" error(Exception) : " + e); // supplier の保障として書く必要がある
		}
		try {
			System.out.println(" res(\"10\") : " + opE.filter(n -> n > 5).orElseThrow(supp2));
		} catch (Exception e) {
			System.out.println("error : " + e);
		}

		// A.ifPresent(consumer)
		// 値があれば consumer を実行
		// 値がないときは何もしない
		Consumer<Integer> cons1 = num -> System.out.println("num is " + num);
		System.out.print("\n-- A.ifPresent(consumer)\n res(\"3\") : ");
		opD.ifPresent(n -> System.out.println(n));
		System.out.print("res(\"3\") : ");
		opD.ifPresent(cons1);
		System.out.println(" res(empty) : ");
		opF.ifPresent(n -> System.out.println(n));

		// A.ifPresentOrElse(consumer,emptyAction)
		// 値があれば consumer , なければ emptyAction を実行
		Runnable emptyAction = () -> System.out.println("value is empty");
		System.out.print("\n-- A.ifPresentOrElse(consumer, emptyAction)\n res(\"3\") : ");
		opD.ifPresentOrElse(n -> System.out.println(n), () -> System.out.println("value is empty"));
		System.out.print("res(\"3\") : ");
		opD.ifPresentOrElse(cons1, emptyAction);
		System.out.print(" res(empty) : ");
		opF.ifPresentOrElse(n -> System.out.println(n), () -> System.out.println("value is empty"));

	}

}

// ■ Optional インターフェース定義ざっくり
// public final class Optional<T> {
//     値を保持するフィールド（null の可能性あり）
//    private final T value;
// --- 値の生成 ---
//    public static <T> Optional<T> empty();
//    public static <T> Optional<T> of(T value);
//    public static <T> Optional<T> ofNullable(T value);
// --- 値の取得 ---
//    public T get();                     // 非推奨（値がなければ例外）
//    public boolean isPresent();         // 値があるか？
//    public boolean isEmpty();           // 値がないか？
// --- 値の操作（Stream と同じ思想） ---
//    public Optional<T> filter(Predicate<? super T> predicate);
//    public <U> Optional<U> map(Function<? super T, ? extends U> mapper);
//    public <U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper);
// --- 値がない場合の処理 ---
//    public T orElse(T other);
//    public T orElseGet(Supplier<? extends T> supplier);
//     public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier);
// --- その他 ---
//    public void ifPresent(Consumer<? super T> action);
//    public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction);
// }