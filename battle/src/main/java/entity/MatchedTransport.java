package entity;

import java.io.Serializable;

import entity.Transport;

public class MatchedTransport implements Serializable{
	private Transport send;

	public Transport getSend() {
		return send;
	}

	public void setSend(Transport send) {
		this.send = send;
	}

	public MatchedTransport() {

	}

	public MatchedTransport(Transport send) {
		if ((send.getTransportType() != Transport.SENDDING)&&(send.getTransportType() != Transport.TRANSPORTING)
				&&(send.getTransportType() != Transport.RECIEVED)) {
			throw new RuntimeException("产品不存在!");
		}

		this.send = send;

	}

	public String toString() {
		return send.toString();
	}
}
