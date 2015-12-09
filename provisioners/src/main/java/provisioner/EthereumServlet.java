package provisioner;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.jsonrpc4j.JsonRpcServer;

public class EthereumServlet extends HttpServlet {
	/**
	 * Is this serializable or dancer?
	 */
	private static final long serialVersionUID = -7702784587269520126L;
	private JsonRpcServer jsonRpcServer;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    	try {
			this.jsonRpcServer.handle(req, resp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void init(ServletConfig config) {
        this.jsonRpcServer = new JsonRpcServer(new EthereumService(), ProvisioningService.class);
    }
}
