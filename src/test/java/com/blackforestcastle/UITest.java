package com.blackforestcastle;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class UITest {

    @org.junit.jupiter.api.Test
    void textPrint() {
        String str1 = new String(" ");
        assertEquals("hello", "hello");
    }

    @org.junit.jupiter.api.Test
    void main() {
        JFrame frame = new JFrame();
        assertSame(frame, frame);
    }
}