/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2012, Telestax Inc and individual contributors
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

package org.restcomm.protocols.ss7.cap.EsiBcsm;

import java.io.IOException;
import java.util.ArrayList;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.restcomm.protocols.ss7.cap.api.CAPException;
import org.restcomm.protocols.ss7.cap.api.CAPParsingComponentException;
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.MetDPCriterion;
import org.restcomm.protocols.ss7.cap.api.EsiBcsm.TChangeOfPositionSpecificInfo;
import org.restcomm.protocols.ss7.cap.primitives.SequenceBase;
import org.restcomm.protocols.ss7.inap.api.INAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.MAPException;
import org.restcomm.protocols.ss7.map.api.MAPParsingComponentException;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberInformation.LocationInformation;
import org.restcomm.protocols.ss7.map.service.mobility.subscriberInformation.LocationInformationImpl;

/**
*
* @author sergey vetyutnev
*
*/
public class TChangeOfPositionSpecificInfoImpl extends SequenceBase implements TChangeOfPositionSpecificInfo {
	private static final long serialVersionUID = 1L;

	public static final int _ID_locationInformation = 50;
    public static final int _ID_metDPCriteriaList = 51;

    private LocationInformation locationInformation;
    private ArrayList<MetDPCriterion> metDPCriteriaList;

    public TChangeOfPositionSpecificInfoImpl() {
        super("TChangeOfPositionSpecificInfo");
    }

    public TChangeOfPositionSpecificInfoImpl(LocationInformation locationInformation, ArrayList<MetDPCriterion> metDPCriteriaList) {
        super("TChangeOfPositionSpecificInfo");

        this.locationInformation = locationInformation;
        this.metDPCriteriaList = metDPCriteriaList;
    }

    @Override
    public LocationInformation getLocationInformation() {
        return locationInformation;
    }

    @Override
    public ArrayList<MetDPCriterion> getMetDPCriteriaList() {
        return metDPCriteriaList;
    }

    @Override
    protected void _decode(AsnInputStream asnIS, int length) throws CAPParsingComponentException, IOException, AsnException, MAPParsingComponentException,
            INAPParsingComponentException {

        this.locationInformation = null;
        this.metDPCriteriaList = null;

        AsnInputStream ais = asnIS.readSequenceStreamData(length);
        while (true) {
            if (ais.available() == 0)
                break;

            int tag = ais.readTag();

            if (ais.getTagClass() == Tag.CLASS_CONTEXT_SPECIFIC) {
                switch (tag) {
                    case _ID_locationInformation:
                        this.locationInformation = new LocationInformationImpl();
                        ((LocationInformationImpl) this.locationInformation).decodeAll(ais);
                        break;
                    case _ID_metDPCriteriaList:
                        this.metDPCriteriaList = new ArrayList<MetDPCriterion>();
                        AsnInputStream ais2 = ais.readSequenceStream();

                        while (true) {
                            if (ais2.available() == 0)
                                break;

                            ais2.readTag();
                            MetDPCriterionImpl elem = new MetDPCriterionImpl();
                            elem.decodeAll(ais2);
                            this.metDPCriteriaList.add(elem);
                        }

                        break;

                    default:
                        ais.advanceElement();
                        break;
                }
            } else {
                ais.advanceElement();
            }
        }
    }

    @Override
    public void encodeData(AsnOutputStream aos) throws CAPException {

        if (this.metDPCriteriaList != null && (this.metDPCriteriaList.size() < 1 || this.metDPCriteriaList.size() > 10))
            throw new CAPException("Error while encoding " + _PrimitiveName + ": metDPCriteriaList length must be from 1 to 10");

        try {
            if (this.locationInformation != null) {
                ((LocationInformationImpl) this.locationInformation).encodeAll(aos, Tag.CLASS_CONTEXT_SPECIFIC, _ID_locationInformation);
            }

            if (this.metDPCriteriaList != null) {
                aos.writeTag(Tag.CLASS_CONTEXT_SPECIFIC, false, _ID_metDPCriteriaList);
                int pos = aos.StartContentDefiniteLength();
                for (MetDPCriterion be : this.metDPCriteriaList) {
                    MetDPCriterionImpl bee = (MetDPCriterionImpl) be;
                    bee.encodeAll(aos);
                }
                aos.FinalizeContent(pos);
            }

        } catch (AsnException e) {
            throw new CAPException("AsnException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        } catch (MAPException e) {
            throw new CAPException("MAPException when encoding " + _PrimitiveName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(_PrimitiveName);
        sb.append(" [");

        if (this.locationInformation != null) {
            sb.append("locationInformation=");
            sb.append(locationInformation);
            sb.append(", ");
        }
        if (this.metDPCriteriaList != null) {
            sb.append("metDPCriteriaList=[");
            boolean firstItem = true;
            for (MetDPCriterion be : this.metDPCriteriaList) {
                if (firstItem)
                    firstItem = false;
                else
                    sb.append(", ");
                sb.append(be.toString());
            }
            sb.append("], ");
        }

        sb.append("]");

        return sb.toString();
    }
}
