package blocklinks.provisioner;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
	
	/**
	 * JSON-RPC server to use
	 */
	private JsonRpcServer jsonRpcServer;
	private ScheduledExecutorService executor;
	/**
	 * Ethereum funding service
	 */
	private EthereumService ethService;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    	try {
			this.jsonRpcServer.handle(req, resp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void init(ServletConfig config) {
    	this.executor = Executors.newScheduledThreadPool(2);
    	this.ethService = new EthereumService("","secret", executor);
        this.jsonRpcServer = new JsonRpcServer(this.ethService, ProvisioningService.class);
    }
    
    @Override
    public void destroy() {
    	this.executor.shutdown();
    	try {
			this.executor.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
