package com.smartrac.objectexplore.interfaces;

public interface SSFilter<T, E> {
	boolean isMatched(T object, E text);
}
