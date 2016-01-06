package com.pistolcaffe.lib.pistolrecyclerview;

/**
 * Created by Administrator on 2016-01-04.
 */
public class NotRegisteredListInfoException extends RuntimeException {
    public NotRegisteredListInfoException() {
        super("should invoke setListInfo() before invoked setAdapter()");
    }
}
