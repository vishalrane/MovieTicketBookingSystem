package org.cog.model;

public class Seat {
	private boolean visible;
	private String name;

	public Seat(String name) {
		visible = true;
		this.name = name;
	}

	public void setVisible(boolean b) {
		visible = b;
	}

	public String getName() {
		return name;
	}

	public boolean getVisible() {
		return visible;
	}

	public String toString() {
		if (visible)
			return name;
		else
			return "X";
	}
}
