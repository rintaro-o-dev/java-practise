package practise09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {

		// Colllection の default ・ static メソッド
		System.out.println("・ Collection");

		// List.of(v1,v2,...)
		System.out.println("-- List.of(v1,v2,...)");
		List<Integer> list1 = List.of(1, 2, 3);
		System.out.println("List" + list1);

		// List.forEach(consumer)
		System.out.println("\n-- List.forEach(consumer)");
		List.of("A", "B", "C").forEach(str -> System.out.print(str));
		System.out.println("");

		// List.parallelStream()
		System.out.println("\n-- List.parallelStream()");
		// 並列ストリームを取得
		List<String> list2 = List.of("あ", "い", "う", "え", "お");
		Stream<String> stA = list2.parallelStream();
		System.out.print("\nlist2 : ");
		list2.forEach(System.out::print);
		System.out.print("\nlist2.parallelStream() : ");
		stA.forEach(System.out::print);

		// Stream
		System.out.println("\n・ stream");
		List<Integer> list3 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		System.out.println("list : " + list3.toString());
		System.out.println(" ↓ list.stream().filter(n -> n > 5).forEach(System.out::print)");
		list3.stream().filter(n -> n > 5).forEach(System.out::print);
		System.out.println("");
		System.out.println(" ↓ list.stream().filter(n -> n > 7).map(n -> n + 2).forEach(System.out::print)");
		list3.stream().filter(n -> n > 7).map(n -> n + 2).forEach(System.out::print);
		System.out.println("\n ↓ list 自体は更新されない (stream は使い切り)");
		System.out.println("list : " + list3.toString());
		System.out.println(" ↓ list2 = list.stream().filter(n -> n > 7).map(n -> n + 2).toList()");
		List<Integer> list4 = list3.stream().filter(n -> n > 7).map(n -> n + 2).toList();
		System.out.println("list2 : " + list4.toString());

		// findAny と findFirst
		System.out.println("\n-- findAny と findFirst");
		Object fA1 = list3.stream().findAny();
		System.out.println(fA1.toString());
		Object fA2 = list3.parallelStream().findAny();
		System.out.println(fA2.toString());
		Object fF1 = list3.stream().findFirst();
		System.out.println(fF1.toString());
		Object fF2 = list3.parallelStream().findFirst();
		System.out.println(fF2.toString());

		// sorted
		System.out.println("\n-- sorted");
		List<Integer> list5 = Arrays.asList(4, 87, 11, 6, 49, 3, 27, 4, 28, 55, 1);
		System.out.println("list : " + list5.toString());
		System.out.println(" ↓ list..stream().sorted()");
		list5.stream().sorted().forEach(x -> System.out.print(x + ", ")); // 自然順序
		System.out.println("");
		System.out.println(" ↓ list..stream().sorted(compare)");
		list5.stream().sorted((a, b) -> {
			if(a > b) return -1;
			if(a < b) return 1;
			return 0;
		}).forEach(x -> System.out.print(x + ", ")); // カスタム順序
		System.out.println("");

		// 中間処理と終端処理
		System.out.println("\n-- 中間処理と終端処理");
		// 中間処理では Stream<T> 型が返る
		Stream<Integer> sTA = list3.stream().filter(n -> n > 5);
		// 終端処理では T で返る
		List<Integer> listA = list3.stream().filter(n -> n > 5).map(n -> n + 2).toList();
		System.out.println("Stream : " + sTA.toString()); // stream は中身を持っていない
		System.out.println("List : " + listA.toString()); // list

		// reduce
		System.out.println(list3.stream().reduce((a, b) -> a + b)); // Optincal で返る
		System.out.println(list3.stream().reduce(0, (a, b) -> a + b)); // T で返る
		List<Integer> list6 = new ArrayList<>();
		System.out.println(list6.stream().reduce(0, (a, b) -> a + b)); // なくても 0 で返る
	}

}

//Stream<T> インターフェース（参照型）
//■ 中間操作（Intermediate Operations）
//→ Stream を返す。終端操作が来るまで実行されない（遅延評価）。
//├─ filter(Predicate<? super T> predicate)
//│    条件に合う要素だけ残す
//├─ map(Function<? super T, ? extends R> mapper)
//│    要素を別の型・値に変換する
//├─ flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
//│    Stream<Stream<T>> を 1 本に平らにする
//├─ distinct()
//│    equals() で重複を除去（順序は保つ）
//├─ sorted()
//│    自然順序でソート（Comparable）
//├─ sorted(Comparator<? super T> comparator)
//│    Comparator 指定でソート
//├─ limit(long maxSize)
//│    先頭から maxSize 件だけに制限
//├─ skip(long n)
//│    先頭から n 件スキップ
//├─ peek(Consumer<? super T> action)
//│    デバッグ用に途中で覗き見る（副作用注意）

//■ 終端操作（Terminal Operations）
//→ Stream を消費して「値」や「コレクション」を返す。再利用不可。
//├─ forEach(Consumer<? super T> action)
//│    各要素に対して処理を実行（順序保証なし）
//├─ forEachOrdered(Consumer<? super T> action)
//│    並列でも順序を保証して処理
//├─ toArray()
//│    Object[] を返す
//├─ toArray(IntFunction<A[]> generator)
//│    型付き配列を返す（例：String[]::new）
//├─ collect(Collector<? super T, A, R> collector)
//│    List / Set / Map などに変換（Collectors ユーティリティとセット）
//├─ toList()   // Java 16+
//│    不変 List を返す（Collectors.toList() とは別物）
//├─ reduce(...)  // オーバーロード複数
//│    畳み込み（合計・最大値・結合など）
//├─ min(Comparator<? super T> comparator)
//│   　Stream の中から「最小値」を返す（Optional<T>）
//├─ max(Comparator<? super T> comparator)
//│   　Stream の中から「最大値」を返す（Optional<T>）
//├─ count()
//│    要素数を long で返す
//├─ anyMatch(Predicate<? super T> predicate)
//│    1つでも条件を満たす要素があれば true
//├─ allMatch(Predicate<? super T> predicate)
//│    全ての要素が条件を満たすなら true
//├─ noneMatch(Predicate<? super T> predicate)
//│    1つも条件を満たさなければ true
//├─ findFirst()
//│    最初の要素を Optional<T> で返す
//└─ findAny()
//　    どれか 1 つを Optional<T> で返す

//IntStream インターフェース（プリミティブ int 用）
//■ 中間操作
//├─ filter(IntPredicate predicate)
//├─ map(IntUnaryOperator mapper)
//├─ flatMap(IntFunction<? extends IntStream> mapper)
//├─ distinct()
//├─ sorted()
//├─ limit(long maxSize)
//├─ skip(long n)
//├─ peek(IntConsumer action)

//■ 終端操作
//├─ forEach(IntConsumer action)
//├─ forEachOrdered(IntConsumer action)
//├─ toArray()
//├─ sum()
//├─ average()          // OptionalDouble
//├─ min()              // OptionalInt
//├─ max()              // OptionalInt
//├─ count()
//├─ anyMatch(IntPredicate predicate)
//├─ allMatch(IntPredicate predicate)
//├─ noneMatch(IntPredicate predicate)
//├─ findFirst()
//├─ findAny()
//├─ reduce(...)
//└─ boxed()            // Stream<Integer> に変換

//
//Stream<T>：
//filter / map / flatMap / distinct / sorted / limit / skip
//forEach / toList / collect / count / anyMatch / findFirst
//IntStream：
//map / filter / sum / average / toArray / boxed

// public interface Collection<E> extends Iterable<E> {
// --- 要素数
// int size();
// boolean isEmpty();
// --- 要素の存在確認
// boolean contains(Object o);
// --- イテレータ（for-each の元）
// Iterator<E> iterator();
// --- 配列化
// Object[] toArray();
// <T> T[] toArray(T[] a);
// --- 追加・削除
// boolean add(E e);
// boolean remove(Object o);
// --- 一括操作
// boolean containsAll(Collection<?> c);
// boolean addAll(Collection<? extends E> c);
// boolean removeAll(Collection<?> c);
// boolean retainAll(Collection<?> c);
// --- 全削除
// void clear();
// --- Stream 系
// default Stream<E> stream() { ... }
// default Stream<E> parallelStream() { ... }
// }