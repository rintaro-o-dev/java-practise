package practise05;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.Queue;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {

		// Collection の継承系
		// java.util.List (順序あり、重複OK)
		// - ArrayList : 挿入順、配列ベース、ランダムアクセス高速
		// - LinkedList : 挿入順、先頭と末尾の操作が高速
		// - Vector : 古い同期版 ArrayList（非推奨）
		//
		// java.util.Set (順序なし、重複NG)
		// - HashSet : 順序保証なし、ハッシュで高速検索
		// - LinkedHashSet : 挿入順、HashSet より少し遅いが順序が安定
		// - TreeSet : 自然順序でソート、要 Comparable、内部は Red-Black Tree
		//
		// java.util.Queue (FIFO : 先入先出)
		// - PriorityQueue : 優先度順（FIFO ではない）、内部はヒープ構造
		// - ArrayDeque : 高速な両端キュー（Deque）、スタックにもキューにも使える
		//
		List<Integer> aa = new ArrayList<>();
		List<Integer> bb = new LinkedList<>();
		List<Integer> cc = new Vector<>();
		Set<Integer> dd = new HashSet<>();
		Set<Integer> ee = new LinkedHashSet<>();
		Set<Integer> ff = new TreeSet<>();
		Queue<Integer> gg = new PriorityQueue<>();
		Queue<Integer> hh = new ArrayDeque<>();

		// コレクションのaddパターン1
		Collection<Integer>[] collections1 = new Collection[] { aa, bb, cc, dd, ee, ff, gg, hh };
		for (Collection<Integer> cole : collections1) {
			for (int i = 0; i < 3; i++) {
				cole.add(i);
			}
		}
		System.out.println(aa + "\n" + bb + "\n" + cc + "\n" + dd + "\n" + ee + "\n" + ff + "\n" + gg + "\n" + hh);

		// コレクションのaddパターン2 (モダン)
		List<Collection<Integer>> collections2 = new ArrayList<>();
		for (Collection<Integer> cole : collections1) {
			collections2.add(cole);
		}
		for (Collection<Integer> cole : collections2) {
			for (int x = 3; x < 6; x++) {
				cole.add(x);
			}
		}
		System.out.println(aa + "\n" + bb + "\n" + cc + "\n" + dd + "\n" + ee + "\n" + ff + "\n" + gg + "\n" + hh);

		// Collection の継承系ではない
		// java.util.Map
		// - HashMap
		// - LinkedHashMap
		// - TreeMap
		// - Hashtable
		//
		Map<String, Integer> i = new HashMap<>();
		Map<String, Integer> j = new LinkedHashMap<>();
		Map<String, Integer> k = new TreeMap<>();
		Map<String, Integer> l = new Hashtable<>();
		int index = 0;
		List<Map<String, Integer>> maps1 = new ArrayList<>();
		maps1.add(i);
		maps1.add(j);
		maps1.add(k);
		maps1.add(l);
		for (Map<String, Integer> map : maps1) {
			index = 0;
			for (int y = 3; y < 6; y++) {
				index++;
				map.put("key" + index, y);
			}
		}
		System.out.println(i + "\n" + j + "\n" + k + "\n" + l);

		// put と entry
		System.out.println("\n・ Map に put で書き込み (可変 : mutable)");
		Map<Integer, String> mapMutable1 = new HashMap<>();
		// put で入れたデータは "可変 (mutable)"
		mapMutable1.put(1, "あ");
		System.out.println("map.get(key1) : " + mapMutable1.get(1));
		System.out.println("\n-- map.put(key1,value) -> value を更新");
		System.out.println("map.get(key1) : " + mapMutable1.get(1) + "\n ↓");
		mapMutable1.put(1, "い");
		System.out.println("map.get(key1) : " + mapMutable1.get(1));

		System.out.println("\n-- map.remove(key1) で削除");
		System.out.println("map.get(key1) : " + mapMutable1.get(1) + "\n ↓");
		mapMutable1.remove(1);
		System.out.println("map.get(key1) : " + mapMutable1.get(1));

		System.out.println("\n-- map.clear() で全削除");
		mapMutable1.put(1, "あ");
		mapMutable1.put(2, "い");
		mapMutable1.put(3, "う");
		System.out.println("map : " + mapMutable1 + "\n ↓");
		mapMutable1.clear();
		System.out.println("map : " + mapMutable1);

		System.out.println("\n-- map.putIfAbsent(key, value) で key がまだなければ追加");
		System.out.println("map.get(key1) : " + mapMutable1.get(1) + "\n ↓ map.putIfAbsent(1, \"あ\")");
		mapMutable1.putIfAbsent(1, "あ");
		System.out.println("map.get(key1) : " + mapMutable1.get(1) + "\n ↓ map.putIfAbsent(1, \"い\")");
		mapMutable1.putIfAbsent(1, "い");
		System.out.println("map.get(key1) : " + mapMutable1.get(1));

		System.out.println("\n-- map.replace(key, value) で key がある時だけ更新(更新専用)");
		mapMutable1.put(2, "い");
		System.out.println("map : " + mapMutable1 + "\n ↓ map.replace(2, \"う\")");
		mapMutable1.replace(2, "う");
		System.out.println("map : " + mapMutable1 + "\n ↓ map.replace(3, \"え\")");
		mapMutable1.replace(3, "え");
		System.out.println("map : " + mapMutable1);

		System.out.println("\n-- map.replace(key, oldValue, newValue) で key,oldValue がある時だけ更新(条件付き更新)");
		System.out.println("map : " + mapMutable1 + "\n ↓ map.replace(2, \"う\",\"え\")");
		mapMutable1.replace(2, "う", "え");
		System.out.println("map : " + mapMutable1 + "\n ↓ map.replace(2, \"う\",\"お\")");
		mapMutable1.replace(2, "う", "お");
		System.out.println("map : " + mapMutable1);

		System.out.println("\n-- map.remove(key, value) で key,value の組み合わせがある時だけ削除(条件付き削除)");
		System.out.println("map : " + mapMutable1 + "\n ↓ map.remove(2, \"え\")");
		mapMutable1.remove(2, "え");
		System.out.println("map : " + mapMutable1 + "\n ↓ map.remove(1, \"い\")");
		mapMutable1.remove(1, "い");
		System.out.println("map : " + mapMutable1);

		System.out.println("\n-- 存在確認");
		System.out.println("map : " + mapMutable1);
		System.out.println("map.containsKey(1) (キーがある) : " + mapMutable1.containsKey(1)
				+ "\nmap.containsKey(2) (キーがない) : " + mapMutable1.containsKey(2)
				+ "\nmap.containsValue(\"あ\") (値がある) : " + mapMutable1.containsValue("あ")
				+ "\nmap.containsValue(\"お\") (値がない) : " + mapMutable1.containsValue("お"));

		System.out.println("\n-- 各全件取得操作");
		mapMutable1.put(2, "い");
		mapMutable1.put(3, "う");
		System.out.println("map : " + mapMutable1);
		System.out.println("map.entrySet() (キーと値を全取得) : " + mapMutable1.entrySet() // バラバラ
				+ "\nmap.values() (値を全取得) : " + mapMutable1.values() // バラバラ
				+ "\nmap.keySet() (キーを全取得) : " + mapMutable1.keySet()); // バラバラ

		System.out.println("\n・ Map.entry() でレコード作成 (不変 : immutable)");
		var mE1 = Map.entry(1, "A"); // entry を作成するための static なメソッド！
		var mE2 = Map.entry(2, "B");
		var mE3 = Map.entry(3, "C");
		System.out.println("entry1 : " + mE1 + "\nentry2 : " + mE2 + "\nentry3 : " + mE3);
		System.out.println(" ↓ Map.ofEntries(entry1, entry3) でエントリーをくみ上げて構築 (不変 : immutable)");
		// Map.ofEntries() で java.util.ImmutableCollections$MapN (JDK の内部クラス) が作られる！
		// -> 順序保証なし、配列ベース
		Map<Integer, String> mapImmutable1 = Map.ofEntries(mE1, mE3);
		System.out.println("map.entrySet() (キーと値を全取得) : " + mapImmutable1.entrySet()); // バラバラに出力
		System.out.println(" ↓ Map.ofEntries(entry1, entry2, entry3) でエントリーをくみ上げて作り直しはできる");
		mapImmutable1 = Map.ofEntries(mE1, mE2, mE3);
		System.out.println("map.entrySet() : " + mapImmutable1.entrySet() // バラバラに出力
				+ "\nmap.values() : " + mapImmutable1.values() // バラバラに出力
				+ "\nmap.keySet() : " + mapImmutable1.keySet() // バラバラに出力
				+ "\nmap.get(1) : " + mapImmutable1.get(1) + "\n"); // バラバラに出力

		// immutable なので put しようとするとエラー
		System.out.println("-- immutable な map では読み取り専用 -> OK : get(x)  NG : put(),remove(),clear() ");
		try {
			mapMutable1.put(2, "い");
			mapImmutable1.put(1, "B");
		} catch (UnsupportedOperationException e) {
			System.out.println("UnsupportedOperationException が発生");
		} catch (Exception e) {

		}

		// Map.entry(x,y) は entry を作る static メソッド
		// Map.Entry<x ,y> は entry 1レコードを扱う型
		// map.entrySet() で全ての entry を取得 (ミソ)
		// Map.Entry には getKey(), getValue() ,setValue() がある
		System.out.println("\n-- Map.entry(x, y) と Map.Entry<x, y>");
		for (Map.Entry<Integer,String> entry : mapImmutable1.entrySet()) {
			System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
		}

		// immutable な Map では entry.setValue(y) はできない
		System.out.println("\n-- immutable な Map では entry.setValue(y) はエラー");
		try {
			for (Map.Entry<Integer,String> entry : mapImmutable1.entrySet()) {
				System.out.println("entry.setValue(\"Z\")");
				entry.setValue("Z");
				System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
			}
		} catch (UnsupportedOperationException e) {
			System.out.println("UnsupportedOperationException が発生");
		} catch (Exception e) {
		}

		// mutable な Map なら entry.setValue(y) ができる
		System.out.println("\n-- mutable な Map では entry.setValue(y) ができる");
		for (Map.Entry<Integer,String> entry : mapMutable1.entrySet()) {
			System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
		}
		System.out.println("　↓ entry.setValue(\"Z\")");
		for (Map.Entry<Integer,String> entry : mapMutable1.entrySet()) {
			entry.setValue("Z");
			System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
		}


	}
}
