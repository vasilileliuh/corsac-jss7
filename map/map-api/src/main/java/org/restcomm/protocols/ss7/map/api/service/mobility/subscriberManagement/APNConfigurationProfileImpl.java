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
package org.restcomm.protocols.ss7.map.api.service.mobility.subscriberManagement;

import java.util.ArrayList;

import org.restcomm.protocols.ss7.map.api.primitives.MAPExtensionContainerImpl;

import com.mobius.software.telco.protocols.ss7.asn.ASNClass;
import com.mobius.software.telco.protocols.ss7.asn.annotations.ASNProperty;
import com.mobius.software.telco.protocols.ss7.asn.annotations.ASNTag;
import com.mobius.software.telco.protocols.ss7.asn.primitives.ASNInteger;
import com.mobius.software.telco.protocols.ss7.asn.primitives.ASNNull;

/**
 *
 * @author Lasith Waruna Perera
 *
 */
@ASNTag(asnClass=ASNClass.UNIVERSAL,tag=16,constructed=true,lengthIndefinite=false)
public class APNConfigurationProfileImpl {
	@ASNProperty(asnClass=ASNClass.UNIVERSAL,tag=2,constructed=false,index=0)
    private ASNInteger defaultContext;
    private ASNNull completeDataListIncluded;
    
    @ASNProperty(asnClass=ASNClass.CONTEXT_SPECIFIC,tag=1,constructed=true,index=-1)
    private APNConfigurationListWrapperImpl ePSDataList;
    
    @ASNProperty(asnClass=ASNClass.CONTEXT_SPECIFIC,tag=2,constructed=true,index=-1)
    private MAPExtensionContainerImpl extensionContainer;

    public APNConfigurationProfileImpl() {
    }

    public APNConfigurationProfileImpl(int defaultContext, boolean completeDataListIncluded,
            ArrayList<APNConfigurationImpl> ePSDataList, MAPExtensionContainerImpl extensionContainer) {
        this.defaultContext = new ASNInteger();
        this.defaultContext.setValue((long)defaultContext & 0x0FFFFFFFFL);
        
        if(completeDataListIncluded)
        	this.completeDataListIncluded = new ASNNull();
        
        if(ePSDataList!=null)
        	this.ePSDataList = new APNConfigurationListWrapperImpl(ePSDataList);
        
        this.extensionContainer = extensionContainer;
    }

    public int getDefaultContext() {
    	if(this.defaultContext==null)
    		return 0;
    	
        return this.defaultContext.getValue().intValue();
    }

    public boolean getCompleteDataListIncluded() {
        return this.completeDataListIncluded!=null;
    }

    public ArrayList<APNConfigurationImpl> getEPSDataList() {
    	if(this.ePSDataList==null)
    		return null;
    	
        return this.ePSDataList.getAPNConfiguration();
    }

    public MAPExtensionContainerImpl getExtensionContainer() {
        return this.extensionContainer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("APNConfigurationProfile [");

        if(this.defaultContext!=null) {
	        sb.append("defaultContext=");
	        sb.append(this.defaultContext.getValue());
	        sb.append(", ");
        }
        
        if (this.completeDataListIncluded!=null) {
            sb.append("completeDataListIncluded, ");
        }

        if (this.ePSDataList != null && this.ePSDataList.getAPNConfiguration()!=null) {
            sb.append("ePSDataList=[");
            boolean firstItem = true;
            for (APNConfigurationImpl be : this.ePSDataList.getAPNConfiguration()) {
                if (firstItem)
                    firstItem = false;
                else
                    sb.append(", ");
                sb.append(be.toString());
            }
            sb.append("], ");
        }

        if (this.extensionContainer != null) {
            sb.append("extensionContainer=");
            sb.append(this.extensionContainer.toString());
            sb.append(" ");
        }

        sb.append("]");

        return sb.toString();
    }
}
