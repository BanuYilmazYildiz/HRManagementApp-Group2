package com.bilgeadam.constants;

public class RestApi {
    public static final String VERSION = "/api/v1";

    public static final String MANAGER = VERSION + "/manager";

    public static final String UPDATE = "/update";
    public static final String FINDBYIDDETAIL = "/find-by-id-detail/{userId}";
    public static final String FINDBYID2 = "/find-by-id2/{userId}";
    public static final String CREATE = "/create";
    public static final String IMAGE_UPLOAD = "/image-upload";
    public static final String FIND_ALL_EXPENSE_FOR_MANAGER = "/find-all-expense-for-manager";

    public static final String EXPENSE_APPROVE = "/expense-approve";
    public static final String ADVANCE_APPROVE = "/advance-approve";
    public static final String PERMISSION_APPROVE = "/permission-approve";
    public static final String FIND_ALL_ADVANCE_FOR_MANAGER = "/find-all-advance-for-manager";
    public static final String FIND_ALL_PERMISSION_FOR_MANAGER = "/find-all-permission-for-manager";

    //-------------------------------------------------------------------------------------------
    public static final String COMPANY_DETAIL = "/company-detail";
    public static final String FINDALL_COMPANY = "/find-all-company";
    public static final String COMPANY_DELETEBYID = "/company-deletebyid";
    public static final String COMPANY_CREATE = "/company-create";
    public static final String COMPANY_UPDATE = "/company-update";

}
