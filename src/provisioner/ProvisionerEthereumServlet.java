package provisioner;

import javax.servlet.http.HttpServlet;

public class ProvisionerEthereumServlet extends HttpServlet {
	private JsonRpcServer jsonRpcServer;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        jsonRpcServer.handle(req, resp);
    }

    public void init(ServletConfig config) {
        this.jsonRpcServer = new JsonRpcServer(new ProvisionerEthereumServlet(), ProvisionerService.class);
    }
}
