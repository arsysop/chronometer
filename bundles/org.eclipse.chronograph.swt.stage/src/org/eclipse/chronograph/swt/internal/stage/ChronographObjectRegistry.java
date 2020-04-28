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

package org.eclipse.chronograph.swt.internal.stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.chronograph.internal.api.Brick;
import org.eclipse.chronograph.internal.api.Group;
import org.eclipse.chronograph.internal.api.Section;
import org.eclipse.chronograph.internal.api.providers.BrickContentProvider;
import org.eclipse.chronograph.internal.api.providers.ContainerProvider;
import org.eclipse.chronograph.internal.api.providers.GroupContentProvider;
import org.eclipse.chronograph.internal.api.providers.SectionContentProvider;
import org.eclipse.chronograph.internal.api.providers.SubGroupContentProvider;
import org.eclipse.chronograph.internal.base.UnitConverter;

/**
 * Class intended to aggregate providers
 * 
 *
 */
public class ChronographObjectRegistry {

	private final ContainerProvider provider;
	private final Map<String, Section> sectionsById = new HashMap<>();
	private final Map<String, List<Group>> groupsBySection = new HashMap<>();
	private final Map<Group, List<Group>> subGroupsBygroup = new HashMap<>();
	private final Map<Group, List<Brick>> bricksBySubgroup = new HashMap<>();

	public ChronographObjectRegistry(ContainerProvider provider) {
		this.provider = provider;
	}

	public void createRegistry() {
		SectionContentProvider<?> sectionProvider = provider.getSectionContentProvider();
		GroupContentProvider<?> groupProvider = provider.getGroupContentProvider();
		SubGroupContentProvider<?> subgroupProvider = provider.getSubGroupContentProvider();
		BrickContentProvider<?> brickProvider = provider.getBrickContentProvider();
		List input = provider.getInput();
		List<Section> sections = sectionProvider.getSections(input);
		for (Section section : sections) {
			sectionsById.put(section.id(), section);
			List<Group> groups = groupProvider.getGroupsBySection(input, section);
			groupsBySection.put(section.id(), groups);
			for (Group group : groups) {
				List<Group> subGroups = subgroupProvider.getGroupsBySectionGroup(input, section, group);
				subGroupsBygroup.put(group, subGroups);
				for (Group subGroup : subGroups) {
					List<Brick> bricks = brickProvider.getBricksBySubGroup(input, group.id(), subGroup);
					bricksBySubgroup.put(subGroup, bricks);
				}
			}
		}
	}

	public List<Section> getSections() {
		return new ArrayList<Section>(sectionsById.values());
	}

	public List<Group> getGroupBySection(Section section) {
		return groupsBySection.get(section.id());
	}

	public List<Group> getSubGroupByGroupSection(Group group) {
		return subGroupsBygroup.get(group);
	}

	public List<Brick> getBrickExpired() {
		List<Brick> result = new ArrayList<>();
		for (List<Brick> bricks : bricksBySubgroup.values()) {
			for (Brick brick : bricks) {
				LocalDate now = LocalDate.now();
				LocalDate start = UnitConverter.unitsToLocalDate((int) brick.position().start());
				LocalDate end = UnitConverter.unitsToLocalDate((int) brick.position().end());
				if (now.isAfter(end) && now.isBefore(start)) {
					result.add(brick);
				}
			}
		}

		return result;
	}

	public List<Brick> getBrickActual() {
		List<Brick> result = new ArrayList<>();
		for (List<Brick> bricks : bricksBySubgroup.values()) {
			for (Brick brick : bricks) {
				LocalDate now = LocalDate.now();
				LocalDate start = UnitConverter.unitsToLocalDate((int) brick.position().start());
				LocalDate end = UnitConverter.unitsToLocalDate((int) brick.position().end());
				if (now.isAfter(start) && now.isBefore(end)) {
					result.add(brick);
				}
			}
		}
		return result;
	}

	public List<Brick> getBrickBySubgroup(String subgroupId, String groupId, String sectionId) {
		if (!sectionId.isEmpty()) {
			Section section = sectionsById.get(sectionId);
			List<Group> groups = groupsBySection.get(section.id());
			for (Group group : groups) {
				if (group.id().equals(groupId)) {
					List<Group> subGroups = subGroupsBygroup.get(group);
					for (Group subGroup : subGroups) {
						return bricksBySubgroup.get(subGroup);
					}
				}
			}
		}
		return new ArrayList<>();
	}

	public List<Brick> getBricks() {
		return bricksBySubgroup.values().stream() //
				.flatMap(List::stream) //
				.collect(Collectors.toList());
	}
}