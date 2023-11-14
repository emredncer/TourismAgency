package com.TourismAgencySystem.Operations;

import java.util.ArrayList;

public interface IOperations<T> {
    public void Create(T model);
    public T GetFetchById(int id);
    public ArrayList<T> GetAll();
    public void Update(T model);
    public void Delete(T model);
}