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
import org.whizu.jquery.ClickListener;
import org.whizu.jquery.mobile.HeaderBuilder;
import org.whizu.jquery.mobile.JQueryMobile;
import org.whizu.jquery.mobile.Page;
import org.whizu.jquery.mobile.Theme;
import org.whizu.jquery.mobile.list.DefaultValueListControl;
import org.whizu.jquery.mobile.list.ListControl;
import org.whizu.jquery.mobile.list.ListViewBuilderNg;
import org.whizu.value.ValueList;

/**
 * @author Rudy D'hauwe
 */
@App("/whizu/tournament3")
public class Tournament3App implements JQueryMobile {

	private final ValueList<Player> playerList = new ValueList<Player>(Player.class);

	private final ListControl<Player> listControl = new DefaultValueListControl<Player>(playerList);

	@Override
	public void onLoad(Page page) {
		initListData();

		//EventHandler onItemClickHandler = listControl.editEvent();
		//ListManager manager = ...;
		//ListAdmin admin = ...;
		//ListControl control = ...; //playerList.getControl() //playerList.setControl()
		
		// @formatter:off
		ClickListener addPlayerEventHandler = listControl.addEvent();
		
		HeaderBuilder.create()
			.title("Tournament")
			.button("Add player")
			    .theme(Theme.E)
			    //.onClick(new AddPlayerAction(playerList)) //manager.add(), or, playerList.add() for default behavior
				//.onClick(new DefaultListSupport<Player>(playerList))
			    .onClick(addPlayerEventHandler)
			    .build()
			.build()
			.on(page);

		ListViewBuilderNg.createWith(listControl)  //or: createWith(manager) so next line is unnecessary
		
			//.onItemClick(new UpdatePlayerAction()) //manager.update()
		    //.onItemClick(onItemClickListener)
			.build()
			.on(page);
		// @formatter:on
	}

	private void initListData() {
		Player player1 = new Player();
		player1.name.set("Mark");
		playerList.add(player1);
		Player player2 = new Player();
		player2.name.set("Rudy");
		playerList.add(player2);
	}

	/*
	public OnItemClickListener<Player> onPlayerClickListener2() {
		return new OnItemClickListener<Player>() {

			private Popup popup2;

			public void click(Player model) {
				System.out.println("de speler is " + model.name.get());
				// final Button submitButton =
				// ButtonBuilder.createWithTitle("Update").onClick(closePopup(model)).build();
				final Button submitButton = ButtonBuilder.createWithTitle("Update")
						.onClick(call(this, "submitForm", model)).build();

				// @formatter:off
				final Form form = FormBuilder.create()
						.addText(model.name)
						.addDate(model.birthdate)
						.addButton(submitButton)
						.build();
				// @formatter:on

				popup2 = PopupBuilder.createWithId("update-player-popup" + model.name.get()).center()
						.h3("Messages.UPDATE_PLAYER").padding("10px").add(form).build();
				System.out.println("de speler is " + model.name.get());
				popup2.open();
			}

			public ClickListener closePopup(final Player model) {
				return new ClickListener() {

					public void click() {
						System.out.println("close popup " + model);
						popup2.close();
						playerList.update(model);
					}
				};
			}

			public void submitForm(final Player model) {
				System.out.println("close popup " + model);
				popup2.close();
				playerList.update(model);
			}
		};
	}

	private OnItemClickListener<Player> onPlayerClickListener() {

		return new OnItemClickListener<Player>() {

			@Override
			public void click(Player item) {
				System.out.println("list item clicked " + item.name);
				Popup popup = PopupBuilder.createWithId("edit-player-" + item.name.get()).h3(item.name.get()).build();
				popup.open();
			}
		};
	}
	*/

	/*
	private Popup addPlayerEvent() {
		log.debug("TournamentApp.this::addPlayerEvent()");

		final Player model = new Player();

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

	private void addPlayer(Player player) {
		log.debug("this::addPlayer()");
		Player item = new Player();
		item.refresh(player);
		playerList.add(item);
		player.clear();
		popup.close();
	}
	*/

	/**
	 * Java 8 should obsolete this method by calling "this::addPlayer()".
	 */
	/*
	@Deprecated
	private ClickListener _addPlayer(final Player player) {
		return new ClickListener() {

			@Override
			public void click() {
				addPlayer(player);
			}
		};
	}
	*/
}
