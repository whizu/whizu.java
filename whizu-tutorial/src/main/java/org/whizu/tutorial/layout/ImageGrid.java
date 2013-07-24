package org.whizu.tutorial.layout;

import org.whizu.annotation.App;
import org.whizu.layout.GridLayout;
import org.whizu.ui.ClickListener;
import org.whizu.ui.DocumentImpl;
import org.whizu.ui.Image;
import org.whizu.ui.ImageImpl;
import org.whizu.ui.LabelImpl;

@App("/whizu/imagegrid")
public class ImageGrid  {

	public void init() {
		final GridLayout grid = new GridLayout(8);
		for (int i=0; i<64; i++) {
			Image image = new ImageImpl("/images/img48.png");
			grid.add(image);
		}
		new DocumentImpl().add(grid);
		
		new DocumentImpl().add(new LabelImpl("reset").addClickListener(new ClickListener() {
			
			@Override
			public void click() {
				grid.empty();
				for (int i=0; i<64; i++) {
					Image image = new ImageImpl("/images/img48.png");
					grid.add(image);
				}
			}
		}));
	}
}
