package com.offheap.serialization;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayOutputStream;

public class KryoSerializer<T> implements Serializer<T> {
    private final Kryo kryo = new Kryo();
    private final Class<T> type;

    public KryoSerializer(Class<T> type) {
        this.type = type;
        kryo.register(type);
    }

    @Override
    public byte[] serialize(T obj) {
        try (Output output = new Output(new ByteArrayOutputStream())) {
            kryo.writeObject(output, obj);
            return output.toBytes();
        }
    }

    @Override
    public T deserialize(byte[] data) {
        try (Input input = new Input(data)) {
            return kryo.readObject(input, type);
        }
    }

    @Override
    public int estimatedSize() {
        return -1; // Variable, can override for fixed
    }
}