package blocklinks.provisioner;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.jsonrpc4j.JsonRpcServer;

public class RippleServlet extends HttpServlet {
	/**
	 * Generated serial ID
	 */
	private static final long serialVersionUID = 5666532580257640955L;
	/**
	 * JSON-RPC server to use
	 */
	private JsonRpcServer jsonRpcServer;
	/**
	 * Ripple funding service
	 */
	private RippleService rippleService;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    	try {
			this.jsonRpcServer.handle(req, resp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void init(ServletConfig config) {
    	this.rippleService = new RippleService("localhost",5105);
        this.jsonRpcServer = new JsonRpcServer(this.rippleService, ProvisioningService.class);
    }
}
