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
package eu.aleon.aleoncean.packet.radio.userdata;

/**
 *
 * @author Markus Rathgeb <maggu2810@gmail.com>
 */
public class UserDataEEPA50402 extends UserDataEEPA504 {

    public static final double HUMIDITY_SCALE_MIN = 0;
    public static final double HUMIDITY_SCALE_MAX = 100;

    public static final double TEMPERATURE_SCALE_MIN = -20;
    public static final double TEMPERATURE_SCALE_MAX = 60;

    public UserDataEEPA50402(byte[] eepData) {
        super(eepData, HUMIDITY_SCALE_MIN, HUMIDITY_SCALE_MAX, TEMPERATURE_SCALE_MIN, TEMPERATURE_SCALE_MAX);
    }

}
