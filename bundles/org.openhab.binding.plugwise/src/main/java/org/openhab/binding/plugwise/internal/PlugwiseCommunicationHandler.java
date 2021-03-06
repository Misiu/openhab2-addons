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
package org.openhab.binding.plugwise.internal;

import java.io.IOException;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.io.transport.serial.SerialPortManager;
import org.openhab.binding.plugwise.internal.config.PlugwiseStickConfig;
import org.openhab.binding.plugwise.internal.listener.PlugwiseMessageListener;
import org.openhab.binding.plugwise.internal.protocol.Message;
import org.openhab.binding.plugwise.internal.protocol.field.MACAddress;

/**
 * The {@link PlugwiseCommunicationHandler} handles all serial communication with the Plugwise Stick.
 *
 * @author Wouter Born, Karel Goderis - Initial contribution
 */
@NonNullByDefault
public class PlugwiseCommunicationHandler {

    private final PlugwiseCommunicationContext context = new PlugwiseCommunicationContext();
    private final PlugwiseMessageProcessor messageProcessor = new PlugwiseMessageProcessor(context);
    private final PlugwiseMessageSender messageSender = new PlugwiseMessageSender(context);

    private boolean initialized = false;

    public void addMessageListener(PlugwiseMessageListener listener) {
        context.getFilteredListeners().addListener(listener);
    }

    public void addMessageListener(PlugwiseMessageListener listener, MACAddress macAddress) {
        context.getFilteredListeners().addListener(listener, macAddress);
    }

    public PlugwiseStickConfig getConfiguration() {
        return context.getConfiguration();
    }

    public void removeMessageListener(PlugwiseMessageListener listener) {
        context.getFilteredListeners().removeListener(listener);
    }

    public void sendMessage(Message message, PlugwiseMessagePriority priority) throws IOException {
        if (initialized) {
            messageSender.sendMessage(message, priority);
        }
    }

    public void setConfiguration(PlugwiseStickConfig configuration) {
        context.setConfiguration(configuration);
    }

    public void setSerialPortManager(SerialPortManager serialPortManager) {
        context.setSerialPortManager(serialPortManager);
    }

    public void start() throws PlugwiseInitializationException {
        try {
            context.clearQueues();
            context.initializeSerialPort();
            messageSender.start();
            messageProcessor.start();
            initialized = true;
        } catch (PlugwiseInitializationException e) {
            initialized = false;
            throw e;
        }
    }

    public void stop() {
        messageSender.stop();
        messageProcessor.stop();
        context.closeSerialPort();
        initialized = false;
    }

}
