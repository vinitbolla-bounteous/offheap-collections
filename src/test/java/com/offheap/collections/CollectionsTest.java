package com.offheap.collections;

import com.offheap.collections.list.OffHeapList;
import com.offheap.collections.map.OffHeapMap;
import com.offheap.collections.set.OffHeapSet;
import com.offheap.serialization.JavaSerializer;
import com.offheap.storage.DirectMemoryStorage;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CollectionsTest {
    // Update testOffHeapList in CollectionsTest.java
    // Update testOffHeapList in CollectionsTest.java
    @Test
    void testOffHeapList() {
        try (OffHeapList<String> list = new OffHeapList<>(new DirectMemoryStorage(), new JavaSerializer<>(), 10, false)) {
            list.add("A");
            list.add("C");
            list.add(1, "B");
            assertEquals(3, list.size());
            assertEquals("B", list.get(1));
            assertEquals(List.of("A", "B", "C"), list.stream().collect(Collectors.toList()));
            list.remove(1); // Use index 1 for "B"
            assertEquals(2, list.size());
            assertTrue(list.averageLatencyMs() > 0);
        }
    }

    @Test
    void testOffHeapMap() {
        try (OffHeapMap<String, Integer> map = new OffHeapMap<>(new DirectMemoryStorage(), new JavaSerializer<>(), new JavaSerializer<>(), 10, false)) {
            map.put("X", 1);
            map.put("Y", 2);
            assertEquals(2, map.size());
            assertEquals(2, map.get("Y"));
            assertEquals(1, map.remove("X"));
            assertEquals(1, map.size());
            assertTrue(map.averageLatencyMs() > 0);
        }
    }

    @Test
    void testOffHeapSet() {
        try (OffHeapSet<String> set = new OffHeapSet<>(new DirectMemoryStorage(), new JavaSerializer<>(), 10, false)) {
            set.add("P");
            set.add("Q");
            set.add("P"); // Duplicate
            assertEquals(2, set.size());
            assertTrue(set.contains("Q"));
            assertTrue(set.remove("P"));
            assertEquals(1, set.size());
            assertTrue(set.averageLatencyMs() > 0);
        }
    }
}