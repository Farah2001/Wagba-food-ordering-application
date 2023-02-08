package com.example.wagbaproject;

import java.util.List;

public interface IDishesLoadListener {
    void onDishLoadSuccess(List<DishesModels> dishmodelList);
    void onDishLoadFailed(String message);
}
