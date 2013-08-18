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
package org.whizu.jquery.mobile.list;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.whizu.jquery.OnItemClickListener;
import org.whizu.jquery.mobile.AbstractJqmTest;
import org.whizu.jquery.mobile.DataIcon;
import org.whizu.jquery.mobile.Jqm;
import org.whizu.jquery.mobile.ListView;
import org.whizu.jquery.mobile.Page;
import org.whizu.jquery.mobile.Popup;
import org.whizu.jquery.mobile.PopupBuilder;
import org.whizu.jquery.mobile.Theme;
import org.whizu.util.Callback;
import org.whizu.util.Objects;
import org.whizu.value.ValueList;

/**
 * @author Rudy D'hauwe
 */
public class ListViewBuilderNgTest extends AbstractJqmTest {

	@Test
	public void testCreate() {
		ListView view = ListViewBuilderNg.create().build();
		equals("<ul data-role='listview' id='c0'></ul>", view);
	}

	@Test
	public void testCreateWithListControlOfT() {
		// fail("Not yet implemented");
	}

	@Test
	public void testCreateWithValueListOfT() {
		ValueList<TestVO> list = new ValueList<TestVO>(TestVO.class);
		ListView view = ListViewBuilderNg.createWith(list).build();
		equals("<ul data-role='listview' id='c0'></ul>", view);
	}

	@Test
	public void testCreateWithNonEmptyValueListOfT() {
		ValueList<TestVO> list = new ValueList<TestVO>(TestVO.class);
		TestVO v1 = new TestVO();
		v1.name.set("v1");
		list.add(v1);
		TestVO v2 = new TestVO();
		v2.name.set("v2");
		list.add(v2);

		ListView view = ListViewBuilderNg.createWith(list).build();

		Page page = Jqm.index();
		page.add(view);
		assertEquals(
				"$('#index').find('div[data-role=content]').append(\"<ul data-role='listview' id='c0'><li><a data-role='list-anchor' data-id='generated-id0' href='#'><h2>v1</h2></a></li><li><a data-role='list-anchor' data-id='generated-id1' href='#'><h2>v2</h2></a></li></ul>\");$(document).on('click', '#c0 li a[data-role=list-anchor]', function(event) { event.preventDefault();$.get('/dev/whizu/event?id=c1', 'data-id='+$(this).attr('data-id')+'&data-index='+$(this).closest('ul,ol[data-role=listview]').find('li').index($(this).closest('li')), function(data) {  }, 'script');return false; });",
				theRequest.finish());
	}

	@Test
	public void testAddEventOnNonEmptyValueListOfT() {
		@SuppressWarnings("deprecation")
		Date date = new Date(2012, 7, 22);
		ValueList<TestVO> list = new ValueList<TestVO>(TestVO.class);
		TestVO v1 = new TestVO();
		v1.name.set("v1");
		v1.date.set(date);
		list.add(v1);
		TestVO v2 = new TestVO();
		v2.name.set("v2");
		v2.date.set(date);
		list.add(v2);

		ListViewBuilderNg<TestVO> builder = ListViewBuilderNg.createWith(list);
		ListView view = builder.build();

		Page page = Jqm.index();
		page.add(view);
		assertEquals(
				"$('#index').find('div[data-role=content]').append(\"<ul data-role='listview' id='c0'><li><a data-role='list-anchor' data-id='generated-id0' href='#'><h2>v1</h2></a></li><li><a data-role='list-anchor' data-id='generated-id1' href='#'><h2>v2</h2></a></li></ul>\");$(document).on('click', '#c0 li a[data-role=list-anchor]', function(event) { event.preventDefault();$.get('/dev/whizu/event?id=c1', 'data-id='+$(this).attr('data-id')+'&data-index='+$(this).closest('ul,ol[data-role=listview]').find('li').index($(this).closest('li')), function(data) {  }, 'script');return false; });",
				theRequest.finish());
		
		DefaultValueListControl<TestVO> control = Objects.cast(builder.getControl());
		control.handleAddEvent();
		assertEquals("$.mobile.activePage.append(\"<div data-role='popup' id='org_whizu_jquery_mobile_list_DefaultValueListControl2' style='padding:10px;'><form id='c2' method='post'><label for='c3'>name</label><input id='c3' name='c3' value='' type='text'/><input id='c4' value='OK' type='submit'/></form></div>\").trigger(\"pagecreate\");$('#c2').submit(function(event) { event.preventDefault();$.get('/dev/whizu/event?id=c5', $(this).closest(\"form\").serialize(), function(data) {  }, 'script');return false; });$('#org_whizu_jquery_mobile_list_DefaultValueListControl2').popup(\"open\");", theRequest.finish());
		
		control.model().name.set("nieuwe naam");
		control.ifValidatePerformCallback();
		assertEquals("$('#c3').val(\"nieuwe naam\");$('#c0').append(\"<li><a data-role='list-anchor' data-id='newid' href='#'><h2>nieuwe naam</h2></a></li>\").listview(\"refresh\");$('#org_whizu_jquery_mobile_list_DefaultValueListControl2').popup(\"close\");", theRequest.finish());
	}
	
	@Test
	public void testUpdateEventOnNonEmptyValueListOfT() {
		@SuppressWarnings("deprecation")
		Date date = new Date(2012, 7, 22);
		ValueList<TestVO> list = new ValueList<TestVO>(TestVO.class);
		TestVO v1 = new TestVO();
		v1.name.set("v1");
		v1.date.set(date);
		list.add(v1);
		TestVO v2 = new TestVO();
		v2.name.set("v2");
		v2.date.set(date);
		list.add(v2);

		ListViewBuilderNg<TestVO> builder = ListViewBuilderNg.createWith(list);
		ListView view = builder.build();

		Page page = Jqm.index();
		page.add(view);
		assertEquals(
				"$('#index').find('div[data-role=content]').append(\"<ul data-role='listview' id='c0'><li><a data-role='list-anchor' data-id='generated-id0' href='#'><h2>v1</h2></a></li><li><a data-role='list-anchor' data-id='generated-id1' href='#'><h2>v2</h2></a></li></ul>\");$(document).on('click', '#c0 li a[data-role=list-anchor]', function(event) { event.preventDefault();$.get('/dev/whizu/event?id=c1', 'data-id='+$(this).attr('data-id')+'&data-index='+$(this).closest('ul,ol[data-role=listview]').find('li').index($(this).closest('li')), function(data) {  }, 'script');return false; });",
				theRequest.finish());
		
		DefaultValueListControl<TestVO> control = Objects.cast(builder.getControl());
		TestVO elementToUpdate = list.get(1);
		control.handleClickEvent(elementToUpdate);
		assertEquals("$.mobile.activePage.append(\"<div data-role='popup' id='org_whizu_jquery_mobile_list_DefaultValueListControl2' style='padding:10px;'><form id='c2' method='post'><label for='c3'>name</label><input id='c3' name='c3' value='v2' type='text'/><input id='c4' value='OK' type='submit'/></form></div>\").trigger(\"pagecreate\");$('#c2').submit(function(event) { event.preventDefault();$.get('/dev/whizu/event?id=c5', $(this).closest(\"form\").serialize(), function(data) {  }, 'script');return false; });$('#org_whizu_jquery_mobile_list_DefaultValueListControl2').popup(\"open\");", theRequest.finish());
		
		control.model().name.set("nieuwe naam");
		control.ifValidatePerformCallback();
		assertEquals("$('#c3').val(\"nieuwe naam\");$('#c0').find('li:eq(1) a').first().empty().append(\"<h2>nieuwe naam</h2>\");$('#c0').listview(\"refresh\");$('#org_whizu_jquery_mobile_list_DefaultValueListControl2').popup(\"close\");", theRequest.finish());
	}
	
	@Test
	public void testBuild() {
		ValueList<TestVO> list = new ValueList<TestVO>(TestVO.class);
		TestVO v1 = new TestVO();
		v1.name.set("v1");
		list.add(v1);
		TestVO v2 = new TestVO();
		v2.name.set("v2");
		list.add(v2);

		ListView view = ListViewBuilderNg.createWith(list).build();
		equals("<ul data-role='listview' id='c0'><li><a data-role='list-anchor' data-id='generated-id0' href='#'><h2>v1</h2></a></li><li><a data-role='list-anchor' data-id='generated-id1' href='#'><h2>v2</h2></a></li></ul>",
				view);
	}

	@Test
	public void testOnSplitButtonClick() {
		final ValueList<TestVO> list = new ValueList<TestVO>(TestVO.class);
		TestVO v1 = new TestVO();
		v1.name.set("v1");
		list.add(v1);
		TestVO v2 = new TestVO();
		v2.name.set("v2");
		list.add(v2);

		Page page = Jqm.index();

		// @formatter:off
		ListView view = ListViewBuilderNg.createWith(list)
			.splitButtonIcon(DataIcon.DELETE)
			.onSplitButtonClick(new OnItemClickListener<TestVO>() {
				
				@Override
				public void click(TestVO item, Callback callback) {
					list.remove(item);
				}
			})
			.build();
		// @formatter:on

		page.add(view);
		assertEquals(
				"$('#index').find('div[data-role=content]').append(\"<ul data-role='listview' id='c0' data-split-icon='delete'><li><a data-role='list-anchor' data-id='generated-id0' href='#'><h2>v1</h2></a></li><li><a data-role='list-anchor' data-id='generated-id1' href='#'><h2>v2</h2></a></li></ul>\");$(document).on('click', '#c0 li a[data-role=splitbutton]', function(event) { event.preventDefault();$.get('/dev/whizu/event?id=c2', 'data-id='+$(this).attr('data-id')+'&data-index='+$(this).closest('ul,ol[data-role=listview]').find('li').index($(this).closest('li')), function(data) {  }, 'script');return false; });$('#c0').find('li').append(\"<a data-role='splitbutton' href='#'></a>\");$(document).on('click', '#c0 li a[data-role=list-anchor]', function(event) { event.preventDefault();$.get('/dev/whizu/event?id=c1', 'data-id='+$(this).attr('data-id')+'&data-index='+$(this).closest('ul,ol[data-role=listview]').find('li').index($(this).closest('li')), function(data) {  }, 'script');return false; });",
				theRequest.finish());
	}

	@Test
	public void testOnSplitButtonClickDeleteItem() {
		final ValueList<TestVO> list = new ValueList<TestVO>(TestVO.class);
		TestVO v1 = new TestVO();
		v1.name.set("v1");
		list.add(v1);
		TestVO v2 = new TestVO();
		v2.name.set("v2");
		list.add(v2);

		Page page = Jqm.index();

		// @formatter:off
		ListView view = ListViewBuilderNg.createWith(list)
			.splitButtonIcon(DataIcon.DELETE)
			.onSplitButtonClick(new OnItemClickListener<TestVO>() {
				
				@Override
				public void click(TestVO item, Callback callback) {
					list.remove(item);
				}
			})
			.build(); 
		// @formatter:on

		page.add(view);
		assertEquals(
				"$('#index').find('div[data-role=content]').append(\"<ul data-role='listview' id='c0' data-split-icon='delete'><li><a data-role='list-anchor' data-id='generated-id0' href='#'><h2>v1</h2></a></li><li><a data-role='list-anchor' data-id='generated-id1' href='#'><h2>v2</h2></a></li></ul>\");$(document).on('click', '#c0 li a[data-role=splitbutton]', function(event) { event.preventDefault();$.get('/dev/whizu/event?id=c2', 'data-id='+$(this).attr('data-id')+'&data-index='+$(this).closest('ul,ol[data-role=listview]').find('li').index($(this).closest('li')), function(data) {  }, 'script');return false; });$('#c0').find('li').append(\"<a data-role='splitbutton' href='#'></a>\");$(document).on('click', '#c0 li a[data-role=list-anchor]', function(event) { event.preventDefault();$.get('/dev/whizu/event?id=c1', 'data-id='+$(this).attr('data-id')+'&data-index='+$(this).closest('ul,ol[data-role=listview]').find('li').index($(this).closest('li')), function(data) {  }, 'script');return false; });",
				theRequest.finish());
		
		TestVO itemToDelete = list.get(1);
		list.remove(itemToDelete);
		
		assertEquals("$('#c0').find('li:eq(1)').remove();$('#c0').listview(\"refresh\");", theRequest.finish());
	}
	
	@Test
	public void testOnSplitButtonClickOpenPage() {
		ValueList<TestVO> list = new ValueList<TestVO>(TestVO.class);
		TestVO v1 = new TestVO();
		v1.name.set("v1");
		list.add(v1);
		TestVO v2 = new TestVO();
		v2.name.set("v2");
		list.add(v2);

		Page page = Jqm.index();

		// @formatter:off
		ListView view = ListViewBuilderNg.createWith(list)
			.splitButtonIcon(DataIcon.DELETE)
			.onSplitButtonClickOpen(page)
			.build();
		// @formatter:on

		page.add(view);
		assertEquals(
				"$('#index').find('div[data-role=content]').append(\"<ul data-role='listview' id='c0' data-split-icon='delete'><li><a data-role='list-anchor' data-id='generated-id0' href='#'><h2>v1</h2></a></li><li><a data-role='list-anchor' data-id='generated-id1' href='#'><h2>v2</h2></a></li></ul>\");$('#c0').find('li').append(\"<a data-role='splitbutton' href='#index'></a>\");$(document).on('click', '#c0 li a[data-role=list-anchor]', function(event) { event.preventDefault();$.get('/dev/whizu/event?id=c1', 'data-id='+$(this).attr('data-id')+'&data-index='+$(this).closest('ul,ol[data-role=listview]').find('li').index($(this).closest('li')), function(data) {  }, 'script');return false; });",
				theRequest.finish());
	}

	@Test
	public void testOnSplitButtonClickOpenPopup() {
		ValueList<TestVO> list = new ValueList<TestVO>(TestVO.class);
		TestVO v1 = new TestVO();
		v1.name.set("v1");
		list.add(v1);
		TestVO v2 = new TestVO();
		v2.name.set("v2");
		list.add(v2);

		Page page = Jqm.index();
		
		Popup popup = PopupBuilder.createWithId("popup").build();

		// @formatter:off
		ListView view = ListViewBuilderNg.createWith(list)
			.splitButtonIcon(DataIcon.DELETE)
			.onSplitButtonClickOpen(popup)
			.build();
		// @formatter:on

		page.add(view);
		assertEquals(
				"$('#index').find('div[data-role=content]').append(\"<ul data-role='listview' id='c1' data-split-icon='delete'><li><a data-role='list-anchor' data-id='generated-id0' href='#'><h2>v1</h2></a></li><li><a data-role='list-anchor' data-id='generated-id1' href='#'><h2>v2</h2></a></li></ul>\");$('#c1').find('li').append(\"<a data-role='splitbutton' href='#popup'></a>\");$(document).on('click', '#c1 li a[data-role=list-anchor]', function(event) { event.preventDefault();$.get('/dev/whizu/event?id=c2', 'data-id='+$(this).attr('data-id')+'&data-index='+$(this).closest('ul,ol[data-role=listview]').find('li').index($(this).closest('li')), function(data) {  }, 'script');return false; });",
				theRequest.finish());	}

	@Test
	public void testOrdered() {
		ValueList<TestVO> list = new ValueList<TestVO>(TestVO.class);
		ListView view = ListViewBuilderNg.createWith(list).ordered().build();
		equals("<ol data-role='listview' id='c0'></ol>", view);
	}

	@Test
	public void testSplitButtonIcon() {
		ValueList<TestVO> list = new ValueList<TestVO>(TestVO.class);
		TestVO v1 = new TestVO();
		v1.name.set("v1");
		list.add(v1);
		TestVO v2 = new TestVO();
		v2.name.set("v2");
		list.add(v2);

		ListView view = ListViewBuilderNg.createWith(list)
				.splitButtonIcon(DataIcon.DELETE).build();

		Page page = Jqm.index();
		page.add(view);
		assertEquals(
				"$('#index').find('div[data-role=content]').append(\"<ul data-role='listview' id='c0' data-split-icon='delete'><li><a data-role='list-anchor' data-id='generated-id0' href='#'><h2>v1</h2></a></li><li><a data-role='list-anchor' data-id='generated-id1' href='#'><h2>v2</h2></a></li></ul>\");$('#c0').find('li').append(\"<a data-role='splitbutton' href='#'></a>\");$(document).on('click', '#c0 li a[data-role=list-anchor]', function(event) { event.preventDefault();$.get('/dev/whizu/event?id=c1', 'data-id='+$(this).attr('data-id')+'&data-index='+$(this).closest('ul,ol[data-role=listview]').find('li').index($(this).closest('li')), function(data) {  }, 'script');return false; });",
				theRequest.finish());
	}

	@Test
	public void testSplitButtonTheme() {
		ValueList<TestVO> list = new ValueList<TestVO>(TestVO.class);
		TestVO v1 = new TestVO();
		v1.name.set("v1");
		list.add(v1);
		TestVO v2 = new TestVO();
		v2.name.set("v2");
		list.add(v2);

		// @formatter:off
		ListView view = ListViewBuilderNg.createWith(list)
			.splitButtonIcon(DataIcon.DELETE)
			.splitButtonTheme(Theme.B)
			.build();
		// @formatter:on

		Page page = Jqm.index();
		page.add(view);
		assertEquals(
				"$('#index').find('div[data-role=content]').append(\"<ul data-role='listview' id='c0' data-split-icon='delete' data-split-theme='b'><li><a data-role='list-anchor' data-id='generated-id0' href='#'><h2>v1</h2></a></li><li><a data-role='list-anchor' data-id='generated-id1' href='#'><h2>v2</h2></a></li></ul>\");$('#c0').find('li').append(\"<a data-role='splitbutton' href='#'></a>\");$(document).on('click', '#c0 li a[data-role=list-anchor]', function(event) { event.preventDefault();$.get('/dev/whizu/event?id=c1', 'data-id='+$(this).attr('data-id')+'&data-index='+$(this).closest('ul,ol[data-role=listview]').find('li').index($(this).closest('li')), function(data) {  }, 'script');return false; });",
				theRequest.finish());
	}
}
