/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sergei Kovalchuk <sergei.kovalchuk@arsysop.ru> - initial API and implementation
 *******************************************************************************/

package org.eclipse.chronograph.api;

/**
 * 
 * The Section describes typed container with sequence Group items  
 *
 */
public interface Section extends GroupContainer, BrickContainer {

	/**
	 * 
	 * @return the String identifier of Section element
	 */
	String id();
}
