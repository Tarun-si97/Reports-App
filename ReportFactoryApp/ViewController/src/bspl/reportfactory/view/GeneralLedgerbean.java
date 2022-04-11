package bspl.reportfactory.view;

import bspl.reportfactory.bean.ADFUtils;

import java.net.InetAddress;

import java.net.UnknownHostException;

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

public class GeneralLedgerbean {
    public GeneralLedgerbean() {
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
    

       private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    public void OnClickButton(ActionEvent actionEvent) {
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String Gl = (String) pvIter.getCurrentRow().getAttribute("GlCode");
        // String SessionId = (String) pvIter.getCurrentRow().getAttribute("SessionId");
        String Year = (String) pvIter.getCurrentRow().getAttribute("FinYear");
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate");
            
//          HttpSession fetchSession = fetchSession();
//        BindingContainer binding = BindingContext.getCurrent().getCurrentBindingsEntry();
//        OperationBinding ob = binding.getOperationBinding("CreateUserSession1");
//       // OperationBinding ob=(OperationBinding)ADFUtils.findOperation("CreateUserSession");
//       // ob.getParamsMap().put("sessionId", fetchSession.getId().toString());
//       ob.getParamsMap().put("userId", "1");
//       ob.getParamsMap().put("sessionId", fetchSession.getId().toString());
//       ob.getParamsMap().put("unitCode",Unit);
//       ob.getParamsMap().put("clientIp", getClientIpAddress() + "/" + getClientCompName());
//       ob.getParamsMap().put("successValue", "No");
//        Object ob1 = ob.execute();
//               
//               String SID=fetchSession.getId().toString();
//        
//               System.out.println("====Session=="+fetchSession.getId().toString());
//               
//               System.out.println("======SID=="+SID);
              Random rand = new Random();

        int num = rand.nextInt(9023632) + 1000000;
        
        System.out.println("==========="+num);
        
        // ADFUtils.setValueToPageFlowScope("LV_SID",num);
        ADFContext adfCtx = ADFContext.getCurrent();
        Map pageFlowScope = adfCtx.getPageFlowScope();
        Object val = pageFlowScope.put("Sid", num);
        
        System.out.println("Parameters :- fromDate:" + fromDate + " ||UNIT CODE:" + Unit + "|| toDate:" + toDate  +
                        " GL:"+Gl+"Year:"+Year);
        
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callGenLedgerproc");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_GLCD",Gl);
        operationBinding.getParamsMap().put("LV_SID",num);
        operationBinding.getParamsMap().put("LV_YEAR",Year);
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
