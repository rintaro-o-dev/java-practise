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
		// - ArrayList      : 挿入順、配列ベース、ランダムアクセス高速
		// - LinkedList     : 挿入順、先頭と末尾の操作が高速
		// - Vector         : 古い同期版 ArrayList（非推奨）
		//
		// java.util.Set (順序なし、重複NG)
		// - HashSet        : 順序保証なし、ハッシュで高速検索
		// - LinkedHashSet  : 挿入順、HashSet より少し遅いが順序が安定
		// - TreeSet        : 自然順序でソート、要 Comparable、内部は Red-Black Tree
		//
		// java.util.Queue (FIFO : 先入先出)
		// - PriorityQueue  : 優先度順（FIFO ではない）、内部はヒープ構造
		// - ArrayDeque     : 高速な両端キュー（Deque）、スタックにもキューにも使える
		//
		List<Integer> a = new ArrayList<>();
		List<Integer> b = new LinkedList<>();
		List<Integer> c = new Vector<>();
		Set<Integer> d = new HashSet<>();
		Set<Integer> e = new LinkedHashSet<>();
		Set<Integer> f = new TreeSet<>();
		Queue<Integer> g = new PriorityQueue<>();
		Queue<Integer> h = new ArrayDeque<>();

		// コレクションのaddパターン1
		Collection<Integer>[] collections1 = new Collection[] {a,b,c,d,e,f,g,h};
		for (Collection<Integer> cole : collections1) {
			for (int i=0; i<3; i++) {
				cole.add(i);
			}
		}
		System.out.println(a + "\n" + b + "\n" + c + "\n" + d + "\n" + e + "\n" + f + "\n" + g + "\n" + h);

		// コレクションのaddパターン2 (モダン)
		List<Collection<Integer>> collections2 = new ArrayList<>();
		for (Collection<Integer> cole : collections1) {
			collections2.add(cole);
		}
		for (Collection<Integer> cole : collections2) {
			for (int x=3; x<6; x++) {
				cole.add(x);
			}
		}
		System.out.println(a + "\n" + b + "\n" + c + "\n" + d + "\n" + e + "\n" + f + "\n" + g + "\n" + h);

		// Collection の継承系ではない
		// java.util.Map
		// - HashMap
		// - LinkedHashMap
		// - TreeMap
		// - Hashtable
		//
		Map<String,Integer> i = new HashMap<>();
		Map<String,Integer> j = new LinkedHashMap<>();
		Map<String,Integer> k = new TreeMap<>();
		Map<String,Integer> l = new Hashtable<>();
		int index = 0;
		List<Map<String,Integer>> maps1 = new ArrayList<>();
		maps1.add(i);
		maps1.add(j);
		maps1.add(k);
		maps1.add(l);
		for (Map<String,Integer> map : maps1) {
			index = 0;
			for (int y=3; y<6; y++) {
				index ++;
				map.put("key" + index, y);
			}
		}
		System.out.println(i + "\n" + j + "\n" + k + "\n" + l);

	}
}
