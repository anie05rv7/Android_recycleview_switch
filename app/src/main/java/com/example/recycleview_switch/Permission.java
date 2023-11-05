package com.example.recycleview_switch;


public class Permission {
    private String name;
    private boolean isGranted;

    public Permission(String name, boolean isGranted) {
        this.name = name;
        this.isGranted = isGranted;
    }

    public String getName() {
        return name;
    }

    public boolean isGranted() {
        return isGranted;
    }

    public void setGranted(boolean granted) {
        isGranted = granted;
    }
}
