package blocklinks.provisioner;

import java.math.BigInteger;

public interface ProvisioningService {
	
	/**
	 * This returns the account used for provisioning accounts
	 * @return an account address(Ethereum, Ripple, BTC etc...)
	 */
	public String getRootAccount();
	
	/**
	 * Provisions account with the given amount of currency
	 * @param requests requests to execute
	 * @return
	 */
	public int provision(ProvisionRequest[] requests);
	
	public BigInteger[] getAccountBalance(String[] addressList);
}
