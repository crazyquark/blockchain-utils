package provisioner;

import com.fasterxml.jackson.databind.JsonNode;
import com.googlecode.jsonrpc4j.JsonRpcServer;

public class ProvisionerJsonRpcServer extends JsonRpcServer {

	public ProvisionerJsonRpcServer(Object handler, Class<?> remoteInterface) {
		super(handler, remoteInterface);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Get the handler (object) that should be invoked to execute the specified
	 * RPC method.  Used by subclasses to return handlers specific to a service.
	 *
	 * @param serviceName an optional service name
	 * @return the handler to invoke the RPC call against
	 */
	@Override
	protected Object getHandler(String serviceName) {
		return new ProvisionerServiceEthereum();
	}
}
