package service;

import java.util.List;

public interface ICrudService<T> {
    T get(String name);
    T create(List<String> optionList);
}
