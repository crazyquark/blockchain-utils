package blocklinks.provisioner;

/**
 * Implementation for a provisioning service for Ethereum
 *
 */
public class EthereumService implements ProvisioningService {
	
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

}
