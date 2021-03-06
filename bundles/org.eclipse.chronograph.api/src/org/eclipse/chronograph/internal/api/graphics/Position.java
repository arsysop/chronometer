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

package org.eclipse.chronograph.internal.api.graphics;

/**
 * 
 * Described the object positioning in internal interpretation.
 *
 */
public interface Position {

	/**
	 * 
	 * @return start value of position
	 */
	long start();

	/**
	 * 
	 * @return end value of position
	 */
	long end();

	/**
	 * 
	 * @return duration value
	 */
	long duration();

}
