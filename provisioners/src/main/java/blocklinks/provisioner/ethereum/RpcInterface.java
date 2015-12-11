package blocklinks.provisioner.ethereum;

import blocklinks.provisioner.ethereum.pojo.Transaction;
import blocklinks.provisioner.ethereum.pojo.TransactionReceipt;

public interface RpcInterface {
	String 				eth_getBalance(String address);
	TransactionReceipt 	eth_getTransactionReceipt(String txhash);
	String 				eth_sendTransaction(Transaction t);
	String 				eth_coinbase();
	
	boolean 			personal_unlockAccount(String account, String secret);
}
