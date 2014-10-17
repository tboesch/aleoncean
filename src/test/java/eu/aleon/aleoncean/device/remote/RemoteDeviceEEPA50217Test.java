/**
 *
 */
package eu.aleon.aleoncean.device.remote;

import eu.aleon.aleoncean.device.StandardDevice;
import eu.aleon.aleoncean.packet.radio.userdata.UserDataScaleValueException;
import eu.aleon.aleoncean.packet.radio.RadioPacket4BS;
import eu.aleon.aleoncean.packet.radio.userdata.UserDataEEPA50217;
import eu.aleon.aleoncean.device.DeviceFactory;
import org.junit.After;
import org.junit.AfterClass;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Thomas Stezaly (thomas.stezaly@aleuon.eu)
 */
public class RemoteDeviceEEPA50217Test {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSetGetTemperature() {

        final double temperatureSet = 20.0;
        final UserDataEEPA50217 userData = new UserDataEEPA50217();

        try {
            userData.setTemperature(temperatureSet);
            userData.setTeachIn(false);
        } catch (UserDataScaleValueException e) {
            fail("Throws exception: UserDataScaleValueException");
        }

        final RadioPacket4BS packet = userData.generateRadioPacket();

        assertTrue(DeviceFactory.isTypeSupported("RD_A5-02-17"));

        final StandardDevice factoryDevice = DeviceFactory.createFromClass(DeviceFactory.getClassForType("RD_A5-02-17"), null, packet.getSenderId(), packet.getDestinationId());

        assertTrue(factoryDevice instanceof RemoteDeviceEEPA50217);

        final RemoteDeviceEEPA50217 testDevice = (RemoteDeviceEEPA50217) factoryDevice;

        testDevice.parseRadioPacket(packet);

        double temperatureGet = testDevice.getTemperature();
        double delta = Math.abs(temperatureGet - temperatureSet);

        assertTrue(delta < 0.4);
    }
}
