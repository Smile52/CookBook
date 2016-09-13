package com.smile.cookbook.imp;

/**
 * Created
 * DATE 09/09/16
 * TIME 11:09
 */
public interface ImpRequest<T> {

    void onSuccess(T t);

    void onFailure();

}
