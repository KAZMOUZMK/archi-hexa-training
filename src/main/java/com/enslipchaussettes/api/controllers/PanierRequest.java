package com.enslipchaussettes.api.controllers;

public class PanierRequest{
    String sku;
    public PanierRequest(String sku)  {
        this.sku = sku;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }


}
