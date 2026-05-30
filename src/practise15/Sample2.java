package practise15;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Sample2 implements Serializable {
	private String name;
	private int old;

	public Sample2(String name, int old) {
		this.name = name;
		this.old = old;
	}

	@Override
	public String toString() {
		return "Sample2 [ name = " + name + ", old = " + old + " ]";
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		System.out.println("write");
		out.writeObject(this.name);
		out.writeInt(this.old);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		System.out.println("read");
		this.name = (String) in.readObject();
		this.old = (int) in.readInt();
	}
}
