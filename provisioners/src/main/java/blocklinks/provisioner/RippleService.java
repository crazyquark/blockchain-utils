/**
 * 
 */
package blocklinks.provisioner;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;

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
	
	protected class RippleAccountInfoResp {
		private String id;
		private String status;
		private String type;
		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}
		/**
		 * @return the status
		 */
		public String getStatus() {
			return status;
		}
		/**
		 * @param status the status to set
		 */
		public void setStatus(String status) {
			this.status = status;
		}
		/**
		 * @return the type
		 */
		public String getType() {
			return type;
		}
		/**
		 * @param type the type to set
		 */
		public void setType(String type) {
			this.type = type;
		}
	}
	
	public RippleService() {
		// TODO externalize this
		URL rippledRpcHost = null;
		try {
			rippledRpcHost = new URL("http://localhost:5005/");
			
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
				return this.rippledRpcClient.invoke("account_info", new RippleAccountInfoReq(), RippleAccountInfoResp.class).toString();
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
