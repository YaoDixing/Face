package com.yaodixing.permissionlibrary.permission;

import java.util.List;

/**
 * Created by lenovo on 2016/8/29.
 */
public interface INeedCheckPermission {
    void needCheckPermission(List<DangerousPermissionEnum> list);
}
