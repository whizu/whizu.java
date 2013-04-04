/*******************************************************************************
 * Copyright (c) 2013 Rudy D'hauwe @ Whizu
 * Licensed under the EUPL V.1.1
 *   
 * This Software is provided to You under the terms of the European 
 * Union Public License (the “EUPL”) version 1.1 as published by the 
 * European Union. Any use of this Software, other than as authorized 
 * under this License is strictly prohibited (to the extent such use 
 * is covered by a right of the copyright holder of this Software).
 *
 * This Software is provided under the License on an “AS IS” basis and 
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
package org.whizu.jquery;

/**
 * jQuery's event system normalizes the event object according to W3C standards.
 * The event object is guaranteed to be passed to the event handler (no checks
 * for window.event required). It normalizes the target, relatedTarget, which,
 * metaKey and pageX/Y properties and provides both stopPropagation() and
 * preventDefault() methods. Those properties are all documented, and
 * accompanied by examples, on jQuery's Event object page.
 * 
 * The standard events in the Document Object Model are: blur, focus, load,
 * resize, scroll, unload, beforeunload, click, dblclick, mousedown, mouseup,
 * mousemove, mouseover, mouseout, mouseenter, mouseleave, change, select,
 * submit, keydown, keypress, and keyup. Since the DOM event names have
 * predefined meanings for some elements, using them for other purposes is not
 * recommended. jQuery's event model can trigger an event by any name on an
 * element, and it is propagated up the DOM tree to which that element belongs,
 * if any.
 * 
 * @see <a href='http://api.jquery.com/category/events/event-object'>jQuery's Event object</a>
 * @author Rudy D'hauwe
 */
public class Event {

	public static final Event BEFORE_UNLOAD = new Event("beforeunload");

	public static final Event BLUR = new Event("blur");

	public static final Event CHANGE = new Event("change");

	public static final Event CLICK = new Event("click");

	public static final Event DOUBLE_CLICK = new Event("dblclick");

	public static final Event FOCUS = new Event("focus");

	public static final Event KEY_DOWN = new Event("keydown");

	public static final Event KEY_PRESS = new Event("keypress");

	public static final Event KEY_UP = new Event("keyup");

	public static final Event LOAD = new Event("load");

	public static final Event MOUSE_DOWN = new Event("mousedown");

	public static final Event MOUSE_ENTER = new Event("mouseenter");

	public static final Event MOUSE_LEAVE = new Event("mouseleave");

	public static final Event MOUSE_MOVE = new Event("mousemove");

	public static final Event MOUSE_OUT = new Event("mouseout");

	public static final Event MOUSE_OVER = new Event("mouseover");

	public static final Event MOUSE_UP = new Event("mouseup");

	public static final Event RESIZE = new Event("resize");

	public static final Event SCROLL = new Event("scroll");

	public static final Event SELECT = new Event("select");

	public static final Event SUBMIT = new Event("submit");

	public static final Event UNLOAD = new Event("unload");

	private String name;

	public Event(String name) {
		this.name = name;
	}

	/**
	 * The current DOM element within the event bubbling phase. This property
	 * will typically be equal to the <i>this</i> of the function.
	 * 
	 * @see <a href='http://api.jquery.com/event.currentTarget'>jQuery's
	 *      event.currentTarget</a>
	 */
	public Element currentTarget() {
		throw new UnsupportedOperationException();
	}

	public String getName() {
		return name;
	}

	/**
	 * For key or mouse events, the specific key or button that was pressed. It
	 * normalizes event.keyCode and event.charCode. It is recommended to watch
	 * event.which for keyboard key input. The event.which property also
	 * normalizes button presses (mousedown and mouseup events), reporting 1 for
	 * left button, 2 for middle, and 3 for right. Use event.which instead of
	 * event.button.
	 * 
	 * @see <a href='http://api.jquery.com/event.which'>jQuery's event.which</a>
	 */
	public Number which() {
		throw new UnsupportedOperationException();
	}
}
