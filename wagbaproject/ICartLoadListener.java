package com.example.wagbaproject;

import java.util.List;

public interface ICartLoadListener {
    void OnCartLoadSuccess(List<CartModel> cartmodelList);
    void onCartLoadFailed(String message);
}
