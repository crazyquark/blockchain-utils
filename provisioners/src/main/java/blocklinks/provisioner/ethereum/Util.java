package blocklinks.provisioner.ethereum;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Util {
	public static final BigDecimal etherValue = new BigDecimal(1000000000000000000L);
	
	public static BigInteger fromEtherToWei(BigDecimal ether) {
		return ether.multiply(etherValue).toBigInteger();
	}
	
	public static BigDecimal fromWeiToEther(BigInteger wei) {
		BigDecimal weiDecimal = new BigDecimal(wei);
		return weiDecimal.divide(etherValue);
	}
}
