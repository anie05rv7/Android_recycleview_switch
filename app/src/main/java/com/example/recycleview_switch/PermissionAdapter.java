package com.example.recycleview_switch;

import android.Manifest;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PermissionAdapter extends RecyclerView.Adapter<PermissionAdapter.PermissionViewHolder> {
    private List<Permission> permissions;
    private PermissionRequester permissionRequester;

    public PermissionAdapter(List<Permission> permissions, PermissionRequester permissionRequester) {
        this.permissions = permissions;
        this.permissionRequester = permissionRequester;
    }

    @NonNull
    @Override
    public PermissionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new PermissionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PermissionViewHolder holder, int position) {
        Permission permission = permissions.get(position);
        holder.permissionNameTextView.setText(permission.getName());
        holder.permissionSwitch.setChecked(permission.isGranted());
        holder.permissionSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            permission.setGranted(isChecked);
            if (isChecked) {
                switch (position) {
                    case 0:
                        permissionRequester.requestGalleryPermission();
                        break;
                    case 1:
                        permissionRequester.requestCallPermission();
                        break;
                    case 2:
                        permissionRequester.requestContactPermission();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return permissions.size();
    }

    static class PermissionViewHolder extends RecyclerView.ViewHolder {
        TextView permissionNameTextView;
        Switch permissionSwitch;

        public PermissionViewHolder(@NonNull View itemView) {
            super(itemView);
            permissionNameTextView = itemView.findViewById(R.id.permission_name);
            permissionSwitch = itemView.findViewById(R.id.permission_switch);
        }
    }

    public interface PermissionRequester {
        void requestGalleryPermission();
        void requestCallPermission();
        void requestContactPermission();
    }
}
