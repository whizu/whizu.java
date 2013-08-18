/*******************************************************************************
 * Copyright (c) 2013 Rudy D'hauwe @ Whizu
 * Licensed under the EUPL V.1.1
 *   
 * This Software is provided to You under the terms of the European 
 * Union Public License (the "EUPL") version 1.1 as published by the 
 * European Union. Any use of this Software, other than as authorized 
 * under this License is strictly prohibited (to the extent such use 
 * is covered by a right of the copyright holder of this Software).
 *
 * This Software is provided under the License on an "AS IS" basis and 
 * without warranties of any kind concerning the Software, including 
 * without limitation merchantability, fitness for a particular purpose, 
 * absence of defects or errors, accuracy, and non-infringement of 
 * intellectual property rights other than copyright. This disclaimer 
 * of warranty is an essential part of the License and a condition for 
 * the grant of any rights to this Software.
 *   
 * For more  details, see <http://joinup.ec.europa.eu/software/page/eupl>.
 *
 * Contributors:
 *     2013 - Rudy D'hauwe @ Whizu - initial API and implementation
 *******************************************************************************/
package org.whizu.tutorial.tournament;

import org.whizu.annotation.App;
import org.whizu.jquery.OnItemClickListener;
import org.whizu.jquery.mobile.Button;
import org.whizu.jquery.mobile.ButtonBuilder;
import org.whizu.jquery.mobile.DataIcon;
import org.whizu.jquery.mobile.HeaderBuilder;
import org.whizu.jquery.mobile.JQueryMobile;
import org.whizu.jquery.mobile.ListView;
import org.whizu.jquery.mobile.Page;
import org.whizu.jquery.mobile.Theme;
import org.whizu.jquery.mobile.list.ListViewBuilderNg;
import org.whizu.layout.GridLayout;
import org.whizu.layout.Layout;
import org.whizu.util.Callback;
import org.whizu.value.ValueList;

/**
 * @author Rudy D'hauwe
 */
@App("/whizu/tournament4")
public class Tournament4App implements JQueryMobile {

	private final ValueList<PlayerVO> playerList = new ValueList<PlayerVO>(PlayerVO.class);

	@Override
	public void onLoad(Page page) {
		initListData();

		Button b = ButtonBuilder.createWithTitle("my button").inline().onClickOpen(page).theme(Theme.E).build();
//		
		Button c = ButtonBuilder.createWithTitle("my c").inline().onClick(playerList.addEvent()).build();
//		
		// @formatter:off
		HeaderBuilder.create()
			.title("Tournament")
			.button(b)
			.button(c)
			.button("Add player")
				.onClick(playerList.addEvent())
				.build()
			.build()
			.on(page);

		Button addPlayer = ButtonBuilder.createWithTitle("Add player")
				.onClick(playerList.addEvent())
				.inline()
				.build();
		
		ListView listView = ListViewBuilderNg.createWith(playerList)
				.ordered()
				.splitButtonIcon(DataIcon.DELETE)
				.splitButtonTheme(Theme.B)
				.onSplitButtonClick(new OnItemClickListener<PlayerVO>() {
					
					@Override
					public void click(PlayerVO item, Callback callback) {
						System.out.println("Split button click on " + item);
						playerList.remove(item);
					}
				})
				.build();
		// @formatter:on
		
		Layout layout = new GridLayout("180px", "99%");
		layout.add(addPlayer);
		layout.add(listView);
		page.add(layout);
		//.on(page);
	}

	private void initListData() {
		PlayerVO player1 = new PlayerVO();
		player1.name.set("Mark");
		playerList.add(player1);
		PlayerVO player2 = new PlayerVO();
		player2.name.set("Rudy");
		playerList.add(player2);
	}
}
