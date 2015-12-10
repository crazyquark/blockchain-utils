/**
 * 
 */
package blocklinks.provisioner;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

/**
 * @author chris
 *
 */
public class RippleService implements ProvisioningService {
	private JsonRpcHttpClient rippledRpcClient;
	
	protected class RippleAccountInfoReq {
		private String account;
		
		public RippleAccountInfoReq() {
			this.setAccount("rHb9CJAWyB4rj91VRWn96DkukG4bwdtyTh");
		}
		
		public RippleAccountInfoReq(String account) {
			this.setAccount(account);
		}
		
		/**
		 * @return the account
		 */
		public String getAccount() {
			return account;
		}

		/**
		 * @param account the account to set
		 */
		public void setAccount(String account) {
			this.account = account;
		}
	}
	
	public RippleService() {
		this("localhost", 5005);
	}
	
	public RippleService(String host, int port) {
		URL rippledRpcHost = null;
		try {
			rippledRpcHost = new URL("http://" + host + ":" + Integer.toString(port) + "/");
			
			this.rippledRpcClient = new JsonRpcHttpClient(rippledRpcHost);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see blocklinks.provisioner.ProvisioningService#getRootAccount()
	 */
	public String getRootAccount() {
		if (this.rippledRpcClient != null) {
			try {
				@SuppressWarnings("unchecked")
				LinkedHashMap<String, Object> result = this.rippledRpcClient.invoke("account_info", 
						new Object[] { new RippleAccountInfoReq() }, LinkedHashMap.class);
	
				return result.toString();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see blocklinks.provisioner.ProvisioningService#provision(blocklinks.provisioner.ProvisionRequest[])
	 */
	public int provision(ProvisionRequest[] requests) {
		// TODO Auto-generated method stub
		return 0;
	}

	public BigInteger[] getAccountBalance(String[] addressList) {
		// TODO Auto-generated method stub
		return null;
	}

}
