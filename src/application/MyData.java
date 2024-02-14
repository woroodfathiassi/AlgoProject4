package application;

public class MyData {
	private int index; // 1-9
	private char value; // x-o
	private boolean full;

	public MyData(int index, char value, boolean full) {
		super();
		this.index = index;
		this.value = value;
		this.full = full;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public boolean isFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
	}

}
