/**
 * Start time:09:26:46 2009-04-22<br>
 * Project: mobicents-isup-stack<br>
 * 
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski
 *         </a>
 * 
 */
package org.mobicents.protocols.ss7.isup.impl.message;

import org.mobicents.protocols.ss7.isup.message.CircuitGroupUnblockingAckMessage;
import org.mobicents.protocols.ss7.isup.message.ISUPMessage;
import org.mobicents.protocols.ss7.isup.message.parameter.CallReference;
import org.mobicents.protocols.ss7.isup.message.parameter.RangeAndStatus;


/**
 * Start time:09:26:46 2009-04-22<br>
 * Project: mobicents-isup-stack<br>
 * Test for CGU
 * 
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class CGUATest extends MessageHarness {
	
	
	public void testTwo_Params() throws Exception
	{
		byte[] message = getDefaultBody();

		//CircuitGroupUnblockingAckMessage cgb=new CircuitGroupUnblockingAckMessageImpl(this,message);
		CircuitGroupUnblockingAckMessage cgb=super.messageFactory.createCGUA();
		((AbstractISUPMessage)cgb).decode(message,parameterFactory);

		
		try{
			RangeAndStatus RS = (RangeAndStatus) cgb.getParameter(RangeAndStatus._PARAMETER_CODE);
			assertNotNull("Range And Status return is null, it should not be",RS);
			if(RS == null)
				return;
			byte range = RS.getRange();
			assertEquals("Range is wrong,",0x0A, range);
			byte[] b=RS.getStatus();
			assertNotNull("RangeAndStatus.getRange() is null",b);
			if(b == null)
			{
				return;
			}	
			assertEquals("Length of param is wrong",2 ,b.length);
			if(b.length != 2)
				return;
			assertTrue("RangeAndStatus.getRange() is wrong,", super.makeCompare(b, new byte[]{
					0x02
					,0x03
					}));
			
		}catch(Exception e)
		{
			e.printStackTrace();
			super.fail("Failed on get parameter["+CallReference._PARAMETER_CODE+"]:"+e);
		}
		
	}
	
	protected byte[] getDefaultBody() {
		//FIXME: for now we strip MTP part
		byte[] message={

				0x0C
				,(byte) 0x0B
				,CircuitGroupUnblockingAckMessage.MESSAGE_CODE
				//Circuit group supervision message type
				,0x01 // hardware failure oriented
				,0x01 // ptr to variable part
				//no optional, so no pointer
				//RangeAndStatus._PARAMETER_CODE
				,0x03
				,0x0A
				,0x02
				,0x03

		};
		return message;
	}

	
	protected ISUPMessage getDefaultMessage() {
		return super.messageFactory.createCGUA();
	}
}
