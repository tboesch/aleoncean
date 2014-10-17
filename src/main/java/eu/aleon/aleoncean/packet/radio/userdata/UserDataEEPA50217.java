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
public class UserDataEEPA50217 extends UserDataEEPA502BitLength8 {

    private static final double TEMPERATURE_SCALE_MIN = 10;
    private static final double TEMPERATURE_SCALE_MAX = 90;

    /**
     * Default class constructor
     */
    public UserDataEEPA50217(){
        super(TEMPERATURE_SCALE_MIN, TEMPERATURE_SCALE_MAX);
    }

    /**
     * Class constructor specifying the values of the eep data array.
     *
     * @param eepData
     */
    public UserDataEEPA50217(byte[] eepData) {
        super(eepData, TEMPERATURE_SCALE_MIN, TEMPERATURE_SCALE_MAX);
    }

}
