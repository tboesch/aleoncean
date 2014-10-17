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

import eu.aleon.aleoncean.values.Unit;

/**
 *
 * @author Markus Rathgeb <maggu2810@gmail.com>
 */
public class UserDataEEPA50601EltakoFAH60 extends UserData4BS {

    public static final long ILLUMINATION_RANGE_MIN = 0;
    public static final long ILLUMINATION_RANGE_MAX = 255;
    public static final double ILLUMINATION_SCALE_MIN_D2 = 300;
    public static final double ILLUMINATION_SCALE_MAX_D2 = 30000;
    public static final double ILLUMINATION_SCALE_MIN_D3 = 0;
    public static final double ILLUMINATION_SCALE_MAX_D3 = 100;
    public static final Unit ILLUMINATION_UNIT = Unit.LUX;

    public UserDataEEPA50601EltakoFAH60(byte[] eepData) {
        super(eepData);
    }

    public double getIllumination() throws UserDataScaleValueException {
        Double value  = getScaleValue(2, 7, 2, 0, ILLUMINATION_RANGE_MIN, ILLUMINATION_RANGE_MAX, ILLUMINATION_SCALE_MIN_D2, ILLUMINATION_SCALE_MAX_D2);
        if (value == 300) {
            value  = getScaleValue(3, 7, 3, 0, ILLUMINATION_RANGE_MIN, ILLUMINATION_RANGE_MAX, ILLUMINATION_SCALE_MIN_D3, ILLUMINATION_SCALE_MAX_D3);
        }
        return value;
    }

    public Unit getIlluminationUnit() {
        return ILLUMINATION_UNIT;
    }

    public boolean isPIRStatusOn() {
        return getDataBit(0, 7) == 1;
    }
}
