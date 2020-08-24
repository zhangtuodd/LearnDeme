package ui.automatic_recycler;

import java.util.HashMap;

import ui.automatic_recycler.model.ItemTypeModel;

public class RefreshManager {


    public static void initMap(ItemTypeInject itemTypeInject) {
        itemTypeInject.inject(ItemTypeModel.sHashMap);
    }

    public interface ItemTypeInject {
        void inject(HashMap<String, Class<? extends SimpleItem>> hashMap);
    }
}
