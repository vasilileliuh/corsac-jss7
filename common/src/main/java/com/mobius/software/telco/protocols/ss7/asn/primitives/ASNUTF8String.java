package com.mobius.software.telco.protocols.ss7.asn.primitives;

/*
 * Mobius Software LTD
 * Copyright 2019, Mobius Software LTD and individual contributors
 * by the @authors tag.
 *
 * This program is free software: you can redistribute it and/or modify
 * under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

/**
*
* @author yulian oifa
*
*/

import java.io.UnsupportedEncodingException;

import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.ss7.asn.ASNClass;
import com.mobius.software.telco.protocols.ss7.asn.ASNParser;
import com.mobius.software.telco.protocols.ss7.asn.annotations.ASNDecode;
import com.mobius.software.telco.protocols.ss7.asn.annotations.ASNEncode;
import com.mobius.software.telco.protocols.ss7.asn.annotations.ASNLength;
import com.mobius.software.telco.protocols.ss7.asn.annotations.ASNTag;

@ASNTag(asnClass=ASNClass.UNIVERSAL,tag=12,constructed=false,lengthIndefinite=false)
public class ASNUTF8String {
	public static final String ENCODING="UTF-8";
	private String value;
		
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@ASNLength
	public Integer getLength(ASNParser parser) throws UnsupportedEncodingException {
		return getLength(value);
	}
	
	@ASNEncode
	public void encode(ASNParser parser,ByteBuf buffer) throws UnsupportedEncodingException {
		buffer.writeBytes(value.getBytes(ENCODING));
	}
	
	@ASNDecode
	public Boolean decode(ASNParser parser,Object parent,ByteBuf buffer,Boolean skipErrors) throws UnsupportedEncodingException {
		byte[] data=new byte[buffer.readableBytes()];
		buffer.readBytes(data);
		value=new String(data, ENCODING);
		return false;
	}
	
	public static int getLength(String value) throws UnsupportedEncodingException
	{
		return value.getBytes(ENCODING).length;
	}
}