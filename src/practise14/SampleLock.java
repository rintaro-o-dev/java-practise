package practise14;

import java.util.concurrent.atomic.AtomicInteger;

public class SampleLock {

	AtomicInteger val = new AtomicInteger(0);

	public int add() {
		return val.addAndGet(1);
	}
	// val に　1 足す処理は原始性が担保される！
}
