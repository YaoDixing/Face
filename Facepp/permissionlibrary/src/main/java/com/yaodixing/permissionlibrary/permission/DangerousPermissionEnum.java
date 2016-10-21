package com.yaodixing.permissionlibrary.permission;

/**
 * Created by ydx on 16-8-11.
 */

/**
 * 危险权限  6.0以后 需要在运行时获取相应权限
 * 例如 请求READ_SMS权限后 整个group.SMS的权限将全部赋予
 */
public enum DangerousPermissionEnum {
    //group:android.permission-group.CONTACTS
    WRITE_CONTACTS("android.permission.WRITE_CONTACTS", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_CONTACT),
    GET_ACCOUNTS("android.permission.GET_ACCOUNTS", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_CONTACT),
    READ_CONTACTS("android.permission.READ_CONTACTS", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_CONTACT),

    //group:android.permission-group.PHONE
    READ_CALL_LOG("android.permission.READ_CALL_LOG", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_PHONE),
    READ_PHONE_STATE("android.permission.READ_PHONE_STATE", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_PHONE),
    CALL_PHONE("android.permission.CALL_PHONE", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_PHONE),
    WRITE_CALL_LOG("android.permission.WRITE_CALL_LOG", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_PHONE),
    USE_SIP("android.permission.USE_SIP", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_PHONE),
    PROCESS_OUTGOING_CALLS("android.permission.PROCESS_OUTGOING_CALLS", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_PHONE),
    ADD_VOICEMAIL("android.voicemail.permission.ADD_VOICEMAIL", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_PHONE),

//    group:android.permission-group.CALENDAR
    READ_CALENDAR("android.permission.READ_CALENDAR", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_CALENDAR),
    WRITE_CALENDAR("android.permission.WRITE_CALENDAR", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_CALENDAR),

//    group:android.permission-group.CAMERA
    CAMEAR("android.permission.CAMERA", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_CAMERA),

//    group:android.permission-group.SENSORS
    BODY_SENSORS("android.permission.BODY_SENSORS", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_SENSORS),

//    group:android.permission-group.LOCATION
    ACCESS_FINE_LOCATION("android.permission.ACCESS_FINE_LOCATION", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_LOCATION),
    ACCESS_COARSE_LOCATION("android.permission.ACCESS_COARSE_LOCATION", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_LOCATION),

//    group:android.permission-group.STORAGE
    READ_EXTERNAL_STORAGE("android.permission.READ_EXTERNAL_STORAGE", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_STORAGE),
    WRITE_EXTERNAL_STORAGE("android.permission.WRITE_EXTERNAL_STORAGE", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_STORAGE),

//    group:android.permission-group.MICROPHONE
    RECORD_AUDIO("android.permission.RECORD_AUDIO", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_MICROPHONE),

//    group:android.permission-group.SMS
    READ_SMS("android.permission.READ_SMS", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_SMS),
    RECEIVE_WAP_PUSH ("android.permission.RECEIVE_WAP_PUSH", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_SMS),
    RECEIVE_MMS (" android.permission.RECEIVE_MMS", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_SMS),
    RECEIVE_SMS (" android.permission.RECEIVE_SMS", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_SMS),
    SEND_SMS("android.permission.SEND_SMS", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_SMS),
    READ_CELL_BROADCASTS("android.permission.READ_CELL_BROADCASTS", DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_SMS);


    String value;
    int reqCode;
    private DangerousPermissionEnum(String value,int reqCode){
        this.value=value;
        this.reqCode=reqCode;
    }

    public String getValue(){
        return value;
    }

    public int getReqCode(){
        return reqCode;
    }

}