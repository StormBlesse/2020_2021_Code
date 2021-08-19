import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class MihirKasmalkarJUnitsHW2 {

    private class TestObject {
        public int i;

        public TestObject(int i) {
            this.i = i;
        }

        public boolean equals(Object other) {
            if (!(other instanceof TestObject)) {
                return false;
            }

            return ((TestObject) other).i == i;
        }
    }

    private static final int TIMEOUT = 200;
    private CircularSinglyLinkedList<TestObject> list;

    @Before
    public void setUp() {
        list = new CircularSinglyLinkedList<>();
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void addAtIndex1a() {
        list.addAtIndex(-10, new TestObject(4));
    }

    @Test(timeout=TIMEOUT)
    public void addAtIndex1b() {
        list.addAtIndex(0, new TestObject(5));

        assertEquals(new TestObject(5), list.getHead().getData());
        assertSame(list.getHead(), list.getHead().getNext());
        assertEquals(1, list.size());
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void addAtIndex1c() {
        list.addAtIndex(1, new TestObject(20));
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void addAtIndex1d() {
        list.addAtIndex(5, new TestObject(4));
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void addAtIndex2a() {
        list.addAtIndex(0, new TestObject(4));

        list.addAtIndex(-4, new TestObject(3));
    }

    @Test(timeout=TIMEOUT)
    public void addAtIndex2b() {
        list.addAtIndex(0, new TestObject(50));

        list.addAtIndex(0, new TestObject(7));

        assertEquals(new TestObject(7), list.getHead().getData());
        assertEquals(2, list.size());
        assertEquals(new TestObject(50), list.getHead().getNext().getData());
        assertSame(list.getHead(), list.getHead().getNext().getNext());
    }

    @Test(timeout=TIMEOUT)
    public void addAtIndex2c() {
        list.addAtIndex(0, new TestObject(-10));

        list.addAtIndex(1, new TestObject(50));

        assertEquals(2, list.size());
        assertEquals(new TestObject(-10), list.getHead().getData());
        assertEquals(new TestObject(50), list.getHead().getNext().getData());
        assertSame(list.getHead(), list.getHead().getNext().getNext());
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void addAtIndex2d() {
        list.addAtIndex(0, new TestObject(44));

        list.addAtIndex(10, new TestObject(10));
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void addAtIndex3a() {
        list.addAtIndex(0, new TestObject(2));
        list.addAtIndex(0, new TestObject(3));
        list.addAtIndex(0, new TestObject(2));
        list.addAtIndex(0, new TestObject(2));
        list.addAtIndex(0, new TestObject(4));

        assertArrayEquals(new TestObject[] {
                new TestObject(4), new TestObject(2),
                new TestObject(2), new TestObject(3),
                new TestObject(2)
        }, list.toArray());

        list.addAtIndex(-5, new TestObject(4));
    }

    @Test(timeout=TIMEOUT)
    public void addAtIndex3b() {
        list.addAtIndex(0, new TestObject(2));
        list.addAtIndex(1, new TestObject(3));
        list.addAtIndex(2, new TestObject(2));
        list.addAtIndex(3, new TestObject(2));
        list.addAtIndex(4, new TestObject(4));

        assertArrayEquals(new TestObject[] {
                new TestObject(2), new TestObject(3),
                new TestObject(2), new TestObject(2),
                new TestObject(4)
        }, list.toArray());

        list.addAtIndex(0, new TestObject(5));

        assertEquals(new TestObject(5), list.getHead().getData());
        assertEquals(6, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()          // 5
                        .getNext()      // 2
                        .getNext()      // 3
                        .getNext()      // 2
                        .getNext()      // 2
                        .getNext()      // 4
                        .getNext());    // 5
    }

    @Test(timeout=TIMEOUT)
    public void addAtIndex3c() {
        list.addAtIndex(0, new TestObject(2));
        list.addAtIndex(1, new TestObject(3));
        list.addAtIndex(2, new TestObject(2));
        list.addAtIndex(3, new TestObject(2));
        list.addAtIndex(4, new TestObject(4));

        list.addAtIndex(1, new TestObject(5));
        assertArrayEquals(new TestObject[] {
                new TestObject(2),
                new TestObject(5),
                new TestObject(3),
                new TestObject(2),
                new TestObject(2),
                new TestObject(4)
        }, list.toArray());

        assertEquals(new TestObject(2), list.getHead().getData());
        assertEquals(6, list.size());
        assertEquals(new TestObject(5), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()       // 2
                        .getNext()      // 5
                        .getNext()      // 3
                        .getNext()      // 2
                        .getNext()      // 2
                        .getNext()      // 4
                        .getNext());    // 2

    }

    @Test(timeout=TIMEOUT)
    public void addAtIndex3d() {
        list.addAtIndex(0, new TestObject(2));
        list.addAtIndex(1, new TestObject(3));
        list.addAtIndex(2, new TestObject(2));
        list.addAtIndex(3, new TestObject(2));
        list.addAtIndex(4, new TestObject(4));

        list.addAtIndex(2, new TestObject(5));
        assertArrayEquals(new TestObject[] {
                new TestObject(2),
                new TestObject(3),
                new TestObject(5),
                new TestObject(2),
                new TestObject(2),
                new TestObject(4)
        }, list.toArray());

        assertEquals(new TestObject(2), list.getHead().getData());
        assertEquals(6, list.size());
        assertEquals(new TestObject(5), list.getHead()
                .getNext()
                .getNext()
                .getData());
        assertSame(list.getHead(),
                list.getHead()       // 2
                        .getNext()      // 3
                        .getNext()      // 5
                        .getNext()      // 2
                        .getNext()      // 2
                        .getNext()      // 4
                        .getNext());    // 2
    }

    @Test(timeout=TIMEOUT)
    public void addAtIndex3e() {
        list.addAtIndex(0, new TestObject(2));
        list.addAtIndex(1, new TestObject(3));
        list.addAtIndex(2, new TestObject(2));
        list.addAtIndex(3, new TestObject(2));
        list.addAtIndex(4, new TestObject(4));

        list.addAtIndex(3, new TestObject(5));
        assertArrayEquals(new TestObject[] {
                new TestObject(2),
                new TestObject(3),
                new TestObject(2),
                new TestObject(5),
                new TestObject(2),
                new TestObject(4)
        }, list.toArray());

        assertEquals(new TestObject(2), list.getHead().getData());
        assertEquals(6, list.size());
        assertEquals(new TestObject(5), list.getHead()
                .getNext()
                .getNext()
                .getNext()
                .getData());
        assertSame(list.getHead(),
                list.getHead()       // 2
                        .getNext()      // 3
                        .getNext()      // 2
                        .getNext()      // 5
                        .getNext()      // 2
                        .getNext()      // 4
                        .getNext());    // 2
    }

    @Test(timeout=TIMEOUT)
    public void addAtIndex3f() {
        list.addAtIndex(0, new TestObject(2));
        list.addAtIndex(1, new TestObject(3));
        list.addAtIndex(2, new TestObject(2));
        list.addAtIndex(3, new TestObject(2));
        list.addAtIndex(4, new TestObject(4));

        list.addAtIndex(4, new TestObject(5));
        assertArrayEquals(new TestObject[] {
                new TestObject(2),
                new TestObject(3),
                new TestObject(2),
                new TestObject(2),
                new TestObject(5),
                new TestObject(4)
        }, list.toArray());

        assertEquals(new TestObject(2), list.getHead().getData());
        assertEquals(6, list.size());
        assertEquals(new TestObject(5), list.getHead()
                .getNext()
                .getNext()
                .getNext()
                .getNext()
                .getData());
        assertSame(list.getHead(),
                list.getHead()       // 2
                        .getNext()      // 3
                        .getNext()      // 2
                        .getNext()      // 2
                        .getNext()      // 5
                        .getNext()      // 4
                        .getNext());    // 2
    }

    @Test(timeout=TIMEOUT)
    public void addAtIndex3g() {
        list.addAtIndex(0, new TestObject(2));
        list.addAtIndex(1, new TestObject(3));
        list.addAtIndex(2, new TestObject(2));
        list.addAtIndex(3, new TestObject(2));
        list.addAtIndex(4, new TestObject(4));

        list.addAtIndex(5, new TestObject(5));
        assertArrayEquals(new TestObject[] {
                new TestObject(2),
                new TestObject(3),
                new TestObject(2),
                new TestObject(2),
                new TestObject(4),
                new TestObject(5)
        }, list.toArray());

        assertEquals(new TestObject(2), list.getHead().getData());
        assertEquals(6, list.size());
        assertEquals(new TestObject(5), list.getHead()
                .getNext()
                .getNext()
                .getNext()
                .getNext()
                .getNext()
                .getData());
        assertSame(list.getHead(),
                list.getHead()       // 2
                        .getNext()      // 3
                        .getNext()      // 2
                        .getNext()      // 2
                        .getNext()      // 4
                        .getNext()      // 5
                        .getNext());    // 2
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void addAtIndex3h() {
        list.addAtIndex(0, new TestObject(2));
        list.addAtIndex(1, new TestObject(3));
        list.addAtIndex(2, new TestObject(2));
        list.addAtIndex(3, new TestObject(2));
        list.addAtIndex(4, new TestObject(4));

        assertArrayEquals(new TestObject[] {
                new TestObject(2),
                new TestObject(3),
                new TestObject(2),
                new TestObject(2),
                new TestObject(4),
        }, list.toArray());

        list.addAtIndex(6, new TestObject(5));
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void addAtIndex3i() {
        list.addAtIndex(0, new TestObject(2));
        list.addAtIndex(1, new TestObject(3));
        list.addAtIndex(2, new TestObject(2));
        list.addAtIndex(3, new TestObject(2));
        list.addAtIndex(4, new TestObject(4));

        assertArrayEquals(new TestObject[] {
                new TestObject(2),
                new TestObject(3),
                new TestObject(2),
                new TestObject(2),
                new TestObject(4),
        }, list.toArray());

        list.addAtIndex(10, new TestObject(5));
    }

    @Test(timeout=TIMEOUT, expected=IllegalArgumentException.class)
    public void addAtIndexNull() {
        list.addAtIndex(0, null);
    }


    @Test(timeout=TIMEOUT)
    public void addToFront1a() {
        list.addToFront(new TestObject(2));

        assertEquals(new TestObject(2), list.getHead().getData());
        assertSame(list.getHead(), list.getHead().getNext());
        assertEquals(1, list.size());
    }

    @Test(timeout=TIMEOUT)
    public void addToFront2a() {
        list.addToFront(new TestObject(2));

        list.addToFront(new TestObject(5));

        assertEquals(new TestObject(5), list.getHead().getData());
        assertEquals(2, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext());
    }

    @Test(timeout=TIMEOUT)
    public void addToFront2b() {
        TestObject toAdd = new TestObject(2);

        list.addToFront(toAdd);

        list.addToFront(toAdd);

        assertEquals(new TestObject(2), list.getHead().getData());
        assertEquals(2, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext());
    }

    @Test(timeout=TIMEOUT)
    public void addToFront3a() {
        list.addToFront(new TestObject(3));
        list.addToFront(new TestObject(5));

        list.addToFront(new TestObject(3));

        assertEquals(new TestObject(3), list.getHead().getData());
        assertEquals(3, list.size());
        assertEquals(new TestObject(5), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext());
    }

    @Test(timeout=TIMEOUT)
    public void addToFront3b() {
        TestObject toAdd = new TestObject(3);

        list.addToFront(new TestObject(5));
        list.addToFront(toAdd);

        list.addToFront(toAdd);

        assertEquals(new TestObject(3), list.getHead().getData());
        assertEquals(3, list.size());
        assertEquals(new TestObject(3), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext());
    }

    @Test(timeout=TIMEOUT)
    public void addToFront4a() {
        list.addToFront(new TestObject(1));
        list.addToFront(new TestObject(2));
        list.addToFront(new TestObject(3));
        list.addToFront(new TestObject(4));
        list.addToFront(new TestObject(5));

        list.addToFront(new TestObject(0));

        assertEquals(new TestObject(0), list.getHead().getData());
        assertEquals(6, list.size());
        assertEquals(new TestObject(5), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
    }

    @Test(timeout=TIMEOUT)
    public void addToFront4b() {
        TestObject toAdd = new TestObject(3);

        list.addToFront(new TestObject(1));
        list.addToFront(toAdd);
        list.addToFront(new TestObject(3));
        list.addToFront(toAdd);
        list.addToFront(toAdd);

        list.addToFront(toAdd);

        assertEquals(new TestObject(3), list.getHead().getData());
        assertEquals(6, list.size());
        assertEquals(new TestObject(3), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
    }

    @Test(timeout=TIMEOUT, expected=IllegalArgumentException.class)
    public void addToFrontNull() {
        list.addToFront(null);
    }

    @Test(timeout=TIMEOUT)
    public void addToBack1a() {
        list.addToBack(new TestObject(2));

        assertEquals(new TestObject(2), list.getHead().getData());
        assertSame(list.getHead(), list.getHead().getNext());
        assertEquals(1, list.size());
    }

    @Test(timeout=TIMEOUT)
    public void addToBack2a() {
        list.addToFront(new TestObject(2));
        list.addToBack(new TestObject(5));

        assertEquals(new TestObject(2), list.getHead().getData());
        assertEquals(2, list.size());
        assertEquals(new TestObject(5), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext());
    }

    @Test(timeout=TIMEOUT)
    public void addToBack2b() {
        TestObject toAdd = new TestObject(2);

        list.addToFront(toAdd);
        list.addToBack(toAdd);

        assertEquals(new TestObject(2), list.getHead().getData());
        assertEquals(2, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext());
    }

    @Test(timeout=TIMEOUT)
    public void addToBack3a() {
        list.addToFront(new TestObject(1));
        list.addToFront(new TestObject(2));
        list.addToBack(new TestObject(3));

        assertEquals(new TestObject(2), list.getHead().getData());
        assertEquals(3, list.size());
        assertEquals(new TestObject(1), list.getHead().getNext().getData());
        assertEquals(new TestObject(3), list.getHead()
                .getNext().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext());
    }

    @Test(timeout=TIMEOUT)
    public void addToBack3b() {
        TestObject toAdd = new TestObject(3);

        list.addToFront(toAdd);
        list.addToFront(new TestObject(10));

        list.addToBack(toAdd);

        assertEquals(new TestObject(10), list.getHead().getData());
        assertEquals(3, list.size());
        assertEquals(new TestObject(3), list.getHead().getNext().getData());
        assertEquals(new TestObject(3), list.getHead()
                .getNext().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext());
    }

    @Test(timeout=TIMEOUT)
    public void addToBack4a() {
        list.addToFront(new TestObject(1));
        list.addToFront(new TestObject(2));
        list.addToFront(new TestObject(3));
        list.addToFront(new TestObject(4));
        list.addToFront(new TestObject(5));

        list.addToBack(new TestObject(0));

        assertEquals(new TestObject(5), list.getHead().getData());
        assertEquals(6, list.size());
        assertEquals(new TestObject(4), list.getHead().getNext().getData());
        assertEquals(new TestObject(0), list.get(5));
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
    }

    @Test(timeout=TIMEOUT)
    public void addToBack4b() {
        TestObject toAdd = new TestObject(3);

        list.addToFront(toAdd);
        list.addToFront(new TestObject(2));
        list.addToFront(new TestObject(3));
        list.addToFront(new TestObject(4));
        list.addToFront(toAdd);

        list.addToBack(toAdd);

        assertEquals(new TestObject(3), list.getHead().getData());
        assertEquals(6, list.size());
        assertEquals(new TestObject(4), list.getHead().getNext().getData());
        assertEquals(new TestObject(3), list.get(5));
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
    }

    @Test(timeout=TIMEOUT, expected=IllegalArgumentException.class)
    public void addToBackNull() {
        list.addToBack(null);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void removeAtIndex1a() {
        list.removeAtIndex(-2);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void removeAtIndex1b() {
        list.removeAtIndex(0);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void removeAtIndex1c() {
        list.removeAtIndex(1);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void removeAtIndex1d() {
        list.removeAtIndex(3);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void removeAtIndex2a() {
        list.addToFront(new TestObject(4));

        list.removeAtIndex(-4);
    }

    @Test(timeout=TIMEOUT)
    public void removeAtIndex2b() {
        list.addToFront(new TestObject(4));

        TestObject removed = list.removeAtIndex(0);

        assertSame(null, list.getHead());
        assertEquals(0, list.size());
        assertEquals(new TestObject(4), removed);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void removeAtIndex2c() {
        list.addToFront(new TestObject(4));

        TestObject removed = list.removeAtIndex(1);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void removeAtIndex2d() {
        list.addToFront(new TestObject(4));

        TestObject removed = list.removeAtIndex(2);
    }


    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void removeAtIndex2e() {
        list.addToFront(new TestObject(4));

        TestObject removed = list.removeAtIndex(5);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void removeAtIndex3a() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));

        list.removeAtIndex(-4);
    }

    @Test(timeout=TIMEOUT)
    public void removeAtIndex3b() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));

        TestObject removed = list.removeAtIndex(0);

        assertEquals(new TestObject(2), list.getHead().getData());
        assertEquals(1, list.size());
        assertSame(list.getHead(), list.getHead().getNext());
        assertSame(list.getHead(), list.getHead().getNext().getNext());
        assertEquals(new TestObject(1), removed);

    }

    @Test(timeout=TIMEOUT)
    public void removeAtIndex3c() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));

        TestObject removed = list.removeAtIndex(1);

        assertEquals(new TestObject(1), list.getHead().getData());
        assertEquals(1, list.size());
        assertSame(list.getHead(), list.getHead().getNext());
        assertSame(list.getHead(), list.getHead().getNext().getNext());
        assertEquals(new TestObject(2), removed);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void removeAtIndex3f() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));

        list.removeAtIndex(2);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void removeAtIndex3g() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));

        list.removeAtIndex(3);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void removeAtIndex4a() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));

        list.removeAtIndex(-5);
    }

    @Test(timeout=TIMEOUT)
    public void removeAtIndex4b() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));

        TestObject removed = list.removeAtIndex(0);

        assertArrayEquals(
                new TestObject[] {
                        new TestObject(2),
                        new TestObject(3),
                        new TestObject(4),
                        new TestObject(5),
                }, list.toArray()
        );

        assertEquals(new TestObject(2), list.getHead().getData());
        assertEquals(4, list.size());
        assertEquals(new TestObject(3), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeAtIndex4c() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));

        TestObject removed = list.removeAtIndex(1);

        assertArrayEquals(
                new TestObject[] {
                        new TestObject(1),
                        new TestObject(3),
                        new TestObject(4),
                        new TestObject(5),
                }, list.toArray()
        );

        assertEquals(new TestObject(1), list.getHead().getData());
        assertEquals(4, list.size());
        assertEquals(new TestObject(3), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(2), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeAtIndex4d() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));

        TestObject removed = list.removeAtIndex(2);

        assertArrayEquals(
                new TestObject[] {
                        new TestObject(1),
                        new TestObject(2),
                        new TestObject(4),
                        new TestObject(5),
                }, list.toArray()
        );

        assertEquals(new TestObject(1), list.getHead().getData());
        assertEquals(4, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(3), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeAtIndex4e() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));

        TestObject removed = list.removeAtIndex(3);

        assertArrayEquals(
                new TestObject[] {
                        new TestObject(1),
                        new TestObject(2),
                        new TestObject(3),
                        new TestObject(5),
                }, list.toArray()
        );

        assertEquals(new TestObject(1), list.getHead().getData());
        assertEquals(4, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(4), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeAtIndex4f() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));

        TestObject removed = list.removeAtIndex(4);

        assertArrayEquals(
                new TestObject[] {
                        new TestObject(1),
                        new TestObject(2),
                        new TestObject(3),
                        new TestObject(4),
                }, list.toArray()
        );

        assertEquals(new TestObject(1), list.getHead().getData());
        assertEquals(4, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(5), removed);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void removeAtIndex4g() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));

        list.removeAtIndex(5);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void removeAtIndex4h() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));

        list.removeAtIndex(10);
    }


    @Test(timeout=TIMEOUT, expected=java.util.NoSuchElementException.class)
    public void removeFromFront1a() {
        list.removeFromFront();
    }

    @Test(timeout=TIMEOUT)
    public void removeFromFront2a() {
        list.addToFront(new TestObject(4));
        list.addAtIndex(1, new TestObject(3));
        list.addToBack(new TestObject(5));
        list.removeFromBack();
        list.removeAtIndex(1);

        TestObject removed = list.removeFromFront();

        assertEquals(null, list.getHead());
        assertEquals(0, list.size());
        assertEquals(new TestObject(4), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeFromFront3a() {
        list.addToFront(new TestObject(1));
        list.addToBack(new TestObject(5));
        list.addAtIndex(1, new TestObject(2));
        list.addToBack(new TestObject(5));
        list.removeAtIndex(1);
        list.removeFromBack();

        TestObject removed = list.removeFromFront();

        assertEquals(new TestObject(5), list.getHead().getData());
        assertEquals(1, list.size());
        assertSame(list.getHead(), list.getHead().getNext());
        assertSame(list.getHead(), list.getHead().getNext().getNext());
        assertEquals(new TestObject(1), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeFromFront4a() {
        list.addToFront(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(11));
        list.addToBack(new TestObject(12));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.removeAtIndex(3);
        list.removeAtIndex(3);
        list.removeAtIndex(3);

        TestObject removed = list.removeFromFront();

        assertEquals(new TestObject(2), list.getHead().getData());
        assertEquals(4, list.size());
        assertEquals(new TestObject(3), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }


    @Test(timeout=TIMEOUT, expected=java.util.NoSuchElementException.class)
    public void removeFromBack1a() {
        list.removeFromBack();
    }

    @Test(timeout=TIMEOUT)
    public void removeFromBack2a() {
        list.addToFront(new TestObject(4));
        list.addAtIndex(1, new TestObject(3));
        list.addToBack(new TestObject(5));
        list.removeFromBack();
        list.removeAtIndex(1);

        TestObject removed = list.removeFromBack();

        assertEquals(null, list.getHead());
        assertEquals(0, list.size());
        assertEquals(new TestObject(4), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeFromBack3a() {
        list.addToFront(new TestObject(1));
        list.addToBack(new TestObject(5));
        list.addAtIndex(1, new TestObject(2));
        list.addToBack(new TestObject(5));
        list.removeAtIndex(1);
        list.removeFromBack();

        TestObject removed = list.removeFromBack();

        assertEquals(new TestObject(1), list.getHead().getData());
        assertEquals(1, list.size());
        assertSame(list.getHead(), list.getHead().getNext());
        assertSame(list.getHead(), list.getHead().getNext().getNext());
        assertEquals(new TestObject(5), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeFromBack4a() {
        list.addToFront(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(11));
        list.addToBack(new TestObject(12));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.removeAtIndex(3);
        list.removeAtIndex(3);
        list.removeAtIndex(3);

        TestObject removed = list.removeFromBack();

        assertArrayEquals(new TestObject[] {
                new TestObject(1),
                new TestObject(2),
                new TestObject(3),
                new TestObject(4),
        }, list.toArray());

        assertEquals(new TestObject(1), list.getHead().getData());
        assertEquals(4, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()          // 1
                        .getNext()      // 2
                        .getNext()      // 3
                        .getNext()      // 4
                        .getNext());    // 1
        assertEquals(new TestObject(5), removed);
    }


    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void get1a() {
        list.get(-1);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void get1b() {
        list.get(0);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void get1c() {
        list.get(1);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void get1d() {
        list.get(10);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void get2a() {
        list.addToFront(new TestObject(4));

        list.get(-2);
    }

    @Test(timeout=TIMEOUT)
    public void get2b() {
        list.addToFront(new TestObject(4));

        TestObject gotten = list.get(0);

        assertEquals(new TestObject(4), gotten);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void get2c() {
        list.addToFront(new TestObject(4));

        TestObject gotten = list.get(1);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void get2d() {
        list.addToFront(new TestObject(4));

        TestObject gotten = list.get(2);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void get2e() {
        list.addToFront(new TestObject(4));

        TestObject gotten = list.get(5);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void get3a() {
        list.addToFront(new TestObject(4));
        list.addAtIndex(1, new TestObject(10));

        TestObject gotten = list.get(-4);
    }

    @Test(timeout=TIMEOUT)
    public void get3b() {
        list.addToFront(new TestObject(4));
        list.addAtIndex(1, new TestObject(10));

        TestObject gotten = list.get(0);

        assertEquals(new TestObject(4), gotten);
    }

    @Test(timeout=TIMEOUT)
    public void get3c() {
        list.addToFront(new TestObject(4));
        list.addAtIndex(1, new TestObject(10));

        TestObject gotten = list.get(1);

        assertEquals(new TestObject(10), gotten);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void get3d() {
        list.addToFront(new TestObject(4));
        list.addAtIndex(1, new TestObject(10));

        TestObject gotten = list.get(2);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void get3e() {
        list.addToFront(new TestObject(4));
        list.addAtIndex(1, new TestObject(10));

        TestObject gotten = list.get(3);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void get3f() {
        list.addToFront(new TestObject(4));
        list.addAtIndex(1, new TestObject(10));

        TestObject gotten = list.get(4);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void get4a() {
        list.addToFront(new TestObject(1));
        list.addAtIndex(1, new TestObject(2));
        list.addAtIndex(2, new TestObject(3));
        list.addAtIndex(3, new TestObject(4));
        list.addToBack(new TestObject(5));

        TestObject gotten = list.get(-1);
    }

    @Test(timeout=TIMEOUT)
    public void get4b() {
        list.addToFront(new TestObject(1));
        list.addAtIndex(1, new TestObject(2));
        list.addAtIndex(2, new TestObject(3));
        list.addAtIndex(3, new TestObject(4));
        list.addToBack(new TestObject(5));

        TestObject gotten = list.get(0);

        assertEquals(new TestObject(1), gotten);
    }

    @Test(timeout=TIMEOUT)
    public void get4c() {
        list.addToFront(new TestObject(1));
        list.addAtIndex(1, new TestObject(2));
        list.addAtIndex(2, new TestObject(3));
        list.addAtIndex(3, new TestObject(4));
        list.addToBack(new TestObject(5));

        TestObject gotten = list.get(1);

        assertEquals(new TestObject(2), gotten);
    }

    @Test(timeout=TIMEOUT)
    public void get4d() {
        list.addToFront(new TestObject(1));
        list.addAtIndex(1, new TestObject(2));
        list.addAtIndex(2, new TestObject(3));
        list.addAtIndex(3, new TestObject(4));
        list.addToBack(new TestObject(5));

        TestObject gotten = list.get(2);

        assertEquals(new TestObject(3), gotten);
    }

    @Test(timeout=TIMEOUT)
    public void get4e() {
        list.addToFront(new TestObject(1));
        list.addAtIndex(1, new TestObject(2));
        list.addAtIndex(2, new TestObject(3));
        list.addAtIndex(3, new TestObject(4));
        list.addToBack(new TestObject(5));

        TestObject gotten = list.get(3);

        assertEquals(new TestObject(4), gotten);
    }

    @Test(timeout=TIMEOUT)
    public void get4f() {
        list.addToFront(new TestObject(1));
        list.addAtIndex(1, new TestObject(2));
        list.addAtIndex(2, new TestObject(3));
        list.addAtIndex(3, new TestObject(4));
        list.addToBack(new TestObject(5));

        TestObject gotten = list.get(4);

        assertEquals(new TestObject(5), gotten);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void get4g() {
        list.addToFront(new TestObject(1));
        list.addAtIndex(1, new TestObject(2));
        list.addAtIndex(2, new TestObject(3));
        list.addAtIndex(3, new TestObject(4));
        list.addToBack(new TestObject(5));

        TestObject gotten = list.get(5);
    }

    @Test(timeout=TIMEOUT, expected=IndexOutOfBoundsException.class)
    public void get4h() {
        list.addToFront(new TestObject(1));
        list.addAtIndex(1, new TestObject(2));
        list.addAtIndex(2, new TestObject(3));
        list.addAtIndex(3, new TestObject(4));
        list.addToBack(new TestObject(5));

        TestObject gotten = list.get(6);
    }

    @Test(timeout=TIMEOUT)
    public void isEmpty1a() {
        assertTrue(list.isEmpty());
    }

    @Test(timeout=TIMEOUT)
    public void isEmpty1b() {
        list.addAtIndex(0, new TestObject(3));
        list.addToFront(new TestObject(0));
        list.addToBack(new TestObject(5));

        assertFalse(list.isEmpty());

        list.removeAtIndex(2);
        list.removeFromFront();
        list.removeFromBack();

        assertTrue(list.isEmpty());
    }

    @Test(timeout=TIMEOUT)
    public void isEmpty1c() {
        list.addAtIndex(0, new TestObject(3));
        list.addToFront(new TestObject(0));
        list.addToBack(new TestObject(5));

        assertFalse(list.isEmpty());

        list.clear();

        assertTrue(list.isEmpty());
    }

    @Test(timeout=TIMEOUT)
    public void isEmpty2a() {
        assertTrue(list.isEmpty());

        list.addToFront(new TestObject(9));
        assertFalse(list.isEmpty());

        list.addToBack(new TestObject(10));
        assertFalse(list.isEmpty());

        list.addAtIndex(0, new TestObject(1));
        assertFalse(list.isEmpty());

        list.addToFront(new TestObject(5));
        assertFalse(list.isEmpty());

        list.removeFromBack();
        assertFalse(list.isEmpty());

        list.removeFromFront();
        assertFalse(list.isEmpty());

        list.removeAtIndex(0);
        assertFalse(list.isEmpty());

        list.removeAtIndex(0);
        assertTrue(list.isEmpty());

        list.addToFront(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(11));
        list.addToBack(new TestObject(12));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.removeAtIndex(3);
        list.removeAtIndex(3);
        list.removeAtIndex(3);
        assertFalse(list.isEmpty());

        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test(timeout=TIMEOUT)
    public void clear1a() {
        list.clear();

        assertEquals(0, list.size());
        assertEquals(null, list.getHead());
    }

    @Test(timeout=TIMEOUT)
    public void clear1b() {
        list.addToFront(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(11));
        list.addToBack(new TestObject(12));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.removeAtIndex(3);
        list.removeAtIndex(3);
        list.removeAtIndex(3);
        list.removeFromFront();
        list.removeFromBack();
        list.removeFromFront();
        list.removeFromBack();
        list.removeFromFront();

        list.clear();

        assertEquals(0, list.size());
        assertEquals(null, list.getHead());
    }

    @Test(timeout=TIMEOUT)
    public void clear2a() {
        list.addToFront(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(11));
        list.addToBack(new TestObject(12));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.removeAtIndex(3);
        list.removeAtIndex(3);
        list.removeAtIndex(3);
        list.removeFromBack();
        list.removeFromFront();
        list.removeFromBack();
        list.removeFromFront();

        list.clear();

        assertEquals(0, list.size());
        assertEquals(null, list.getHead());
    }

    @Test(timeout=TIMEOUT)
    public void clear3a() {
        list.addToFront(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(11));
        list.addToBack(new TestObject(12));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.removeAtIndex(3);
        list.removeAtIndex(3);
        list.removeAtIndex(3);
        list.removeFromBack();
        list.removeFromFront();
        list.removeFromFront();

        list.clear();

        assertEquals(0, list.size());
        assertEquals(null, list.getHead());
    }

    @Test(timeout=TIMEOUT)
    public void clear4a() {
        list.addToFront(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(11));
        list.addToBack(new TestObject(12));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.removeAtIndex(3);
        list.removeAtIndex(3);
        list.removeFromBack();
        list.removeFromFront();
        list.removeFromFront();

        list.clear();

        assertEquals(0, list.size());
        assertEquals(null, list.getHead());
    }


    @Test(timeout=TIMEOUT, expected=java.util.NoSuchElementException.class)
    public void removeLastOccurrence1a() {
        assertTrue(list.isEmpty());

        list.removeLastOccurrence(new TestObject(2));
    }

    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence2a() {
        list.addToFront(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.removeAtIndex(0);

        TestObject removed = list.removeLastOccurrence(new TestObject(5));

        assertEquals(null, list.getHead());
        assertEquals(0, list.size());
        assertEquals(new TestObject(5), removed);
    }

    @Test(timeout=TIMEOUT, expected=java.util.NoSuchElementException.class)
    public void removeLastOccurrence2b() {
        list.addToFront(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.removeAtIndex(0);

        TestObject removed = list.removeLastOccurrence(new TestObject(8));
    }

    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence3a() {
        list.addToFront(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.addAtIndex(1, new TestObject(5));
        list.removeFromFront();

        TestObject removed = list.removeLastOccurrence(new TestObject(5));

        assertEquals(new TestObject(5), list.getHead().getData());
        assertEquals(1, list.size());
        assertSame(list.getHead(), list.getHead().getNext());
        assertSame(list.getHead(), list.getHead().getNext().getNext());
        assertEquals(new TestObject(5), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence3b() {
        list.addToFront(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.addAtIndex(1, new TestObject(2));
        list.removeFromFront();

        TestObject removed = list.removeLastOccurrence(new TestObject(2));

        assertEquals(new TestObject(5), list.getHead().getData());
        assertEquals(1, list.size());
        assertSame(list.getHead(), list.getHead().getNext());
        assertSame(list.getHead(), list.getHead().getNext().getNext());
        assertEquals(new TestObject(2), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence3c() {
        list.addToFront(new TestObject(4));
        list.addToBack(new TestObject(2));
        list.addAtIndex(1, new TestObject(5));
        list.removeFromFront();

        TestObject removed = list.removeLastOccurrence(new TestObject(5));

        assertEquals(new TestObject(2), list.getHead().getData());
        assertEquals(1, list.size());
        assertSame(list.getHead(), list.getHead().getNext());
        assertSame(list.getHead(), list.getHead().getNext().getNext());
        assertEquals(new TestObject(5), removed);
    }

    @Test(timeout=TIMEOUT, expected=java.util.NoSuchElementException.class)
    public void removeLastOccurrence3d() {
        list.addToFront(new TestObject(4));
        list.addToBack(new TestObject(2));
        list.addAtIndex(1, new TestObject(5));
        list.removeFromFront();

        TestObject removed = list.removeLastOccurrence(new TestObject(10));
    }


    @Test(timeout=TIMEOUT, expected=java.util.NoSuchElementException.class)
    public void removeLastOccurrence4a() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.addToBack(new TestObject(6));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(9));
        list.addToBack(new TestObject(10));

        list.removeLastOccurrence(new TestObject(11));
    }


    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4b() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.addToBack(new TestObject(6));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(9));
        list.addToBack(new TestObject(10));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(2),
                new TestObject(3),
                new TestObject(4),
                new TestObject(5),
                new TestObject(6),
                new TestObject(7),
                new TestObject(8),
                new TestObject(9),
                new TestObject(10),
        }, list.toArray());

        assertEquals(new TestObject(2), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(3), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }


    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4c() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(6));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(9));
        list.addToBack(new TestObject(10));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(1),
                new TestObject(2),
                new TestObject(3),
                new TestObject(4),
                new TestObject(6),
                new TestObject(7),
                new TestObject(8),
                new TestObject(9),
                new TestObject(10),
        }, list.toArray());

        assertEquals(new TestObject(1), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4d() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(9));
        list.addToBack(new TestObject(10));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(1),
                new TestObject(2),
                new TestObject(3),
                new TestObject(4),
                new TestObject(1),
                new TestObject(7),
                new TestObject(8),
                new TestObject(9),
                new TestObject(10),
        }, list.toArray());

        assertEquals(new TestObject(1), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4e() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(9));
        list.addToBack(new TestObject(1));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(1),
                new TestObject(2),
                new TestObject(3),
                new TestObject(4),
                new TestObject(1),
                new TestObject(10),
                new TestObject(7),
                new TestObject(8),
                new TestObject(9),
        }, list.toArray());

        assertEquals(new TestObject(1), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4f() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(9));
        list.addToBack(new TestObject(1));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(1),
                new TestObject(2),
                new TestObject(3),
                new TestObject(4),
                new TestObject(1),
                new TestObject(1),
                new TestObject(7),
                new TestObject(8),
                new TestObject(9),
        }, list.toArray());

        assertEquals(new TestObject(1), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4g() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.addToBack(new TestObject(6));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(9));
        list.addToBack(new TestObject(1));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(1),
                new TestObject(2),
                new TestObject(3),
                new TestObject(4),
                new TestObject(5),
                new TestObject(6),
                new TestObject(7),
                new TestObject(8),
                new TestObject(9),
        }, list.toArray());

        assertEquals(new TestObject(1), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }


    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4h() {
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(6));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(9));
        list.addToBack(new TestObject(11));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(10),
                new TestObject(2),
                new TestObject(3),
                new TestObject(4),
                new TestObject(6),
                new TestObject(7),
                new TestObject(8),
                new TestObject(9),
                new TestObject(11),
        }, list.toArray());

        assertEquals(new TestObject(10), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }


    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4i() {
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(6));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(9));
        list.addToBack(new TestObject(11));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(10),
                new TestObject(2),
                new TestObject(3),
                new TestObject(4),
                new TestObject(1),
                new TestObject(6),
                new TestObject(8),
                new TestObject(9),
                new TestObject(11),
        }, list.toArray());

        assertEquals(new TestObject(10), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4j() {
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(6));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(9));
        list.addToBack(new TestObject(1));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(10),
                new TestObject(2),
                new TestObject(3),
                new TestObject(4),
                new TestObject(1),
                new TestObject(6),
                new TestObject(7),
                new TestObject(8),
                new TestObject(9),
        }, list.toArray());

        assertEquals(new TestObject(10), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4k() {
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(6));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(9));
        list.addToBack(new TestObject(1));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(10),
                new TestObject(1),
                new TestObject(3),
                new TestObject(4),
                new TestObject(1),
                new TestObject(6),
                new TestObject(7),
                new TestObject(8),
                new TestObject(9),
        }, list.toArray());

        assertEquals(new TestObject(10), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(1), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4L() {
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.addToBack(new TestObject(6));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(9));
        list.addToBack(new TestObject(1));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(10),
                new TestObject(2),
                new TestObject(3),
                new TestObject(4),
                new TestObject(5),
                new TestObject(6),
                new TestObject(7),
                new TestObject(8),
                new TestObject(9),
        }, list.toArray());

        assertEquals(new TestObject(10), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4m() {
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.addToBack(new TestObject(6));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(9));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(1),
                new TestObject(2),
                new TestObject(3),
                new TestObject(4),
                new TestObject(5),
                new TestObject(6),
                new TestObject(7),
                new TestObject(8),
                new TestObject(9),
        }, list.toArray());

        assertEquals(new TestObject(1), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4n() {
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(6));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(9));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(10),
                new TestObject(2),
                new TestObject(3),
                new TestObject(4),
                new TestObject(1),
                new TestObject(6),
                new TestObject(7),
                new TestObject(8),
                new TestObject(9),
        }, list.toArray());

        assertEquals(new TestObject(10), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4O() {
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.addToBack(new TestObject(6));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(9));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(10),
                new TestObject(2),
                new TestObject(3),
                new TestObject(4),
                new TestObject(5),
                new TestObject(6),
                new TestObject(7),
                new TestObject(8),
                new TestObject(9),
        }, list.toArray());

        assertEquals(new TestObject(10), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4p() {
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(6));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(9));
        list.addToBack(new TestObject(1));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(10),
                new TestObject(2),
                new TestObject(3),
                new TestObject(4),
                new TestObject(1),
                new TestObject(6),
                new TestObject(7),
                new TestObject(8),
                new TestObject(9),
        }, list.toArray());

        assertEquals(new TestObject(10), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(2), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }


    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4q() {
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.addToBack(new TestObject(6));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(9));
        list.addToBack(new TestObject(11));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(10),
                new TestObject(3),
                new TestObject(4),
                new TestObject(5),
                new TestObject(6),
                new TestObject(7),
                new TestObject(8),
                new TestObject(9),
                new TestObject(11),
        }, list.toArray());

        assertEquals(new TestObject(10), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(3), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4r() {
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(6));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(9));
        list.addToBack(new TestObject(11));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(10),
                new TestObject(1),
                new TestObject(3),
                new TestObject(4),
                new TestObject(6),
                new TestObject(7),
                new TestObject(8),
                new TestObject(9),
                new TestObject(11),
        }, list.toArray());

        assertEquals(new TestObject(10), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(1), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4s() {
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(6));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(9));
        list.addToBack(new TestObject(1));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(10),
                new TestObject(1),
                new TestObject(3),
                new TestObject(4),
                new TestObject(1),
                new TestObject(6),
                new TestObject(7),
                new TestObject(8),
                new TestObject(9),
        }, list.toArray());

        assertEquals(new TestObject(10), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(1), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4t() {
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(6));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(9));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(10),
                new TestObject(1),
                new TestObject(3),
                new TestObject(4),
                new TestObject(1),
                new TestObject(6),
                new TestObject(7),
                new TestObject(8),
                new TestObject(9),
        }, list.toArray());

        assertEquals(new TestObject(10), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(1), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }

    @Test(timeout=TIMEOUT)
    public void removeLastOccurrence4u() {
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.addToBack(new TestObject(6));
        list.addToBack(new TestObject(7));
        list.addToBack(new TestObject(8));
        list.addToBack(new TestObject(1));
        list.addToBack(new TestObject(9));

        TestObject removed = list.removeLastOccurrence(new TestObject(1));

        assertArrayEquals(new TestObject[] {
                new TestObject(10),
                new TestObject(1),
                new TestObject(3),
                new TestObject(4),
                new TestObject(5),
                new TestObject(6),
                new TestObject(7),
                new TestObject(8),
                new TestObject(9),
        }, list.toArray());

        assertEquals(new TestObject(10), list.getHead().getData());
        assertEquals(9, list.size());
        assertEquals(new TestObject(1), list.getHead().getNext().getData());
        assertSame(list.getHead(),
                list.getHead()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext()
                        .getNext());
        assertEquals(new TestObject(1), removed);
    }


    @Test(timeout=TIMEOUT)
    public void toArray1a() {
        assertArrayEquals(new TestObject[0], list.toArray());
    }


    @Test(timeout=TIMEOUT)
    public void toArray2a() {
        list.addToFront(new TestObject(1));
        list.addToBack(new TestObject(5));
        list.removeAtIndex(0);

        assertArrayEquals(new TestObject[] {
                new TestObject(5)
        }, list.toArray());
    }


    @Test(timeout=TIMEOUT)
    public void toArray3a() {
        list.addToBack(new TestObject(1));
        list.addAtIndex(1, new TestObject(10));
        list.addAtIndex(0, new TestObject(-5));
        list.removeFromBack();

        assertArrayEquals(new TestObject[] {
                new TestObject(-5),
                new TestObject(1)
        }, list.toArray());
    }


    @Test(timeout=TIMEOUT)
    public void toArray4a() {
        list.addToFront(new TestObject(1));
        list.addToBack(new TestObject(2));
        list.addToBack(new TestObject(3));
        list.addToBack(new TestObject(10));
        list.addToBack(new TestObject(11));
        list.addToBack(new TestObject(12));
        list.addToBack(new TestObject(4));
        list.addToBack(new TestObject(5));
        list.removeAtIndex(3);
        list.removeAtIndex(3);
        list.removeAtIndex(3);

        assertArrayEquals(new TestObject[] {
                new TestObject(1),
                new TestObject(2),
                new TestObject(3),
                new TestObject(4),
                new TestObject(5),
        }, list.toArray());
    }
}