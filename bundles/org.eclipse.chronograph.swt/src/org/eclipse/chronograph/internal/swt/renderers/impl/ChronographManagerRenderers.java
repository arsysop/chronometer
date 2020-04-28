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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.chronograph.internal.api.Brick;
import org.eclipse.chronograph.internal.api.Group;
import org.eclipse.chronograph.internal.api.Section;
import org.eclipse.chronograph.internal.swt.renderers.api.ChronographGroupRenderer;
import org.eclipse.chronograph.internal.swt.renderers.api.ChronographObjectContentRenderer;
import org.eclipse.chronograph.internal.swt.renderers.api.ChronographObjectExtRenderer;
import org.eclipse.chronograph.internal.swt.renderers.api.ChronographObjectLabelRenderer;
import org.eclipse.chronograph.internal.swt.renderers.api.ChronographSectionRenderer;
import org.eclipse.chronograph.internal.swt.renderers.api.ChronographStageRenderer;
import org.eclipse.chronograph.internal.swt.renderers.api.ChronographStageRulerRenderer;
import org.eclipse.chronograph.internal.swt.renderers.api.ChronographStatusRenderer;

/**
 * 
 * Class intended to aggregate the renders
 *
 */
public class ChronographManagerRenderers {

	public static ChronographManagerRenderers INSTANCE = null;
	private final ChronographObjectExtRenderer<Brick> objectExtensionRenderer;
	private final ChronographObjectLabelRenderer<Brick> objectLabelRenderer;
	private final ChronographObjectContentRenderer<Brick> objectContentRenderer;
	private final ChronographObjectContentRenderer<Brick> objectSelectedRenderer;
	private final ChronographGroupRenderer<Group> chronographGroupRenderer;
	private final List<ChronographStageRulerRenderer> chronographStageRulerRenderers;
	private final ChronographStageRenderer chronographStageRenderer;
	private final ChronographStatusRenderer chronographStatusRenderer;
	private final ChronographSectionRenderer<Section> sectionPinter;

	private ChronographManagerRenderers() {
		this.objectContentRenderer = new ObjectContentRendererImpl();
		this.objectLabelRenderer = new ObjectLabelRendererImpl();
		this.objectExtensionRenderer = new ObjectExtensionRendererImpl();
		this.objectSelectedRenderer = new ObjectSelectedRendererImpl();
		this.chronographStageRulerRenderers = new ArrayList<>();
		this.chronographStageRulerRenderers.add(new RulerDayRendererImpl());
		this.chronographStageRulerRenderers.add(new RulerMonthRendererImpl());
		this.chronographStageRulerRenderers.add(new RulerYearRendererImpl());
		this.chronographStageRenderer = new StageRendererImpl();
		this.chronographStatusRenderer = new StatusRendererImpl();
		this.chronographGroupRenderer = new GroupRendererImpl<Group>();
		this.sectionPinter = new SectionRendererImpl();

	}

	public static synchronized ChronographManagerRenderers getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ChronographManagerRenderers();
		}
		return INSTANCE;
	}

	public ChronographObjectContentRenderer<Brick> getContentPainter() {
		return this.objectContentRenderer;
	}

	public <V extends ChronographObjectExtRenderer> ChronographObjectExtRenderer<Brick> getDurationPainter() {
		return this.objectExtensionRenderer;
	}

	public <V extends ChronographObjectLabelRenderer> ChronographObjectLabelRenderer<Brick> getLabelPainter() {
		return this.objectLabelRenderer;
	}

	public List<ChronographStageRulerRenderer> getDrawingRulersPainter() {
		return chronographStageRulerRenderers;
	}

	public ChronographStageRenderer getDrawingStagePainter() {
		return chronographStageRenderer;
	}

	public ChronographStatusRenderer getDrawingStatusPainter() {
		return chronographStatusRenderer;
	}

	public ChronographGroupRenderer<Group> getDrawingGroupPainter() {
		return chronographGroupRenderer;
	}

	public ChronographSectionRenderer<Section> getDrawingSectionPainter() {
		return sectionPinter;
	}

	public ChronographObjectContentRenderer<Brick> getSelectedContentPainter() {
		return objectSelectedRenderer;
	}

}