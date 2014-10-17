/*
 * Copyright (c) 2014 aleon GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Markus Rathgeb - initial API and implementation and/or initial documentation
 */
package eu.aleon.aleoncean.device.remote;

import eu.aleon.aleoncean.device.DeviceParameter;
import eu.aleon.aleoncean.device.DeviceParameterUpdatedInitiation;
import eu.aleon.aleoncean.device.IllegalDeviceParameterException;
import eu.aleon.aleoncean.device.RemoteDevice;
import eu.aleon.aleoncean.device.StandardDevice;
import eu.aleon.aleoncean.packet.EnOceanId;
import eu.aleon.aleoncean.packet.RadioPacket;
import eu.aleon.aleoncean.packet.radio.RadioPacket4BS;
import eu.aleon.aleoncean.packet.radio.userdata.UserDataEEPA50601EltakoFAH60;
import eu.aleon.aleoncean.packet.radio.userdata.UserDataScaleValueException;
import eu.aleon.aleoncean.rxtx.ESP3Connector;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Markus Rathgeb <maggu2810@gmail.com>
 */
public class RemoteDeviceEEPA50601EltakoFAH60 extends StandardDevice implements RemoteDevice {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteDeviceEEPA50802.class);

    private Double illumination;

    public RemoteDeviceEEPA50601EltakoFAH60(ESP3Connector conn, EnOceanId addressRemote, EnOceanId addressLocal) {
        super(conn, addressRemote, addressLocal);
    }

    public Double getIllumination() {
        return illumination;
    }

    public void setIllumination(final DeviceParameterUpdatedInitiation initiation, final Double illumination) {
        final Double oldIllumination = this.illumination;
        this.illumination = illumination;
        fireParameterChanged(DeviceParameter.ILLUMINATION_LUX, initiation, oldIllumination, illumination);
    }

    private void parseRadioPacket4BS(RadioPacket4BS packet) {
        if (packet.isTeachIn()) {
            LOGGER.debug("Ignore teach-in packets.");
            return;
        }

        final UserDataEEPA50601EltakoFAH60 userData = new UserDataEEPA50601EltakoFAH60(packet.getUserDataRaw());
        try {
            setIllumination(DeviceParameterUpdatedInitiation.RADIO_PACKET, userData.getIllumination());
        }
        catch (UserDataScaleValueException e) {
            LOGGER.warn("UserDataScaleValueException: could not parse packet (illumination not changed): " + packet.toString());
        }
    }

    @Override
    public void parseRadioPacket(RadioPacket packet) {
        if (packet instanceof RadioPacket4BS) {
            parseRadioPacket4BS((RadioPacket4BS) packet);
        } else {
            LOGGER.warn(String.format("Don't know how to handle radio choice 0x%02X.", packet.getChoice()));
        }
    }

    @Override
    protected void fillParameters(final Set<DeviceParameter> params) {
        params.add(DeviceParameter.ILLUMINATION_LUX);
    }

    @Override
    public Object getByParameter(DeviceParameter parameter) throws IllegalDeviceParameterException {
        switch (parameter) {
            case ILLUMINATION_LUX:
                return getIllumination();
            default:
                return super.getByParameter(parameter);
        }
    }

    @Override
    public void setByParameter(DeviceParameter parameter, Object value) throws IllegalDeviceParameterException {
        assert DeviceParameter.getSupportedClass(parameter).isAssignableFrom(value.getClass());
        super.setByParameter(parameter, value);
    }

}
