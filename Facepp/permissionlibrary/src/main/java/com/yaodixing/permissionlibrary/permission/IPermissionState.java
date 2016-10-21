package com.yaodixing.permissionlibrary.permission;

/**
 * Created by lenovo on 2016/9/9.
 */
public interface IPermissionState {
    void onGrant(int requestCode);
    void onRefuse(int requestCode);
}
