package com.offheap.serialization;

public interface Serializer<T> {
    byte[] serialize(T obj);
    T deserialize(byte[] data);
    int estimatedSize(); // -1 for variable size
}