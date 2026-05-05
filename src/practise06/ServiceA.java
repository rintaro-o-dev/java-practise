package practise06;

public class ServiceA {
	private Algorithm1 logic;

	public void setLogic(Algorithm1 logic) {
		this.logic = logic;
	}

	public void doProcess(String name) {
		System.out.println("start");
		this.logic.perform(name);
		System.out.println("end");
	}

}
