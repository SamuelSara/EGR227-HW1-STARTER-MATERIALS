import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

//1. Write a method interleave that accepts a queue of integers as a parameter
// and rearranges the elements by alternating the elements from the first half
// of the queue with those from the second half of the queue. Your method
// should throw an IllegalArgumentException if the queue does not have even
// size. You may use one stack as auxiliary storage to solve this problem.
// Your solution must run in O(n) time, where n represents the size of the
// queue.

public class PraticeIT {
    public static void interleave(Queue<Integer> q) {
        if (q.size() % 2 == 1) {
            throw new IllegalArgumentException();
        }
        Stack<Integer> s = new Stack<Integer>();
        int size = q.size();
        for (int i = 0; i < size / 2; i++) {
            s.push(q.remove());
        }
        while (!s.isEmpty()) {
            q.add(s.pop());
        }
        for (int i = 0; i < size; i++) {
            if (i < size / 2) {
                q.add(q.remove());
            } else {
                s.add(q.remove());
            }
        }
        while (!s.isEmpty()) {
            q.add(s.pop());
            q.add(q.remove());
        }
    }

//2. Write a method isConsecutive that takes a stack of integers as a parameter
// and that returns whether or not the stack contains a sequence of
// consecutive integers starting from the bottom of the stack (returning true
// if it does, returning false if it does not). Your method must restore the
// stack so that it stores the same sequence of values after the call as it
// did before. Any stack with fewer than two values should be considered to be
// a list of consecutive integers. You may use one queue as auxiliary storage
// to solve this problem.

    public static boolean isConsecutive(Stack<Integer> s) {
        Queue<Integer> q = new LinkedList<Integer>();
        if (s.size() < 2) {
            return false;
        }
        while (!s.isEmpty()) {
            q.add(s.pop());
        }
        boolean consecutive = true;
        s.push(q.remove());
        while (!q.isEmpty()) {
            if (s.peek() - 1 != q.peek()) {
                consecutive = false;
            }
            s.push(q.remove());
        }
        while (!s.isEmpty()) {
            q.add(s.pop());
        }
        while (!q.isEmpty()) {
            s.push(q.remove());
        }
        return consecutive;
    }


// 3. Write a method isPalindrome that takes a queue of integers as a parameter
// and returns true if the numbers in the queue represent a palindrome (and
// false otherwise). Your method must restore the queue so that it stores the
// same sequence of values after the call as it did before. You may use one
// stack as auxiliary storage.

    public static boolean isPalindrome(Queue<Integer> q) {
        Stack<Integer> s = new Stack<Integer>();
        int oldSize = q.size();

        for (int i = 0; i < oldSize; i++) {
            int num = q.remove();
            q.add(num);
            s.push(num);
        }
        boolean same = true;
        for (int i = 0; i < oldSize; i++) {
            int num = q.remove();
            if (num != s.pop()) {
                same = false;
            }
            q.add(num);
        }
        return same;
    }


//4. Write a method isSorted that accepts a stack of integers as a parameter and
// returns true if the elements in the stack occur in ascending
// (non-decreasing) order from top to bottom, and false otherwise. That is,
// the smallest element should be on top, growing larger toward the bottom. An
// empty or one-element stack is considered to be sorted. When your method
// returns, the stack should be in the same state as when it was passed in. In
// other words, if your method modifies the stack, you must restore it before
// returning. You may use one queue or stack (but not both) as auxiliary
// storage. Your solution should run in O(N) time, where N is the number of
// elements of the stack.

    public static boolean isSorted(Stack<Integer> s) {
        Stack<Integer> storage = new Stack<Integer>();
        boolean sorted = true;

        while (sorted && s.size() > 1) {
            storage.push(s.pop());
            if (storage.peek() > s.peek()) {
                sorted = false;
            }
        }
        while (!storage.isEmpty()) {
            s.push(storage.pop());
        }
        return sorted;
    }

// 5. Write a method mirror that accepts a stack of integers as a parameter and
// replaces the stack contents with itself plus a mirrored version of itself
// (the same elements in the opposite order). Note that the mirrored version
// is added on to the top of what was originally in the stack. The bottom half
// of the stack contains the original numbers in the same order. If your
// method is passed an empty stack, the result should be an empty stack. If
// your method is passed a null stack, your method should throw an
// IllegalArgumentException. You may use one stack or one queue (but not both)
// as auxiliary storage to solve this problem. Your code must run in O(n) time
// where n is the number of elements of the original stack.

    public static void mirror(Stack<Integer> s) {
        Queue<Integer> q = new LinkedList<Integer>();

        if (s == null) {
            throw new IllegalArgumentException();
        }
        while (!s.isEmpty()) {
            q.add(s.pop());
        }

        int sizeOfQueue = q.size();
        for (int i = 0; i < sizeOfQueue; i++) {
            int num = q.remove();
            q.add(num);
            s.push(num);
        }
        while (!s.isEmpty()) {
            q.add(s.pop());
        }
        for (int i = 0; i < sizeOfQueue; i++) {
            q.add(q.remove());
        }
        while (!q.isEmpty()) {
            s.push(q.remove());
        }
    }
}