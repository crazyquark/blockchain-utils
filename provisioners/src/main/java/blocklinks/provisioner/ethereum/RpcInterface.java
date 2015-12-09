package blocklinks.provisioner.ethereum;

import java.math.BigInteger;

public interface RpcInterface {
	String 		eth_getBalance(String address);
	String 		eth_getTransactionReceipt(String txhash);
	String 		eth_sendTransaction(String from, String to, BigInteger value);
	String 		eth_coinbase();
	
	boolean 	personal_unlockAccount(String account, String secret);
}
