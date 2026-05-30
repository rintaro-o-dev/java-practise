package practise13;

import java.util.concurrent.locks.ReentrantLock;

public class LockSample {

	// ReentrantLock は lock を手動で設定する
	// 必ず unlock を入れないと永遠にロックかかる
	private final ReentrantLock lock = new ReentrantLock();
	private int value = 0;

	public void increment() throws InterruptedException {
		lock.lock();
		// 排他処理
		try {
			value++;
			System.out.println(value);
		} finally {
			lock.unlock(); // ★必ず解放
		}
	}

	public void incrementTry() throws InterruptedException {
		if (lock.tryLock()) {
			// 排他処理
			try {
				Thread.sleep(1000);
				value++;
				System.out.println(value);
			} finally {
				lock.unlock(); // ★必ず解放
			}
		} else {
			System.out.println("ロックが取れませんでした");
		}
	}

}
