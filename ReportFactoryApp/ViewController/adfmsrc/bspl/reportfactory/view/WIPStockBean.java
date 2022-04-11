package bspl.reportfactory.view;

import java.time.Year;

import java.util.Map;
import java.util.Random;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.service.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class WIPStockBean {
    public WIPStockBean() {
    }
    
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void OnClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String LOC = (String) pvIter.getCurrentRow().getAttribute("LocWip");
        //String Gl = (String) pvIter.getCurrentRow().getAttribute("GlCode");
        // String SessionId = (String) pvIter.getCurrentRow().getAttribute("SessionId");
        //String Year = (String) pvIter.getCurrentRow().getAttribute("FinYear");
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate");
       

        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callWIPStock");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LOC", LOC);
        operationBinding.getParamsMap().put("FRDATE",fromDate);
        operationBinding.getParamsMap().put("TODATE",toDate);

        Object result = operationBinding.execute();
        
    }

    public HttpSession fetchSession()
       {
           HttpServletRequest request =
               (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
           HttpSession httpSession = request.getSession(true);
           logger.info("Login Session value with true : " + httpSession);
           HttpSession httpSessionfalse = request.getSession(false);
           logger.info("Login Session value with false : " + httpSessionfalse);
           return httpSession;
       }
}
