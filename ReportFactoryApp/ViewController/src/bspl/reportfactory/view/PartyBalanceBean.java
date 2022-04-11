package bspl.reportfactory.view;

import bspl.reportfactory.bean.ADFUtils;

import java.util.Map;
import java.util.Random;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class PartyBalanceBean {

    private RichInputText unit;

    public PartyBalanceBean() {
    }

    public void OnClickFind(ActionEvent actionEvent) {

        DCIteratorBinding pvIter = (DCIteratorBinding) getBindings().get("DummyVVO1Iterator");
        String Unit = (String) pvIter.getCurrentRow().getAttribute("UnitCode");
        String Gl = (String) pvIter.getCurrentRow().getAttribute("GlType");
       // String SessionId = (String) pvIter.getCurrentRow().getAttribute("SessionId");
        String Year = (String) pvIter.getCurrentRow().getAttribute("FinYear");
        oracle.jbo.domain.Date fromDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("fromDate");
        oracle.jbo.domain.Date toDate = (oracle.jbo.domain.Date) pvIter.getCurrentRow().getAttribute("toDate");
               
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
        OperationBinding operationBinding = bindings.getOperationBinding("callPartBalProc");
        operationBinding.getParamsMap().put("LV_UNIT",Unit);
        operationBinding.getParamsMap().put("LV_GL_TYPE",Gl);
        operationBinding.getParamsMap().put("LV_SID",num);
        operationBinding.getParamsMap().put("LV_YEAR",Year);
        operationBinding.getParamsMap().put("FRDATE",fromDate);
        operationBinding.getParamsMap().put("TODATE",toDate);
        Object result = operationBinding.execute();
        
        
//        DCIteratorBinding pvIter1 = (DCIteratorBinding) getBindings().get("PartyBalancesVO1Iterator");
//        pvIter1.executeQuery();
//        
//        System.out.println("=="+pvIter1.getDeferredEstimatedRowCount());
    }
       
       
       
       
        public BindingContainer getBindings() {
            return BindingContext.getCurrent().getCurrentBindingsEntry();
        }

    public void setUnit(RichInputText unit) {
        this.unit = unit;
    }

    public RichInputText getUnit() {
        return unit;
    }
}
