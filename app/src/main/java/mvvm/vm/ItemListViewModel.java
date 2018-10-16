package mvvm.vm;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.subjects.BehaviorSubject;
import mvvm.Navigator;

/**
 * recyclerView 对应的viewmodel
 *
 * @author zhangtuo
 * @date 2018/10/15
 */

public class ItemListViewModel implements ViewModel {

    public final Observable<List<ItemViewModel>> itemVms;

    private static final Observable<List<String>> itemSource;

    static {
        List<String> items = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            items.add("number : " + i);
        }
        itemSource = BehaviorSubject.createDefault(items);
    }

    public ItemListViewModel(final Navigator navigator) {
        this.itemVms = itemSource.map(new Function<List<String>, List<ItemViewModel>>() {
            @Override
            public List<ItemViewModel> apply(List<String> items) throws Exception {
                List<ItemViewModel> viewModels = new ArrayList<>();
                for (String item : items) {
                    viewModels.add(new ItemViewModel(item, navigator));
                }
                return viewModels;
            }
        });
    }

}
