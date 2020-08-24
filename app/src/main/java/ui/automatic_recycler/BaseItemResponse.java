package ui.automatic_recycler;

import java.io.Serializable;

public class BaseItemResponse<T> implements Serializable {

    public int errCode = 0;

    public String errMessage = "";

    public byte duplicateId = 0;

    public int itemType = 0;

    public T t;

}
