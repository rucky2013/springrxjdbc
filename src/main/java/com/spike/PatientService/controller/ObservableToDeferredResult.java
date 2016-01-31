package com.spike.PatientService.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;

@Component
public class ObservableToDeferredResult {

    @Value("${response.timeout}")
    private long timeOut;

    public <T> DeferredResult<List<T>> getListAsDeferredResult(Observable<T> observable){
        DeferredResult<List<T>> deferredResult = new DeferredResult<>(timeOut);
        List<T> arrayList = new ArrayList<>();
        observable.subscribe(add -> arrayList.add(add),
                error -> deferredResult.setErrorResult(error),
                () -> deferredResult.setResult(arrayList));
        return deferredResult;
    }
}
