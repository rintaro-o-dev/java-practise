package practise09;

import java.util.List;

public class Main {

	public static void main(String[] args) {

		// Colllection の default ・ static メソッド
		// List.of(v1,v2,...)
		System.out.println("-- List.of(v1,v2,...)");
		List<Integer> list1 = List.of(1,2,3);
		System.out.println("List" + list1);
		// List.forEach(consumer)
		System.out.println("\n-- List.forEach(consumer)");
		List.of("A","B","C").forEach(str -> System.out.print(str));

	}

}
