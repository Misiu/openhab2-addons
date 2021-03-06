/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.pcf8574.internal;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.wiringpi.GpioUtil;

/**
 * The {@link GPIODataHolder} holds a reference to GpioController.
 * There should be only one instance per whole system
 *
 * @author Tomasz Jagusz - Initial contribution, based on MCP23017 by Anatol Ogorek
 */
public class GPIODataHolder {
    static {
        GpioUtil.enableNonPrivilegedAccess();
    }
    public static final GpioController GPIO = GpioFactory.getInstance();
}
