package com.example.recycleview_switch;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback, PermissionAdapter.PermissionRequester {

    public static final int REQUEST_PERMISSIONS_CODE = 1001;
    private RecyclerView recyclerView;
    private List<Permission> permissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar el RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Agregar los permisos a la lista
        permissions = new ArrayList<>();
        permissions.add(new Permission("Galería", false));
        permissions.add(new Permission("Llamada", false));
        permissions.add(new Permission("Contactos", false));

        // Configurar el adaptador y establecerlo en el RecyclerView
        PermissionAdapter permissionAdapter = new PermissionAdapter(permissions, this);
        recyclerView.setAdapter(permissionAdapter);
    }

    @Override
    public void requestGalleryPermission() {
        requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    @Override
    public void requestCallPermission() {
        requestPermission(Manifest.permission.CALL_PHONE);
    }

    @Override
    public void requestContactPermission() {
        requestPermission(Manifest.permission.READ_CONTACTS);
    }

    private void requestPermission(String permission) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, REQUEST_PERMISSIONS_CODE);
        } else {
            Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    switch (permissions[i]) {
                        case Manifest.permission.READ_EXTERNAL_STORAGE:
                            break;
                        case Manifest.permission.CALL_PHONE:
                            break;
                        case Manifest.permission.READ_CONTACTS:
                            break;
                    }
                } else {
                    // aun no agrego la logica para esta parte

                }
            }
        }
    }

}
