/**
 * Start time:00:01:33 2009-09-07<br>
 * Project: mobicents-isup-stack<br>
 * 
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 */
package org.mobicents.protocols.ss7.isup.impl.message;

import org.mobicents.protocols.ss7.isup.ISUPParameterFactory;
import org.mobicents.protocols.ss7.isup.ParameterException;
import org.mobicents.protocols.ss7.isup.message.ForwardTransferMessage;
import org.mobicents.protocols.ss7.isup.message.parameter.MessageType;

/**
 * Start time:00:01:33 2009-09-07<br>
 * Project: mobicents-isup-stack<br>
 * 
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski
 *         </a>
 */
public class ForwardTransferMessageImpl extends ISUPMessageImpl implements ForwardTransferMessage {

	/**
	 * 	
	 * @param source
	 * @throws ParameterException
	 */
	public ForwardTransferMessageImpl(){
		
		
	}

	/* (non-Javadoc)
	 * @see org.mobicents.protocols.ss7.isup.ISUPMessageImpl#decodeMandatoryParameters(byte[], int)
	 */
	
	protected int decodeMandatoryParameters(ISUPParameterFactory parameterFactory,byte[] b, int index) throws ParameterException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.mobicents.protocols.ss7.isup.ISUPMessageImpl#decodeMandatoryVariableBody(byte[], int)
	 */
	
	protected void decodeMandatoryVariableBody(ISUPParameterFactory parameterFactory,byte[] parameterBody, int parameterIndex) throws ParameterException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.mobicents.protocols.ss7.isup.ISUPMessageImpl#decodeOptionalBody(byte[], byte)
	 */
	
	protected void decodeOptionalBody(ISUPParameterFactory parameterFactory,byte[] parameterBody, byte parameterCode) throws ParameterException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.mobicents.protocols.ss7.isup.ISUPMessageImpl#getMessageType()
	 */
	
	public MessageType getMessageType() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.mobicents.protocols.ss7.isup.ISUPMessageImpl#getNumberOfMandatoryVariableLengthParameters()
	 */
	
	protected int getNumberOfMandatoryVariableLengthParameters() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.mobicents.protocols.ss7.isup.ISUPMessageImpl#hasAllMandatoryParameters()
	 */
	
	public boolean hasAllMandatoryParameters() {
		throw new UnsupportedOperationException();
	}
	
	protected boolean optionalPartIsPossible() {
		
		throw new UnsupportedOperationException();
	}

}
