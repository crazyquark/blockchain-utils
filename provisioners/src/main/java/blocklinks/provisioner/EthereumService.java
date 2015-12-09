package blocklinks.provisioner;
import java.math.BigInteger;

import blocklinks.provisioner.ethereum.RpcClient;
/**
 * Implementation for a provisioning service for Ethereum
 *
 */
public class EthereumService implements ProvisioningService {
	private RpcClient rpc = new RpcClient();
	/**
	 * Creates an Ethereum provisioning service
	 * @param rootAccount account used for funding
	 * @param secret secret to be used for operations
	 */
	public EthereumService(String rootAccount, String secret) {
		// TODO Remove secret
	}

	public String getRootAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	public int provision(ProvisionRequest[] requests) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public BigInteger[] getAccountBalance(String [] addressList) {
		BigInteger[] balances = new BigInteger[addressList.length];
		
		int i = 0;
		for (String address : addressList) {
			balances[i++] = rpc.getBalance(address);
		}
		
		return balances;
	}
}
