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
import java.text.SimpleDateFormat;  

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.Connection;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.naming.InitialContext;

import javax.print.attribute.standard.Sides;

import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;



import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;



public class StockLedgerBean {
    public StockLedgerBean() {
    }
      
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
        
    }
      
    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);
    
    
    public void onClickButton(ActionEvent actionEvent) {
        
        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
//        String Gl = (String) pvIter.getCurrentRow().getAttribute("GlCode");
        String GroupCd = (String)pvIter.getCurrentRow().getAttribute("GroupCd");
        String SubGroupCd = (String)pvIter.getCurrentRow().getAttribute("SubGroupCd");
        String ItemCd = (String)pvIter.getCurrentRow().getAttribute("ItemCd");
        String Location = (String)pvIter.getCurrentRow().getAttribute("LOC");
        // String SessionId = (String) pvIter.getCurrentRow().getAttribute("SessionId");
//        String Year = (String) pvIter.getCurrentRow().getAttribute("FinYear");
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");

        
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate");
        
        
        Random rand = new Random();

        int num = rand.nextInt(9023632) + 1000000;
        
        System.out.println("==========="+num);
        
        // ADFUtils.setValueToPageFlowScope("LV_SID",num);
        ADFContext adfCtx = ADFContext.getCurrent();
        Map pageFlowScope = adfCtx.getPageFlowScope();
        Object val = pageFlowScope.put("Sid", num);
        
        

//        String sId = oracle.jbo.server.uniqueid.UniqueIdHelper.getNextId().toString();
//        
//        System.out.println("SID======="+sId);
//        
//        // ADFUtils.setValueToPageFlowScope("LV_SID",num);
//        ADFContext adfCtx = ADFContext.getCurrent();
//        Map pageFlowScope = adfCtx.getPageFlowScope();
//        Object val = pageFlowScope.put("Sid",  sId);
        
        System.out.println("Parameters :- fromDate:" + fromDate + " ||UNIT CODE:" + Unit + "|| toDate:" + toDate + "||ItemCd" + ItemCd + "||GroupCd" + GroupCd +"||SubGroupCd" + SubGroupCd);

        
                BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        OperationBinding operationBinding = bindings.getOperationBinding("callStockLedger3");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_ITEM_CD",ItemCd);
        operationBinding.getParamsMap().put("LV_SID",num);
        operationBinding.getParamsMap().put("LV_GROUP_CD",GroupCd);
        operationBinding.getParamsMap().put("LV_SUB_GROUP_CD", SubGroupCd);
        operationBinding.getParamsMap().put("LV_LOC", Location);
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
