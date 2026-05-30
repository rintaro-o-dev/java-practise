package practise12;

public class Main {

	public static void main(String[] args) {

		// Runnable 型の処理を先に作成してから Thread作成
		Runnable rA = () -> System.out.println("thread A run");
		Thread tA = new Thread(rA);
		tA.run();
		tA.start();

		// Thread 宣言時にラムダで処理を注入
		Thread tB = new Thread(() -> {
			System.out.println("thread B run");
		});
		tB.run();
		tB.run(); // run() は start() 前なら何度も使える
		tB.start();
		try {
			tB.join(); // tB.start(); が終了するまで待つ
		} catch (InterruptedException e) { // join 利用時はこの例外処理のため try - catch 必須
			System.out.println("InterruptedException の発生");
		}
		// Thread の start() でスレッド作成実行は "1回" しかできない
		System.out.println("start() の1回目呼び出し");
		try {
			tB.start();
		} catch (IllegalThreadStateException e) {
			System.out.println("start() の2回目呼び出しで IllegalThreadStateException が発生");
		}
		System.out.println("check point A");
		tB.run(); // start() 後は thread が TERMINATED になり、Runnable を内部的に削除する挙動があることから、 run() も使えなくなる
		System.out.println("check point B");

		Runnable rB2 = () -> System.out.println("thread B2 run");
		tB = new Thread(rB2); // 再利用のため宣言しなおし
		tB.run();
		tB.start();

		// 匿名クラスとして、オーバーライドアノテーションをつけて run() を実装
		Thread tC = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread C run 1");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
				System.out.println("thread C run 2");
			}
		});
		tC.start();
		System.out.println("pointA");
		try {
			tC.join();
		} catch (InterruptedException e) {
		}
		System.out.println("pointB");
		// thread C run 1
		// pointA
		// thread C run 2 (join でスレッド終了まで待機する)
		// pointB
		// になるはず（JVM のスケジューラの気分次第なので若干期待と違う）

		// lockObjを共有する
		final Object lockObj = new Object();
		final boolean[] ready = { false };
		Thread tD = new Thread(() -> {
			synchronized (lockObj) {
				System.out.println("thread D run 1");
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			synchronized (lockObj) {
				System.out.println("thread D run 2");
				ready[0] = true;
				lockObj.notify(); // ★準備できた合図(スレッドEを起こす)
			}
		});
		Thread tE = new Thread(() -> {
			try {
				synchronized (lockObj) {
					System.out.println("thread E run 1");
					while (!ready[0]) {
						lockObj.wait(); // ★合図が来るまで待つ（ロックを開放して待機）
					}
				}
			} catch (InterruptedException e) {
			}
			System.out.println("thread E run 2");
		});

		tD.start();

		tE.start();

		// thread D run 1
		// thread E run 1
		// thread D run 2
		// thread E run 2

	}
}
