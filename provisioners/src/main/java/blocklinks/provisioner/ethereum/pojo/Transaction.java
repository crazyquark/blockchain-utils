package blocklinks.provisioner.ethereum.pojo;

import java.math.BigInteger;

public class Transaction {
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public BigInteger getGas() {
		return gas;
	}
	public void setGas(BigInteger gas) {
		this.gas = gas;
	}
	public BigInteger getGasPrice() {
		return gasPrice;
	}
	public void setGasPrice(BigInteger gasPrice) {
		this.gasPrice = gasPrice;
	}
	public BigInteger getValue() {
		return value;
	}
	public void setValue(BigInteger value) {
		this.value = value;
	}
	
	public String 		from;
	public String 		to;
	public BigInteger 	gas;
	public BigInteger 	gasPrice;
	public BigInteger 	value;
}
