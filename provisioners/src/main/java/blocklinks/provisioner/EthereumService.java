package blocklinks.provisioner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import blocklinks.provisioner.ethereum.RpcClient;
import blocklinks.provisioner.ethereum.Util;
import blocklinks.provisioner.ethereum.pojo.Transaction;
import blocklinks.provisioner.ethereum.pojo.TransactionReceipt;

/**
 * Implementation for a provisioning service for Ethereum
 *
 */
public class EthereumService implements ProvisioningService {
	private interface ReceiptCallback {
		public void success(TransactionReceipt receipt);

		public void fail();
	}

	public static final int receiptCheckIntervalMillis = 1000;
	public static final int receiptMaxChecks = 60 * 1000 * 10 / receiptCheckIntervalMillis;

	private final RpcClient rpc = new RpcClient();
	private String rootAccount = "";
	private String secret = "secret";
	private final ScheduledExecutorService executor;

	/**
	 * Creates an Ethereum provisioning service
	 * 
	 * @param rootAccount
	 *            account used for funding
	 * @param secret
	 *            secret to be used for operations
	 * @param executor
	 */
	public EthereumService(String rootAccount, String secret, ScheduledExecutorService executor) {
		this.rootAccount = rootAccount;
		this.secret = secret;
		this.executor = executor;
	}

	public String getRootAccount() {
		return rootAccount;
	}

	public void ListenForTxReceipt(final String txHash, final int checkIntervalMillis, final int checks,
			final ReceiptCallback callback) {

		try {
			synchronized (executor) {
				executor.schedule(new Runnable() {

					public void run() {
						TransactionReceipt receipt = rpc.getTransactionReceipt(txHash);

						if (checks <= 0) {
							callback.fail();
						} else if (receipt != null && receipt.getBlockNumber() != null) {
							callback.success(receipt);
						} else {
							ListenForTxReceipt(txHash, checkIntervalMillis, checks - 1, callback);
						}
					}

				}, checkIntervalMillis, TimeUnit.MILLISECONDS);
			}
		} catch (RejectedExecutionException e) {
		}
	}

	public String[] provision(ProvisionRequest[] requests) {

		String[] txHashes = new String[requests.length];
		String from = rootAccount;
		if (from == null || from.isEmpty()) {
			from = rpc.getCoinbase();
		}

		int it = 0;
		if (rpc.unlockAccount(from, secret)) {
			for (ProvisionRequest req : requests) {
				String to = req.getAccount();
				String currency = req.getCurrency();

				if (currency.equals("ether")) {
					float ether = req.getAmount();

					BigDecimal etherAsBigDecimal = new BigDecimal(ether);
					BigInteger wei = Util.fromEtherToWei(etherAsBigDecimal);

					final String txHash = rpc.sendTransaction(from, to, wei);
					txHashes[it++] = txHash;
					System.out.println("Submitted tx " + txHash + "!");
					ListenForTxReceipt(txHash, receiptCheckIntervalMillis, receiptMaxChecks, new ReceiptCallback() {
						public void success(TransactionReceipt receipt) {
							System.out.println(
								"Transaction " + txHash + " has been mined into block " + receipt.getBlockNumber()
							);
						}

						public void fail() {
							System.err.println("Receipt for Transaction " + txHash + " has timedout!");
						}
					});
				}
			}
		}

		return txHashes;
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
