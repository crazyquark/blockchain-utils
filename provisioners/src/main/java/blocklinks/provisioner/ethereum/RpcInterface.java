package blocklinks.provisioner.ethereum;

public interface RpcInterface {
	String 		eth_getBalance(String address);
	String 		eth_getTransactionReceipt(String txhash);
	String 		eth_sendTransaction(String from, String to, int value);
	
	boolean 	personal_unlockAccount(String account, String secret);
}
