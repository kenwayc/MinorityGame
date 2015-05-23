package MG;

public class Strategy {
	private int vscore; // virtual score
	private String name; // identify strategy
	private boolean[] content = new boolean[4]; // memory size of 2

	public boolean[] getContent() {
		return content;
	}

	public void setContent(boolean[] content) {
		this.content = content;
	}

	// construct a random strategy
	public Strategy(String name) {
		this.name = name;
		this.vscore = 0;
		for (int i = 0; i < 4; i++) {
			content[i] = Math.random() < 0.5;
		}
	}
	
	public int getVscore() {
		return vscore;
	}

	public void setVscore(int vscore) {
		this.vscore = vscore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {		
		return "Strategy" + name + " is " + content[0]+content[1]+content[2]+content[3];
	}

}
