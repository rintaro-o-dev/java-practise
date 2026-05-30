package practise14;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		// 大前提！
		// Thread が受け取れるのは Runnable だけ
		// Callable は 値を返すタスク なので Thread とは合わない
		// Runnable とは違って 返り値のあるタスク
		// supplier に似てるけど、決定的な違いは 例外を投げられるところ
		// ExecutorService のために作られたクラス
		Callable<Integer> cA = () -> 123;

		// パターン1
		ExecutorService poolA = Executors.newCachedThreadPool();
		Future<Integer> fA = poolA.submit(cA);
		try {
			System.out.println(fA.get()); // 123
		} catch (ExecutionException e) {
		}

		// パターン2
		ExecutorService poolB = Executors.newCachedThreadPool();
		Future<Integer> fB = poolB.submit(() -> 567); // return 567 の意味
		try {
			System.out.println(fB.get()); // 567
		} catch (ExecutionException e) {
		}

		// パターン3
		Callable<Integer> cB = () -> {
			throw new Exception();
		};
		ExecutorService poolC = Executors.newCachedThreadPool();
		Future<Integer> fC = poolC.submit(cB);
		try {
			System.out.println(fC.get()); // 例外
		} catch (ExecutionException e) {
			System.out.println("ExecutionException で例外キャッチ");
		}

		// 全スレッドの"終了待ち"
		CountDownLatch latch = new CountDownLatch(3);
		poolC.submit(() -> {
			System.out.println("A");
			latch.countDown();
		});
		poolC.submit(() -> {
			System.out.println("B");
			latch.countDown();
		});
		poolC.submit(() -> {
			System.out.println("C");
			latch.countDown();
		});
		latch.await(); // 全部終わるまで待つ
		System.out.println("全部終わった！");

		// 一定ラインまでの足並み揃え
		CyclicBarrier barrier = new CyclicBarrier(3);
		poolC.submit(() -> {
			try {
				System.out.println("D1");
				barrier.await(); // ここで足並みをそろえる
			} catch (InterruptedException e) {
			} catch (BrokenBarrierException e) {
			}
			System.out.println("D2");
		});

		poolC.submit(() -> {
			try {
				System.out.println("E1");
				barrier.await(); // ここで足並みをそろえる
			} catch (InterruptedException e) {
			} catch (BrokenBarrierException e) {
			}
			System.out.println("E2");
		});

		poolC.submit(() -> {
			try {
				System.out.println("F1");
				barrier.await(); // ここで足並みをそろえる
			} catch (InterruptedException e) {
			} catch (BrokenBarrierException e) {
			}
			System.out.println("F2");
		});

		// 複数 Callable の終了を待って結果を全部 Future で受け取る
		List<Callable<Integer>> tasksA = List.of(() -> 1, () -> 2, () -> 3);
		// 全タスクが終わるまでブロック
		List<Future<Integer>> results = poolA.invokeAll(tasksA);
		// 結果を取り出す
		for (Future<Integer> f : results) {
			try {
				System.out.println(f.get());
			} catch (InterruptedException e) {
			} catch (ExecutionException e) {
			}
		}

		// これはスレッセーフなリスト
		CopyOnWriteArrayList<Integer> lA = new CopyOnWriteArrayList<>();
		lA.add(1);
		lA.add(2);
		lA.add(3);

		// FlowAPI
		// Flow API は Java 標準の “リアクティブストリーム（非同期データの流れ）” を扱うための API。
		// Publisher（データを流す側）と Subscriber（受け取る側）で構成される。
		// Publisher	データを “発行” する側
		// Subscriber	データを “受け取る” 側
		// Subscription	Publisher と Subscriber の橋渡し（リクエスト数の制御）
		// Processor	Publisher でもあり Subscriber でもある（中間処理）

		// 1. Publisher   → Subscriber に onSubscribe() を呼ぶ（Subscriber はここで Subscription を受け取る。）
		// 2. Subscriber  →　Subscription に request(n) を送る｛「n 個だけ欲しい」と要求｝
		// 3. Publisher   → Subscriber に onNext() を n 回送る（Subscriber はデータを受け取る。）
		// 4. Publisher   → Subscriber に onComplete() を送る（データが終わった完了通知。）
	}

}
