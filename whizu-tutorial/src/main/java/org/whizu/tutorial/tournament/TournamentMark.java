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
 * For more details, see http://joinup.ec.europa.eu/software/page/eupl.
 *
 * Contributors:
 *     2013 - Rudy D'hauwe @ Whizu - initial API and implementation
 *******************************************************************************/
package org.whizu.tutorial.tournament;

import java.util.Set;
import java.util.TreeSet;

import org.slf4j.LoggerFactory;
import org.whizu.annotation.App;
import org.whizu.jquery.ClickListener;
import org.whizu.jquery.mobile.Button;
import org.whizu.jquery.mobile.ButtonBuilder;
import org.whizu.jquery.mobile.Header;
import org.whizu.jquery.mobile.HeaderBuilder;
import org.whizu.jquery.mobile.JQueryMobile;
import org.whizu.jquery.mobile.ListView;
import org.whizu.jquery.mobile.Page;
import org.whizu.jquery.mobile.PageBuilder;
import org.whizu.jquery.mobile.Panel;
import org.whizu.jquery.mobile.PanelBuilder;
import org.whizu.jquery.mobile.list.ListViewBuilderNg;
import org.whizu.layout.GridLayout;
import org.whizu.layout.HorizontalLayout;
import org.whizu.layout.Layout;
import org.whizu.layout.VerticalLayout;
import org.whizu.value.ValueList;

@App("/whizu/tournament5")
public class TournamentMark implements JQueryMobile {

	private Header header = null;
	private final ValueList<PlayerVO> playerList = new ValueList<PlayerVO>(
			PlayerVO.class);

	public void onLoad(Page page) {

		// @formatter:off
		
		//final Panel playerPage = playerPage();
		//final Page gamesInProgress = gamesInProgressPage();
		
		header = HeaderBuilder.create().title(Messages.TITLE).build();
		addHeaderOn(page);

		final Layout right = new HorizontalLayout();
		Layout left = new GridLayout();
		Button openPlayerPage = ButtonBuilder.create()
				.title(Messages.ADD_PLAYER).onClick(new ClickListener() {

					public void click() {
						right.empty();
						addPlayerPageContentOn(right);

					}

				}).build();

		Button openRankingPage = ButtonBuilder.create().title(Messages.RANKING)
				.onClick(new ClickListener() {

					public void click() {
						right.empty();
						addRankingPageContentOn(right);
					}

				}).build();

		left.add(openPlayerPage);
		left.add(openRankingPage);

		Layout root = new GridLayout("180px", "99%");
		root.add(left);
		root.add(right);
		page.add(root);
		// @formatter:on

	}

	private Page gamesInProgressPage() {
		Page page = PageBuilder.createWithId("games_in_progress_page")
				.add("games_in_progress_page").build();
		return page;
	}

	private Panel playerPage() {
		Panel page = PanelBuilder.createWithId("player_page").build();
		Layout layout = new GridLayout("180px", "99%"); // per column, de
														// minimum-width
		addPlayerPageContentOn(layout);
		page.add(layout);
		return page;

	}

	private void addPlayerPageContentOn(Layout layout) {
		Layout left = new VerticalLayout();
		Button addPlayer = ButtonBuilder.createWithTitle(Messages.ADD_PLAYER)
		// .inline().onClickOpen(playerPage())
				.onClick(playerList.addEvent()).build();
		left.add(addPlayer);
		layout.add(left);

		ListView playerView = ListViewBuilderNg.createWith(playerList)
				.ordered().build();
		layout.add(playerView);
	}

	private void addRankingPageContentOn(Layout layout) {
		Set<PlayerVO> sorted = new TreeSet<PlayerVO>(playerList.get());
		ValueList<PlayerVO> sortedValueList = new ValueList<PlayerVO>(
				PlayerVO.class);
		sortedValueList.get().addAll(sorted);// :(
		ListView playerView = ListViewBuilderNg.createWith(sortedValueList)
				.ordered().build();

		layout.add(playerView);
	}

	private void addHeaderOn(Page page) {
		if (notNull(header) && notNull(page)) {
			page.header(header);
		}
	}

	private boolean notNull(Object checkMe) {
		if (checkMe == null) {
			LoggerFactory.getLogger(TournamentApp.class).error(
					"! Object is null");
			return false;
		}
		return true;
	}
}
