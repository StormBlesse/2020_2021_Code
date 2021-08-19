import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    @Test
    public void russellTest() {
        MyStack stack1 = new MyStack();
        MyStack stack2 = new MyStack();
        MyStack stack3 = new MyStack();

        // Test pushing
        for (int i = 0; i < 100; i++) {
            stack1.push(Integer.toString(i));
            stack2.push(Integer.toString(i));
            stack3.push(Integer.toString(i));
        }

        // Test pop
        for (int i = 0; i < 100; i++) {
            assertEquals(stack1.pop(), stack2.pop());
        }

        // Test delete and top
        for (int i = 99; i >= 0; i -= 5) {
            assertEquals(stack3.top(), Integer.toString(i));
            stack3.delete(5);
        }
    }

    @Test
    public void chaseTest() {
        //Testing that delete works
        MyStack stack = new MyStack();
        for (int i = 0; i < 10; i++) {
            stack.push("input: " + i);
        }
        stack.delete(5);
        assertEquals("input: 4", stack.top());
    }

    @Test
    public void allisonTest() {

    }

    @Test
    public void gabeTest() {
        MyStack stack = new MyStack();
        for (int i = 0; i < 10; i++) {
            stack.push("hello" + i);
        }
        String top = stack.top();
        assertEquals("hello9", top);

    }

    @Test
    public void junTest() {
        
    }

}