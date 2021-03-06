/*******************************************************************************
 *	Copyright (c) 2020 ArSysOp
 *
 *	This program and the accompanying materials are made available under the
 *	terms of the Eclipse Public License 2.0 which is available at
 *	http://www.eclipse.org/legal/epl-2.0.
 *
 *	SPDX-License-Identifier: EPL-2.0
 *
 *	Contributors:
 *	Sergei Kovalchuk <sergei.kovalchuk@arsysop.ru> - 
 *												initial API and implementation
 *******************************************************************************/

package org.eclipse.chronograph.internal.swt;

import org.eclipse.chronograph.internal.api.graphics.Brick;
import org.eclipse.chronograph.internal.api.representation.Styler;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * 
 * Styler class dedicated to coloring {@link Brick}
 *
 */
public class BrickStyler implements Styler {

	private static final Display DISPLAY = Display.getDefault();
	private static Color COLOR_TOP;
	private static Color COLOR_BOTTOM;
	private static Color SELECTED_COLOR_TOP;
	private static Color SELECTED_COLOR_BOTTOM;
	private static Color COLOR_BORDER;
	private static Color COLOR_TEXT;
	private static Color COLOR_CALL_OUT;
	private static Color ACTIVE_COLOR_TOP;
	private static Color ACTIVE_COLOR_BOTTOM;

	@Override
	public void initClassicTheme() {
		COLOR_TOP = new Color(DISPLAY, new RGB(195, 245, 180));
		COLOR_BOTTOM = new Color(DISPLAY, new RGB(100, 150, 90));
		SELECTED_COLOR_TOP = new Color(DISPLAY, new RGB(110, 190, 82));
		SELECTED_COLOR_BOTTOM = new Color(DISPLAY, new RGB(80, 140, 82));
		ACTIVE_COLOR_TOP = new Color(DISPLAY, new RGB(110, 190, 82));
		ACTIVE_COLOR_BOTTOM = new Color(DISPLAY, new RGB(80, 140, 82));
		COLOR_BORDER = new Color(DISPLAY, new RGB(95, 95, 95));
		COLOR_TEXT = new Color(DISPLAY, new RGB(220, 220, 220));
		COLOR_CALL_OUT = new Color(DISPLAY, new RGB(100, 100, 100));
	}

	@Override
	public void initDarkTheme() {
		COLOR_TOP = new Color(DISPLAY, new RGB(136, 105, 184));
		COLOR_BOTTOM = new Color(DISPLAY, new RGB(64, 36, 89));
		SELECTED_COLOR_TOP = new Color(DISPLAY, new RGB(136, 105, 184));
		SELECTED_COLOR_BOTTOM = new Color(DISPLAY, new RGB(64, 36, 89));
		ACTIVE_COLOR_TOP = new Color(DISPLAY, new RGB(149, 140, 184));
		ACTIVE_COLOR_BOTTOM = new Color(DISPLAY, new RGB(104, 136, 89));
		COLOR_BORDER = new Color(DISPLAY, new RGB(190, 155, 184));
		COLOR_TEXT = new Color(DISPLAY, new RGB(220, 220, 220));
		COLOR_CALL_OUT = new Color(DISPLAY, new RGB(220, 220, 220));
	}

	public static int getHeight() {
		GC gc = new GC(DISPLAY);
		FontMetrics fontMetrics = gc.getFontMetrics();
		int height = fontMetrics.getHeight();
		gc.dispose();
		return height * 2;
	}

	public static Color getColorTop() {
		return COLOR_TOP;
	}

	public static Color getColorBottom() {
		return COLOR_BOTTOM;
	}

	public static Color getColorTopSelected() {
		return SELECTED_COLOR_TOP;
	}

	public static Color getColorBottomSelected() {
		return SELECTED_COLOR_BOTTOM;
	}

	public static Color getColorBorder() {
		return COLOR_BORDER;
	}

	public static Color getColorText() {
		return COLOR_TEXT;
	}

	public static Color getColorCallout() {
		return COLOR_CALL_OUT;
	}

	public static Color getActiveColorTop() {
		return ACTIVE_COLOR_TOP;
	}

	public static Color getActiveColorBottom() {
		return ACTIVE_COLOR_BOTTOM;
	}

}
