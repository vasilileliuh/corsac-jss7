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

package org.restcomm.protocols.ss7.map.api.service.mobility.authentication;

import java.io.Serializable;
import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.ISDNAddressStringImpl;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.BearerServiceCodeImpl;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.CategoryImpl;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ODBDataImpl;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.SubscriberStatus;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.TeleserviceCodeImpl;
import org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement.ZoneCodeImpl;
import org.restcomm.protocols.ss7.map.api.service.supplementary.SSInfoImpl;

/**
 *
 SubscriberData ::= SEQUENCE { msisdn [1] ISDN-AddressStringImpl OPTIONAL, category [2] Category OPTIONAL, subscriberStatus [3]
 * SubscriberStatus OPTIONAL, bearerServiceList [4] BearerServiceList OPTIONAL, teleserviceList [6] TeleserviceList OPTIONAL,
 * provisionedSS [7] SS-InfoList OPTIONAL, odb-Data [8] ODB-Data OPTIONAL, -- odb-Data must be absent in version 1
 * roamingRestrictionDueToUnsupportedFeature [9] NULL OPTIONAL, -- roamingRestrictionDueToUnsupportedFeature must be absent --
 * in version 1 regionalSubscriptionData [10] ZoneCodeList OPTIONAL -- regionalSubscriptionData must be absent in version 1 }
 *
 * BearerServiceList ::= SEQUENCE SIZE (1..50) OF BearerServiceCode
 *
 * TeleserviceList ::= SEQUENCE SIZE (1..20) OF TeleserviceCode
 *
 * SS-InfoList ::= SEQUENCE SIZE (1..30) OF SS-Info
 *
 * ZoneCodeList ::= SEQUENCE SIZE (1..10) OF ZoneCode
 *
 *
 * @author sergey vetyutnev
 *
 */
public interface SubscriberData extends Serializable {

    ISDNAddressStringImpl getMsisdn();

    CategoryImpl getCategory();

    SubscriberStatus getSubscriberStatus();

    ArrayList<BearerServiceCodeImpl> getBearerServiceList();

    ArrayList<TeleserviceCodeImpl> getTeleserviceList();

    ArrayList<SSInfoImpl> getProvisionedSS();

    ODBDataImpl getOdbData();

    boolean getRoamingRestrictionDueToUnsupportedFeature();

    ArrayList<ZoneCodeImpl> getRegionalSubscriptionData();

}
