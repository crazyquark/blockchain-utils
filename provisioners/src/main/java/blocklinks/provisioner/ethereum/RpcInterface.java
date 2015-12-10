package blocklinks.provisioner.ethereum;

import java.math.BigInteger;

import blocklinks.provisioner.ethereum.pojo.Transaction;

public interface RpcInterface {
	String 			eth_getBalance(String address);
	Transaction 	eth_getTransactionReceipt(String txhash);
	String 			eth_sendTransaction(Transaction t);
	String 			eth_coinbase();
	
	boolean 		personal_unlockAccount(String account, String secret);
}
