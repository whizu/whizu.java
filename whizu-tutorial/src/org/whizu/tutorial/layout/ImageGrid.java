package org.whizu.tutorial.layout;

import org.whizu.annotations.App;
import org.whizu.layout.GridLayout;
import org.whizu.ui.Application;
import org.whizu.ui.ClickListener;
import org.whizu.ui.Image;
import org.whizu.ui.UI;

@App(uri="/whizu/imagegrid")
public class ImageGrid implements Application {

	@Override
	public String getTitle() {
		return "myGrid";
	}

	@Override
	public void init(final UI ui) {
		final GridLayout grid = new GridLayout(8);
		for (int i=0; i<64; i++) {
			Image image = ui.createImage("/images/img48.png");
			grid.add(image);
		}
		ui.getDocument().add(grid);
		
		ui.getDocument().add(ui.createLabel("reset").addClickListener(new ClickListener() {
			
			@Override
			public void click() {
				grid.empty();
				for (int i=0; i<64; i++) {
					Image image = ui.createImage("/images/img48.png");
					grid.add(image);
				}
			}
		}));
		
	}
}
