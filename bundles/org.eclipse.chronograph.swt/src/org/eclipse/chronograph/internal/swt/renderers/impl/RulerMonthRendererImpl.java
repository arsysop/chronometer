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
package org.eclipse.chronograph.internal.swt.renderers.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.eclipse.chronograph.internal.swt.RulerStyler;
import org.eclipse.chronograph.internal.swt.renderers.api.ChronographStageRulerRenderer;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

/**
 * 
 * Render implementation for month ruler
 *
 */
public class RulerMonthRendererImpl implements ChronographStageRulerRenderer {

	private final SimpleDateFormat sdf = new SimpleDateFormat("MMMM"); //$NON-NLS-1$
	private final Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

	@Override
	public void draw(GC gc, Rectangle bounds, int scale, int width, int tiksOffset, int xAxis) {
		int xMaxPosition = bounds.width + bounds.x;
		int yBottomPosition = bounds.y + bounds.height - RulerStyler.RULER_MOUNTH_HEIGHT
				- RulerStyler.RULER_YEAR_HEIGHT;
		int xPosition = 0;
		calendar.clear();
		calendar.set(Calendar.YEAR, 2019);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 01);
		calendar.add(Calendar.DATE, tiksOffset);

		gc.setForeground(RulerStyler.RULER_TOP_COLOR);
		gc.setBackground(RulerStyler.RULER_BTM_COLOR);
		gc.fillGradientRectangle(xPosition, yBottomPosition, bounds.width, RulerStyler.RULER_MOUNTH_HEIGHT, true);
		while (true) {

			if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
				gc.setForeground(RulerStyler.RULER_BRD_COLOR);
				gc.drawLine(xPosition, bounds.y, xPosition, yBottomPosition);
				String msg = sdf.format(calendar.getTime());
				gc.setForeground(RulerStyler.RULER_TEXT_COLOR);
				gc.drawString(msg, xPosition + 4, yBottomPosition + 3, true);
			}
			xPosition += width;
			if (xPosition > xMaxPosition) {
				break;
			}
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}

	}

}