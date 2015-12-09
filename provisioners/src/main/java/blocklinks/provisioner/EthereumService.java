package blocklinks.provisioner;

import java.math.BigDecimal;
import java.math.BigInteger;

import blocklinks.provisioner.ethereum.RpcClient;
import blocklinks.provisioner.ethereum.Util;

/**
 * Implementation for a provisioning service for Ethereum
 *
 */
public class EthereumService implements ProvisioningService {
	private RpcClient rpc = new RpcClient();
	private String rootAccount = "";

	/**
	 * Creates an Ethereum provisioning service
	 * 
	 * @param rootAccount
	 *            account used for funding
	 * @param secret
	 *            secret to be used for operations
	 */
	public EthereumService(String rootAccount, String secret) {
		this.rootAccount = rootAccount;
	}

	public String getRootAccount() {
		return rootAccount;
	}

	public int provision(ProvisionRequest[] requests) {

		for (ProvisionRequest req : requests) {
			String to = req.getAccount();
			String currency = req.getCurrency();

			if (currency.equals("ether")) {
				float ether = req.getAmount();

				BigDecimal etherAsBigDecimal = new BigDecimal(ether);
				BigInteger wei = Util.fromEtherToWei(etherAsBigDecimal);

				String from = rootAccount;
				String secret = "secret";
				if (from == null || from.isEmpty()) {
					from = rpc.getCoinbase();
				}

				String txHash = rpc.sendTransaction(from, secret, to, wei);
				System.out.println(txHash);
			}
		}

		return 0;
	}

	public BigInteger[] getAccountBalance(String[] addressList) {
		BigInteger[] balances = new BigInteger[addressList.length];

		int i = 0;
		for (String address : addressList) {
			balances[i++] = rpc.getBalance(address);
		}

		return balances;
	}
}
