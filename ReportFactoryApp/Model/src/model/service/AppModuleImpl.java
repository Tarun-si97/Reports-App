package model.service;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Timestamp;
import java.sql.Types;

import java.util.Date;


import javax.naming.InitialContext;

import javax.sql.DataSource;


import model.service.common.AppModule;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.JboException;
import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewObject;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Mar 08 11:44:54 IST 2022
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AppModuleImpl extends ApplicationModuleImpl implements AppModule {
    /**
     * This is the default constructor (do not remove).
     */
    public AppModuleImpl() {
    }

    /**
     * Container's getter for PartyBalancesVO1.
     * @return PartyBalancesVO1
     */
    public ViewObjectImpl getPartyBalancesVO1() {
        return (ViewObjectImpl) findViewObject("PartyBalancesVO1");
    }
    
    public void callPartBalProc(String LV_UNIT,String LV_GL_TYPE,String LV_SID,String LV_YEAR,Date FRDATE,Date TODATE) {
        
          ViewObjectImpl vo = this.getPartyBalancesVO1();
          System.out.println(LV_GL_TYPE + "--" + LV_UNIT + "--" + LV_SID + "--" + FRDATE + "--" + TODATE+"--"+LV_YEAR);
          if (LV_GL_TYPE != null && LV_UNIT != null && LV_SID != null && FRDATE != null && TODATE != null) {
              System.out.println("Inside condition!");
              try {

                  callStoredProcedure("terms.PARTY_BAL_PROC_APX(?,?,?,?,?,?)", new Object[]
                                      { LV_UNIT, LV_GL_TYPE, LV_SID, LV_YEAR, FRDATE, TODATE });         
                 // vo.setNamedWhereClauseParam("bindModeType", "V");
                  vo.setNamedWhereClauseParam("bindSession", LV_SID);
                  vo.executeQuery();

              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
               
           }
           
    
    protected void callStoredProcedure(String stmt, Object[] bindVars) {
        PreparedStatement st = null;
        try {
            // 1. Create a JDBC PreparedStatement for
            st = getDBTransaction().createPreparedStatement("begin " + stmt + ";end;", 0);
            if (bindVars != null) {
                // 2. Loop over values for the bind variables passed in, if any
                for (int z = 0; z < bindVars.length; z++) {
                    // 3. Set the value of each bind variable in the statement
                    st.setObject(z + 1, bindVars[z]);
                }
            }
            // 4. Execute the statement
            st.executeUpdate();
        } catch (SQLException e) {
            throw new JboException(e);
        } finally {
            if (st != null) {
                try {
                    // 5. Close the statement
                    st.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    /**
     * Container's getter for DummyVVO1.
     * @return DummyVVO1
     */
    public ViewObjectImpl getDummyVVO1() {
        return (ViewObjectImpl) findViewObject("DummyVVO1");
    }
    
    public void getData(){
        
        ViewObjectImpl VOI = this.getPartyBalancesVO1();
        VOI.clearCache();
        VOI.executeQuery();
        
        ViewCriteria hVc = this.getPartyBalancesVO1().getViewCriteria("PartyBalancesVOCriteria");
        this.getPartyBalancesVO1().applyViewCriteria(hVc, false);
        this.getPartyBalancesVO1().setNamedWhereClauseParam("bindunit", 0000);
       // VOI.executeQuery();
       // VOI.clearCache();
        
        this.getPartyBalancesVO1().executeQuery();
    }

    /**
     * Container's getter for GeneralLedgerVO1.
     * @return GeneralLedgerVO1
     */
    public ViewObjectImpl getGeneralLedgerVO1() {
        return (ViewObjectImpl) findViewObject("GeneralLedgerVO1");
    }
    public void callGenLedgerproc(String LV_UNIT,String LV_GLCD,String LV_SID,String LV_YEAR,Date FRDATE,Date TODATE){
        ViewObjectImpl vo = this.getGeneralLedgerVO1();
        System.out.println(LV_GLCD + "--" + LV_UNIT + "--" + LV_SID + "--" + FRDATE + "--" + TODATE+"--"+LV_YEAR);
        
        
        if (LV_GLCD != null && LV_UNIT != null && LV_SID != null && FRDATE != null && TODATE != null) {
            System.out.println("Inside condition!");
            try {

                callStoredProcedure("terms.GEN_LED_PROC_APX(?,?,?,?,?,?)", new Object[]
                                    { LV_UNIT, LV_GLCD, LV_SID, LV_YEAR, FRDATE, TODATE });         
               // vo.setNamedWhereClauseParam("bindModeType", "V");
                vo.setNamedWhereClauseParam("bindSession", LV_SID);
                vo.executeQuery();

            } catch (Exception e) {
                e.printStackTrace();
    }
    
        }
     }

    /**
     * Container's getter for GlCodeVVO1.
     * @return GlCodeVVO1
     */
    public ViewObjectImpl getGlCodeVVO1() {
        return (ViewObjectImpl) findViewObject("GlCodeVVO1");
    }
    private Connection getConnection() {
        Connection connection = null;
        try {
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/APPLICATIONDBDS");
            connection = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static final ADFLogger logger = ADFLogger.createADFLogger(AppModuleImpl.class);

    /**
     * Container's getter for UserSessionVO1.
     * @return UserSessionVO1
     */
    public ViewObjectImpl getUserSessionVO1() {
        return (ViewObjectImpl) findViewObject("UserSessionVO1");
    }

    /**
     * Container's getter for DBSidSeqVVO1.
     * @return DBSidSeqVVO1
     */
    public ViewObjectImpl getDBSidSeqVVO1() {
        return (ViewObjectImpl) findViewObject("DBSidSeqVVO1");
    }


    /**
     * Container's getter for SubLedgerVO1.
     * @return SubLedgerVO1
     */
    public ViewObjectImpl getSubLedgerVO1() {
        return (ViewObjectImpl) findViewObject("SubLedgerVO1");
    }
    
    public void callSubLedgerproc(String LV_UNIT,String LV_GLCD,String LV_SID,String LV_YEAR,Date FRDATE,Date TODATE){
        ViewObjectImpl vo = this.getSubLedgerVO1();
        System.out.println(LV_GLCD + "--" + LV_UNIT + "--" + LV_SID + "--" + FRDATE + "--" + TODATE+"--"+LV_YEAR);
        
        
        if (LV_GLCD != null && LV_UNIT != null && LV_SID != null && FRDATE != null && TODATE != null) {
            System.out.println("Inside condition!");
            try {

                callStoredProcedure("terms.SUB_LED_TRIAL_PROC_APX(?,?,?,?,?,?)", new Object[]
                                    { LV_UNIT, LV_GLCD, LV_SID, LV_YEAR, FRDATE, TODATE });         
               // vo.setNamedWhereClauseParam("bindModeType", "V");
                vo.setNamedWhereClauseParam("bindSession", LV_SID);
                vo.executeQuery();

            } catch (Exception e) {
                e.printStackTrace();
    }
    
        }
     }


    /**
     * Container's getter for LocVVO1.
     * @return LocVVO1
     */
    public ViewObjectImpl getLocVVO1() {
        return (ViewObjectImpl) findViewObject("LocVVO1");
    }

    /**
     * Container's getter for WIPStockVO1.
     * @return WIPStockVO1
     */
    public ViewObjectImpl getWIPStockVO1() {
        return (ViewObjectImpl) findViewObject("WIPStockVO1");
     
    }
    public void callWIPStock(String LV_UNIT,String LOC,Date FRDATE,Date TODATE){
        ViewObjectImpl vo = this.getWIPStockVO1();
        System.out.println(LOC + "--" + LV_UNIT + "--"  + "--" + FRDATE + "--" + TODATE+"--");
        
    if (LOC != null && LV_UNIT != null &&  FRDATE != null && TODATE != null) {
        System.out.println("Inside condition!");
    try {

        
       
        vo.setNamedWhereClauseParam("P40_FROM_DT", FRDATE);
        vo.setNamedWhereClauseParam("P40_LOC", LOC);
        vo.setNamedWhereClauseParam("P40_TO_DT", TODATE);
        vo.setNamedWhereClauseParam("P40_UNIT", LV_UNIT);
       
        vo.executeQuery();
        
        

    } catch (Exception e) {
        e.printStackTrace();
    
}
}
    }

    /**
     * Container's getter for PendingFinishGoodsBatchWiseVO1.
     * @return PendingFinishGoodsBatchWiseVO1
     */
    public ViewObjectImpl getPendingFinishGoodsBatchWiseVO1() {
        return (ViewObjectImpl) findViewObject("PendingFinishGoodsBatchWiseVO1");
    }
    
    public void getPendingFinishGoodsBatchWise (String LV_UNIT){
        ViewObjectImpl vo = this.getPendingFinishGoodsBatchWiseVO1();
    System.out.println(LV_UNIT + "--");
    
    if ( LV_UNIT != null) {
        System.out.println("Inside condition!");
    try {

        
        vo.setNamedWhereClauseParam("p36_unit_code", LV_UNIT);
       
        vo.executeQuery();
        
        

    } catch (Exception e) {
        e.printStackTrace();
    
    
                                        
                                    
        
        
}
    }
    }

    /**
     * Container's getter for B2bSaleInvoiceRegisteredVO1.
     * @return B2bSaleInvoiceRegisteredVO1
     */
    public ViewObjectImpl getB2bSaleInvoiceRegisteredVO1() {
        return (ViewObjectImpl) findViewObject("B2bSaleInvoiceRegisteredVO1");
    }
    
    public void callB2bSaleInvoiceRegistered(String LV_UNIT,String CUSTOMER ,Date FRDATE,Date TODATE){
        ViewObjectImpl vo = this.getB2bSaleInvoiceRegisteredVO1();
        System.out.println(CUSTOMER + "--" + LV_UNIT + "--"  + "--" + FRDATE + "--" + TODATE+"--");
        
    if (CUSTOMER != null && LV_UNIT != null &&  FRDATE != null && TODATE != null) {
        System.out.println("Inside condition!");
    try {

        
       
        vo.setNamedWhereClauseParam("P174_FROM_DATE", FRDATE);
        vo.setNamedWhereClauseParam("P174_CUSTOMER", CUSTOMER);
        vo.setNamedWhereClauseParam("P174_TO_DATE", TODATE);
        vo.setNamedWhereClauseParam("P174_UNIT", LV_UNIT);
       
        vo.executeQuery();
        
        

    } catch (Exception e) {
        e.printStackTrace();
    
    }
    }
    }

    /**
     * Container's getter for StockLedgerVO1.
     * @return StockLedgerVO1
     */
    public ViewObjectImpl getStockLedgerVO1() {
        return (ViewObjectImpl) findViewObject("StockLedgerVO1");
    }
    
    public void StockLedger3(String LV_UNIT,String LV_ITEM_CD,String LV_SID,String LV_LOC,Date FRDATE,Date TODATE,
                             String LV_GROUP_CD, String LV_SUB_GROUP_CD ){
        ViewObjectImpl vo = this.getStockLedgerVO1();
        System.out.println( LV_UNIT + "--" + LV_SID + "--" + FRDATE + "--" + TODATE+"--"+LV_ITEM_CD + "--" + LV_LOC+ "---"
                           /*+ LV_GROUP_CD +"--" + LV_SUB_GROUP_CD + "--"*/);
        
        
        if ( LV_UNIT != null && LV_SID != null && FRDATE != null && TODATE != null && LV_ITEM_CD != null &&
        
                         LV_LOC != null /*&& LV_GROUP_CD !=null && LV_SUB_GROUP_CD !=null */ ) {
            System.out.println("Inside condition!");
            try {

                callStoredProcedure("terms.DPR_STOCK_VAL_WOPN(?,?,?,?,?,?)", new Object[]
                                    { FRDATE,TODATE,LV_LOC,LV_ITEM_CD, LV_SID,LV_UNIT });         
              
                vo.setNamedWhereClauseParam("bindSession", LV_SID);
                vo.setNamedWhereClauseParam("P70_FR_Date", FRDATE);
                vo.setNamedWhereClauseParam("P70_TO_Date", TODATE);
//                vo.setNamedWhereClauseParam("P70_GROUP_CODE", LV_GROUP_CD);
                vo.setNamedWhereClauseParam("P70_ITEM", LV_ITEM_CD);
//                vo.setNamedWhereClauseParam("P70_SUB_GROUP", LV_SUB_GROUP_CD);
                vo.setNamedWhereClauseParam("p70_unit", LV_UNIT);
//                vo.setNamedWhereClauseParam("P70_UNIT", LV_LOC);
                vo.executeQuery();

            } catch (Exception e) {
                e.printStackTrace();
    }
    
        }
     }


    /**
     * Container's getter for B2CSaleInvoiceUnRegisteredVO1.
     * @return B2CSaleInvoiceUnRegisteredVO1
     */
    public ViewObjectImpl getB2CSaleInvoiceUnRegisteredVO1() {
        return (ViewObjectImpl) findViewObject("B2CSaleInvoiceUnRegisteredVO1");
    }
    
    public void B2CSaleInvoiceUnRegistered(String LV_UNIT,Date FRDATE,Date TODATE){
          ViewObjectImpl vo = this.getB2CSaleInvoiceUnRegisteredVO1();
          System.out.println( LV_UNIT + "--"  + "--" + FRDATE + "--" + TODATE+"--");
          
      if (LV_UNIT != null &&  FRDATE != null && TODATE != null) {
          System.out.println("Inside condition!");
      try {

          
         
          vo.setNamedWhereClauseParam("P175_FROM_DATE", FRDATE);
      
          vo.setNamedWhereClauseParam("P175_TO_DATE", TODATE);
          vo.setNamedWhereClauseParam("P175_UNIT", LV_UNIT);
         
          vo.executeQuery();
          
      } catch (Exception e) {
          e.printStackTrace();
      
      }
      }
    
}

    /**
     * Container's getter for LocProdWipVVO1.
     * @return LocProdWipVVO1
     */
    public ViewObjectImpl getLocProdWipVVO1() {
        return (ViewObjectImpl) findViewObject("LocProdWipVVO1");
    }

    /**
     * Container's getter for SaleregisterDetailVO1.
     * @return SaleregisterDetailVO1
     */
    public ViewObjectImpl getSaleregisterDetailVO1() {
        return (ViewObjectImpl) findViewObject("SaleregisterDetailVO1");
    }
    
    public void SaleRegisterDetail4(String LV_UNIT, Integer LV_SID,String LV_INC_CAN, Date FRDATE, Date TODATE){
        ViewObjectImpl vo = this.getSaleregisterDetailVO1();
    String LV_INV_TYPE = "";
    String LV_CUST = "";
        System.out.println(LV_UNIT + "--" + LV_INC_CAN +"---" + FRDATE +"--" + TODATE +"--"  /*LV_INV_TYPE + "--" + LV_CUST + "--"*/ );
        
        if (LV_UNIT != null && FRDATE !=null && TODATE !=null /*&& LV_INV_TYPE ==null && LV_CUST==null */){
            
            
            System.out.println("inside condition");
            
            try {

                callStoredProcedure("terms.Sales_Rep_Proc_Det(?,?,?,?,?,?)", new Object[]
                                    { FRDATE,TODATE,LV_UNIT,LV_INV_TYPE, LV_CUST ,LV_SID});         
              
                vo.setNamedWhereClauseParam("bindSession", LV_SID);
                vo.setNamedWhereClauseParam("P12_INC_CAN", LV_INC_CAN);
                
                     
                vo.executeQuery();

            } catch (Exception e) {
                e.printStackTrace();
            
        }
        
        
    }
    
}

    /**
     * Container's getter for StockStatusVO1.
     * @return StockStatusVO1
     */
    public ViewObjectImpl getStockStatusVO1() {
        return (ViewObjectImpl) findViewObject("StockStatusVO1");
    }
    
    public void StockStatus(String LV_UNIT,String LV_ITEM_CD, String LV_FIN_YEAR){
        ViewObjectImpl vo = this.getStockStatusVO1();
        System.out.println(LV_UNIT + "--" + LV_ITEM_CD + "--"   + LV_FIN_YEAR + "--" );
        
    if ( LV_UNIT != null &&  LV_ITEM_CD != null && LV_FIN_YEAR != null) {
        System.out.println("Inside condition!");
    try {

        
       
        
        vo.setNamedWhereClauseParam("P14_FIN_YEAR", LV_FIN_YEAR);
        vo.setNamedWhereClauseParam("P14_ITEM", LV_ITEM_CD);
        vo.setNamedWhereClauseParam("P14_UNIT", LV_UNIT);
       
        vo.executeQuery();
        
        

    } catch (Exception e) {
        e.printStackTrace();
    
    }
    }
    
    
}

    /**
     * Container's getter for PurchaseRegisterIncludingDRandCRNoteVO1.
     * @return PurchaseRegisterIncludingDRandCRNoteVO1
     */
    public ViewObjectImpl getPurchaseRegisterIncludingDRandCRNoteVO1() {
        return (ViewObjectImpl) findViewObject("PurchaseRegisterIncludingDRandCRNoteVO1");
    }
    
    public void PurchaseRegisterIncludingDRandCRNote(String LV_UNIT,String LV_DOC_TYPE,Date FRDATE,Date TODATE){
        ViewObjectImpl vo = this.getPurchaseRegisterIncludingDRandCRNoteVO1();
        System.out.println(LV_DOC_TYPE + "--" + LV_UNIT + "--"  + "--" + FRDATE + "--" + TODATE+"--");
        
    if (LV_DOC_TYPE != null && LV_UNIT != null &&  FRDATE != null && TODATE != null) {
        System.out.println("Inside condition!");
    try {

        
       
        vo.setNamedWhereClauseParam("P254_FROM_DATE", FRDATE);
        vo.setNamedWhereClauseParam("P254_DOC_TYPE", LV_DOC_TYPE);
        vo.setNamedWhereClauseParam("P254_TO_DATE", TODATE);
        vo.setNamedWhereClauseParam("P254_UNIT", LV_UNIT);
       
        vo.executeQuery();
        
        

    } catch (Exception e) {
        e.printStackTrace();
    
    }
    }
    }


    /**
     * Container's getter for DebitCreditNoteEntryDetailVO1.
     * @return DebitCreditNoteEntryDetailVO1
     */
    public ViewObjectImpl getDebitCreditNoteEntryDetailVO1() {
        return (ViewObjectImpl) findViewObject("DebitCreditNoteEntryDetailVO1");
    }
    
    public void callDebitCreditNoteEntryDetail(String LV_UNIT,Date FRDATE,Date TODATE){
          ViewObjectImpl vo = this.getDebitCreditNoteEntryDetailVO1();
          System.out.println( LV_UNIT + "--"  + "--" + FRDATE + "--" + TODATE+"--");
          
      if (LV_UNIT != null &&  FRDATE != null && TODATE != null) {
          System.out.println("Inside condition!");
      try {

          
         
          vo.setNamedWhereClauseParam("P250_FROM_DATE", FRDATE);
      
          vo.setNamedWhereClauseParam("P250_TO_DATE", TODATE);
          vo.setNamedWhereClauseParam("P250_UNIT_CODE", LV_UNIT);
         
          vo.executeQuery();
          
      } catch (Exception e) {
          e.printStackTrace();
      
      }
      }
    
    }


    /**
     * Container's getter for ProcessProductionVO1.
     * @return ProcessProductionVO1
     */
    public ViewObjectImpl getProcessProductionVO1() {
        return (ViewObjectImpl) findViewObject("ProcessProductionVO1");
    }
    
    public void processproduction(String LV_UNIT,Date FRDATE,Date TODATE){
          ViewObjectImpl vo = this.getProcessProductionVO1();
          System.out.println( LV_UNIT + "--"  + "--" + FRDATE + "--" + TODATE+"--");
          
      if (LV_UNIT != null &&  FRDATE != null && TODATE != null) {
          System.out.println("Inside condition!");
      try {

          
         
          vo.setNamedWhereClauseParam("P32_FR_DT", FRDATE);
      
          vo.setNamedWhereClauseParam("P32_TO_DT", TODATE);
          vo.setNamedWhereClauseParam("P32_UNIT", LV_UNIT);
         
          vo.executeQuery();
          
      } catch (Exception e) {
          e.printStackTrace();
      
      }
      }
    
    }

    /**
     * Container's getter for AssemblyProductionSummaryVO1.
     * @return AssemblyProductionSummaryVO1
     */
    public ViewObjectImpl getAssemblyProductionSummaryVO1() {
        return (ViewObjectImpl) findViewObject("AssemblyProductionSummaryVO1");
    }
    
    public void callAssemblyProductionSummary(String LV_UNIT,Date FRDATE,Date TODATE, String LV_PRODUCT){
          ViewObjectImpl vo = this.getAssemblyProductionSummaryVO1();
          System.out.println( LV_UNIT + "--"  + "--" + FRDATE + "--" + TODATE+"--" + LV_PRODUCT + "--");
          
      if (LV_UNIT != null &&  FRDATE != null && TODATE != null && LV_PRODUCT !=null) {
          System.out.println("Inside condition!");
      try {

          
         
          vo.setNamedWhereClauseParam("P142_FROM", FRDATE);
      
          vo.setNamedWhereClauseParam("P142_TO", TODATE);
          vo.setNamedWhereClauseParam("P142_UNIT", LV_UNIT);
          vo.setNamedWhereClauseParam("P142_PROD", LV_PRODUCT);
         
          vo.executeQuery();
          
      } catch (Exception e) {
          e.printStackTrace();
      
      }
      }
    
    }

}


