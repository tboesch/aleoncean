/**
 *
 */
package eu.aleon.aleoncean.device.remote;

import eu.aleon.aleoncean.device.StandardDevice;
import eu.aleon.aleoncean.packet.radio.userdata.UserDataScaleValueException;
import eu.aleon.aleoncean.packet.radio.RadioPacket4BS;
import eu.aleon.aleoncean.packet.radio.userdata.UserDataEEPA50402;
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
public class RemoteDeviceEEPA50402Test {

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
    public void testCreateThroughFactory() {

        assertTrue(DeviceFactory.isTypeSupported("RD_A5-04-02"));

        final StandardDevice factoryDevice = DeviceFactory.createFromType("RD_A5-04-02", null, null, null);

        assertTrue(factoryDevice instanceof RemoteDeviceEEPA50402);

     }
}
