package practise13;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		LockSample lS1 = new LockSample();
		Thread tA = new Thread(() -> {
			System.out.println("tA");
			try {
				lS1.increment();
			} catch (InterruptedException e) {
			}
		});
		Thread tB = new Thread(() -> {
			System.out.println("tB");
			try {
				lS1.increment();
			} catch (InterruptedException e) {
			}
		});
		tA.start();
		tB.start();

		tA = new Thread(() -> {
			System.out.println("tA");
			try {
				lS1.incrementTry();
			} catch (InterruptedException e) {
			}
		});
		tB = new Thread(() -> {
			System.out.println("tB");
			try {
				lS1.incrementTry();
			} catch (InterruptedException e) {
			}
		});

		tA.start();
		tB.start();

		// Executors クラスのスタティックメソッドでスレッドのプールを作れる
		// 作ったプールを、管理インタフェースに突っ込むことでスレッドのプール管理ができるようになる
		// ■ スレッドプール管理
		// ExecutorService には Runnable を渡す。
		Runnable rA = () -> {
			try {
				Thread.sleep(1000);
				System.out.println("rA");
			} catch (InterruptedException e) {
			}
		};
		ExecutorService poolA = Executors.newFixedThreadPool(2); // 2スレッド分のプール
		poolA.submit(() -> System.out.println("task1"));
		poolA.submit(() -> System.out.println("task2"));
		poolA.submit(() -> System.out.println("task3")); // 3つめはキューにたまる

		ExecutorService poolB = Executors.newCachedThreadPool();
		poolB.submit(rA); // 時間経過で勝手に消える (60 秒使われなかったらきえる)

		ExecutorService poolC = Executors.newSingleThreadExecutor(); // 一つのスレッドで順に処理する (順番保守)
		poolC.submit(() -> System.out.println("task1"));
		poolC.submit(() -> System.out.println("task2"));
		poolC.submit(() -> System.out.println("task3"));

		// ■ スレッドプール管理の上位互換
		// ScheduleedWxecuterService
		// 指定時間後に 1 回だけ実行
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.schedule(() -> System.out.println("1回だけ"), 3, TimeUnit.SECONDS);

		// 一定間隔で “ズレなく” 実行（開始時刻基準）
		scheduler.scheduleAtFixedRate(() -> System.out.println("固定間隔"), 1, // 初回遅延
				2, // 周期
				TimeUnit.SECONDS);
		// 5秒後に止める例

		ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(() -> System.out.println("固定間隔 future"), 1, 2,
				TimeUnit.SECONDS);
		Thread.sleep(5000); // mainスレッドを止める
		future.cancel(true);

		// “前のタスクが終わってから” 一定時間後に実行
		scheduler.scheduleWithFixedDelay(() -> System.out.println("遅延基準"), 1, 3, TimeUnit.SECONDS);

		Thread.sleep(10000);
		scheduler.shutdown();

		ScheduledExecutorService scheduler2 = Executors.newScheduledThreadPool(1);
		// Future の get で戻り値が返る（終了するまで待つ）
		Runnable rB = () -> System.out.println("rB");
		Future<?> futureB = scheduler2.schedule(rB, 3, TimeUnit.SECONDS);
		Object resA = null;
		try {
			resA = futureB.get();
		} catch (ExecutionException e) {
		}
		System.out.println(resA);

		// ExecutorService executor
		// Future<T> f = executor.submit(callable);
		// Future<?> f = executor.submit(runnable);
		// Future<T> f = executor.submit(runnable, result);
		// ScheduledExecutorService scheduler
		// Future<?> f = scheduler.schedule(runnable, delay, unit);
		// Future<T> f = scheduler.schedule(callable, delay, unit);
		// ScheduledFuture<?> f = scheduler.scheduleAtFixedRate(...);

		ExecutorService poolD = Executors.newCachedThreadPool();
		Future<Integer> futureC = poolD.submit(() -> {
		}, 0); // executor.submit(runnable, result); の書き方
		try {
			resA = futureC.get();
		} catch (ExecutionException e) {
		}
		System.out.println(resA);

	}

}
