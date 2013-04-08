package org.whizu.jquery.mobile;

public enum Icon {

	ALERT("alert"), BACK("back"), BARS("bars"), CHECK("check"), DELETE("delete"), DOWN_ARROW("arrow-d"), EDIT("edit"), FORWARD(
			"forward"), GEAR("gear"), GRID("grid"), HOME("home"), INFO("info"), LEFT_ARROW("arrow-l"), MINUS("minus"), PLUS(
			"plus"), REFRESH("refresh"), RIGHT_ARROW("arrow-r"), SEARCH("search"), STAR("star"), UP_ARROW("arrow-u");

	private String value;

	Icon(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
