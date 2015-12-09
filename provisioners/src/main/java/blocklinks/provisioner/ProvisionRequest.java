package blocklinks.provisioner;

public class ProvisionRequest {
	private String account;
	private float amount;
	private String currency;
	
	/**
	 * Create a provision request
	 * 
	 * @param account account to send currency to
	 * @param amount amount to send
	 */
	public ProvisionRequest(String account, float amount, String currency) {
		this.setAccount(account);
		this.setAmount(amount);
		this.setCurrency(currency);
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

	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
}
