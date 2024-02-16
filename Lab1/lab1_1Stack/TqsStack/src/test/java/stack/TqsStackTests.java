package stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.example.TqsStack;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("TqsStack Tests")
public class TqsStackTests {

    TqsStack<String> wordsStack = new TqsStack<>();



    @BeforeEach
    void setUp() {
        wordsStack = new TqsStack<>();
    }

    @Test
    public void testIsEmpty() {
        assert(wordsStack.isEmpty());
    }

    @DisplayName("When stack initialized, size is 0")
    @Test
    public void size0()  {
        assert(wordsStack.size() == 0);
    }

    @Test
    public void testIsFull() {
        assert(!wordsStack.isFull());
    }

    @DisplayName("When stack has one element, size is 1")
    @Test
    public void testPush() {
        wordsStack.push("word");
        assert(wordsStack.size() == 1);
    }

    @Test
    // @Disabled -> permite desativar o teste
    public void push2Elements_thenpop() {
        wordsStack.push("word1");
        wordsStack.push("word2");
        assertEquals("word2", wordsStack.pop());
        assertEquals("word1", wordsStack.pop());
        assert(wordsStack.isEmpty());
    }

    @Test
    @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
    public void pushXthenPeeks() {
        wordsStack.push("word1");
        wordsStack.push("word2");
        wordsStack.push("word3");
        wordsStack.push("word4");
        assertEquals("word4", wordsStack.peek());
        assertEquals(4, wordsStack.size());

    }

    @Test
    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    public void popNtimes() {
        wordsStack.push("word1");
        wordsStack.push("word2");
        wordsStack.push("word3");
        wordsStack.push("word4");
        wordsStack.pop();
        wordsStack.pop();
        wordsStack.pop();
        wordsStack.pop();
        assert(wordsStack.isEmpty());
        assertEquals(0, wordsStack.size());
    }

    @Test
    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    public void popEmptyStack() {
        assert(wordsStack.isEmpty());
        assertThrows(NoSuchElementException.class, () -> wordsStack.pop());
    }

    @Test
    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    public void peekEmptyStack() {
        assert(wordsStack.isEmpty());
        assertThrows(NoSuchElementException.class, () -> wordsStack.peek());
    }
}
