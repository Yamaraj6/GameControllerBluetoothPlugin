package com.unityplugin.bluetoothplugin;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class PermissionFragment extends Fragment
{
    // This is used while requesting for permission
    public static final int PERMISSION_REQUEST_CODE = 1;

    // Required permissions list
    private String[] s_required_permissions;
    private boolean mAskedPermission = false;

    public PermissionFragment(String[] sRequiredPermissions)
    {
        s_required_permissions = sRequiredPermissions;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        checkThemePermissions();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void checkThemePermissions()
    {
        // Check and request for permissions for Android M and older versions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !mAskedPermission)
        {
            ArrayList<String> requiredPermissions = new ArrayList<String>();

            for (String perm : s_required_permissions)
                if (getActivity().checkSelfPermission(perm) != PackageManager.PERMISSION_GRANTED)
                    requiredPermissions.add(perm);

            if (requiredPermissions.size() > 0)
                this.requestPermissions(requiredPermissions.toArray(new String[requiredPermissions.size()]), PERMISSION_REQUEST_CODE);
        }
        mAskedPermission = true;
    }
}