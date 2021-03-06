/*
 * TeleStax, Open Source Cloud Communications  Copyright 2012.
 * and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.restcomm.protocols.ss7.map.service.supplementary;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSCodeImpl;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSForBSCode;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSForBSCodeImpl;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SupplementaryCodeValue;
import org.restcomm.protocols.ss7.map.service.supplementary.EraseSSRequestImpl;
import org.testng.annotations.Test;

/**
*
* @author sergey vetyutnev
*
*/
public class EraseSSRequestTest {

    private byte[] getEncodedData1() {
        return new byte[] { 48, 3, 4, 1, 18 };
    }

    @Test(groups = { "functional.decode", "service.supplementary" })
    public void testDecode() throws Exception {

        byte[] rawData = getEncodedData1();

        AsnInputStream asn = new AsnInputStream(rawData);

        int tag = asn.readTag();
        assertEquals(tag, Tag.SEQUENCE);
        assertEquals(asn.getTagClass(), Tag.CLASS_UNIVERSAL);

        EraseSSRequestImpl impl = new EraseSSRequestImpl();
        impl.decodeAll(asn);

        assertEquals(impl.getSsForBSCode().getSsCode().getSupplementaryCodeValue(), SupplementaryCodeValue.clir);
        assertNull(impl.getSsForBSCode().getBasicService());
        assertFalse(impl.getSsForBSCode().getLongFtnSupported());

    }

    @Test(groups = { "functional.encode", "service.supplementary" })
    public void testEncode() throws Exception {

    	SSCodeImpl ssCode = new SSCodeImpl(SupplementaryCodeValue.clir);
        SSForBSCode ssForBSCode = new SSForBSCodeImpl(ssCode, null, false);
        EraseSSRequestImpl impl = new EraseSSRequestImpl(ssForBSCode);

        AsnOutputStream asnOS = new AsnOutputStream();

        impl.encodeAll(asnOS);

        byte[] encodedData = asnOS.toByteArray();
        byte[] rawData = getEncodedData1();
        assertTrue(Arrays.equals(rawData, encodedData));
    }

}
