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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.whizu.annotation.App;
import org.whizu.jquery.ClickListener;
import org.whizu.jquery.mobile.Button;
import org.whizu.jquery.mobile.ButtonBuilder;
import org.whizu.jquery.mobile.Form;
import org.whizu.jquery.mobile.FormBuilder;
import org.whizu.jquery.mobile.HeaderBuilder;
import org.whizu.jquery.mobile.JQueryMobile;
import org.whizu.jquery.mobile.ListItem;
import org.whizu.jquery.mobile.ListView;
import org.whizu.jquery.mobile.ListViewBuilder;
import org.whizu.jquery.mobile.Page;
import org.whizu.jquery.mobile.Popup;
import org.whizu.jquery.mobile.PopupBuilder;
import org.whizu.jquery.mobile.Theme;

/**
 * @author Rudy D'hauwe
 */
@App("/whizu/tournament")
public class TournamentApp implements JQueryMobile {

	private static final Logger log = LoggerFactory.getLogger(TournamentApp.class);

	// private final ValueList<Player> playerList = new
	// ValueList<Player>(Player.class);
	private final ListView playerList = ListViewBuilder.create().build();

	private Popup popup;

	@Override
	public void onLoad(Page page) {
		// @formatter:off
		popup = addPlayerEvent();
		
		HeaderBuilder.create()
			.title("Tournament")
			.button("Add player")
			    .theme(Theme.E)
			    .onClickOpen(popup)
				.build()
			.build()
			.on(page);
		// @formatter:on

		page.add(playerList);
	}

	private Popup addPlayerEvent() {
		log.debug("TournamentApp.this::addPlayerEvent()");

		final PlayerVO model = new PlayerVO();

		Button submit = ButtonBuilder.createWithTitle("Create").build();

		// @formatter:off
		Form form = FormBuilder.create()
			.addText(model.name)
			.addDate(model.birthdate)
			.addButton(submit)
			.onSubmit(_addPlayer(model))
			.build();

		return PopupBuilder.createWithId("add-player-popup")
			.theme(Theme.B)
			.h3("Add a new player")
			.padding("10px")
			.add(form)
			.center()
			.build();
		// @formatter:on
	}

	private void addPlayer(PlayerVO player) {
		// validate
		// if not ok, show validation messages and don't add item and don't
		// close popup
		if (validate(player)) {
			log.debug("this::addPlayer()");
			ListItem item = new ListItem(player.name);
			item.p(player.birthdate);
			playerList.addItem(item);
			player.name.clear();
			popup.close();
			
		}
	}

	private boolean validate(PlayerVO player) {
		return (!player.name.get().equals(""));
	}

	/**
	 * Java 8 should obsolete this method by calling "this::addPlayer()".
	 */
	@Deprecated
	private ClickListener _addPlayer(final PlayerVO player) {
		return new ClickListener() {

			@Override
			public void click() {
				addPlayer(player);
			}
		};
	}
}
