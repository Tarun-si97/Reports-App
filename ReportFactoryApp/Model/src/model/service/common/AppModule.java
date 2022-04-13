package model.service.common;

import java.util.Date;

import oracle.jbo.ApplicationModule;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Mar 24 12:13:58 IST 2022
// ---------------------------------------------------------------------
public interface AppModule extends ApplicationModule {
    void callGenLedgerproc(String LV_UNIT, String LV_GLCD, String LV_SID, String LV_YEAR, Date FRDATE, Date TODATE);

    void callPartBalProc(String LV_UNIT, String LV_GL_TYPE, String LV_SID, String LV_YEAR, Date FRDATE, Date TODATE);

    void getData();

    void callSubLedgerproc(String LV_UNIT, String LV_GLCD, String LV_SID, String LV_YEAR, Date FRDATE, Date TODATE);

    void callWIPStock(String LV_UNIT, String LOC, Date FRDATE, Date TODATE);


    void callB2bSaleInvoiceRegistered(String LV_UNIT, String CUSTOMER, Date FRDATE, Date TODATE);


    void callDebitCreditNoteEntryDetail(String LV_UNIT, Date FRDATE, Date TODATE);


    void callAssemblyProductionSummary(String LV_UNIT, Date FRDATE, Date TODATE, String LV_PRODUCT);

    void callB2CSaleInvoiceUnRegistered(String LV_UNIT, Date FRDATE, Date TODATE);

    void callGatePassChallan(String LV_UNIT, Date FRDATE, Date TODATE);

    void callPendingFinishGoodsBatchWise(String LV_UNIT);

    void callProcessProduction(String LV_UNIT, Date FRDATE, Date TODATE);

    void callPurchaseRegisterIncludingDRandCRNote(String LV_UNIT, String LV_DOC_TYPE, Date FRDATE, Date TODATE);

    void callSaleRegisterDetail4(String LV_UNIT, Integer LV_SID, String LV_INC_CAN, Date FRDATE, Date TODATE);

    void callStockLedger3(String LV_UNIT, String LV_ITEM_CD, String LV_SID, String LV_LOC, Date FRDATE, Date TODATE,
                          String LV_GROUP_CD, String LV_SUB_GROUP_CD);

    void callStockStatus(String LV_UNIT, String LV_ITEM_CD, String LV_FIN_YEAR);
}

