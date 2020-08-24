package ui.automatic_recycler.helper;

import java.util.HashMap;

import ui.automatic_recycler.RefreshManager;
import ui.automatic_recycler.SimpleItem;

public class RefreshManagerHelper {

    public static void init(){
        RefreshManager.initMap(new RefreshManager.ItemTypeInject(){

            @Override
            public void inject(HashMap<String, Class<? extends SimpleItem>> hashMap) {

            }
        });
    }
}
