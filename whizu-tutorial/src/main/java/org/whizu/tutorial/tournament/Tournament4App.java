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
import org.whizu.jquery.mobile.HeaderBuilder;
import org.whizu.jquery.mobile.JQueryMobile;
import org.whizu.jquery.mobile.Page;
import org.whizu.jquery.mobile.list.ListViewBuilderNg;
import org.whizu.value.ValueList;

/**
 * @author Rudy D'hauwe
 */
@App("/whizu/tournament4")
public class Tournament4App implements JQueryMobile {

	private final ValueList<Player> playerList = new ValueList<Player>(Player.class);

	@Override
	public void onLoad(Page page) {
		initListData();

		// @formatter:off
		HeaderBuilder.create()
			.title("Tournament")
			.button("Add player")
				.onClick(playerList.addEvent())
				.build()
			.build()
			.on(page);
		// @formatter:on

		ListViewBuilderNg.createWith(playerList).ordered().build().on(page);
	}

	private void initListData() {
		Player player1 = new Player();
		player1.name.set("Mark");
		playerList.add(player1);
		Player player2 = new Player();
		player2.name.set("Rudy");
		playerList.add(player2);
	}
}
