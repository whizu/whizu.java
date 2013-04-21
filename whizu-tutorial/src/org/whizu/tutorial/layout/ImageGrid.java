package org.whizu.tutorial.layout;

import org.whizu.layout.GridLayout;
import org.whizu.server.App;
import org.whizu.ui.Application;
import org.whizu.ui.Image;
import org.whizu.ui.UI;

@App(uri="/whizu/imagegrid")
public class ImageGrid implements Application {

	@Override
	public String getTitle() {
		return "myGrid";
	}

	@Override
	public void init(UI ui) {
		GridLayout grid = new GridLayout(8);
		for (int i=0; i<64; i++) {
			Image image = ui.createImage("/images/img48.png");
			grid.add(image);
		}
		ui.getDocument().add(grid);
	}
}
