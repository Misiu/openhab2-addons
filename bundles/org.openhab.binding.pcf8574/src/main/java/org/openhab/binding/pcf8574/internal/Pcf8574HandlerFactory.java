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

import static org.openhab.binding.pcf8574.internal.Pcf8574BindingConstants.THING_TYPE_PCF8574;

import java.util.Collections;
import java.util.Set;

import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory;
import org.openhab.binding.pcf8574.internal.handler.Pcf8574Handler;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link Pcf8574HandlerFactory} is responsible for creating thing
 * handlers.
 *
 * @author Tomasz Jagusz - Initial contribution, based on MCP23017 by Anatol Ogorek
 */
@Component(service = ThingHandlerFactory.class, configurationPid = "binding.pcf8574")
public class Pcf8574HandlerFactory extends BaseThingHandlerFactory {

    private static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Collections.singleton(THING_TYPE_PCF8574);
    private final Logger logger = LoggerFactory.getLogger(Pcf8574HandlerFactory.class);

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();
        logger.debug("Trying to create handler for {}", thingTypeUID.getAsString());
        if (thingTypeUID.equals(THING_TYPE_PCF8574)) {
            logger.debug("Handler match for {}", thingTypeUID.getAsString());
            return new Pcf8574Handler(thing);
        }
        logger.debug("No handler match for {}", thingTypeUID.getAsString());
        return null;
    }

}
