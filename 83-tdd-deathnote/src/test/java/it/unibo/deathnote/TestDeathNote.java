package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private DeathNote note = new DeathNoteImpl();
    private DeathNote book = new DeathNoteImpl();
     private DeathNote notebook = new DeathNoteImpl();
    private final static String HUMAN_1 = "Pietro";
    private final static String HUMAN_2 = "Paolo";
    private final static String CAUSE = "karting accident";
    private final static String DETAILS = "ran for too long";
    private final static int BIGGER_THAN_RULES_NUMBER = 20;

    @Test
    public void TestIlligalRuleNumber() {
        try {
            note.getRule(0);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
        }
        try {
            note.getRule(-1);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    public void TestNotNullRules() {
        try {
            for(int i=0; i < BIGGER_THAN_RULES_NUMBER; i++) {
                assertNotNull(this.note.getRule(i));
                assertNotEquals("", this.note.getRule(i));
            }
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    public void TestNames() {
        assertFalse(this.note.isNameWritten(HUMAN_1));
        note.writeName(HUMAN_1);
        assertTrue(note.isNameWritten(HUMAN_1));
        assertFalse(this.note.isNameWritten(HUMAN_2));
        assertFalse(this.note.isNameWritten(""));
    }

    @Test
    public void TestWriteCause() throws InterruptedException {
        try {
            book.writeDeathCause(CAUSE);
            Assertions.fail();
        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage());
        }
        book.writeName(HUMAN_1);
        assertEquals("heart attack", book.getDeathCause(HUMAN_1));
        book.writeName(HUMAN_2);
        book.writeDeathCause(CAUSE);
        assertEquals(CAUSE, book.getDeathCause(HUMAN_2));
        Thread.sleep(40L);
        assertFalse(book.writeDeathCause("heart attack"));
        assertEquals(CAUSE, book.getDeathCause(HUMAN_2));
    }

    @Test
    public void TestWriteDetails() throws InterruptedException {
        try {
            notebook.writeDetails(DETAILS);
            Assertions.fail();
        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage());
        }
        notebook.writeName(HUMAN_1);
        assertEquals("", notebook.getDeathDetails(HUMAN_1));
        notebook.writeDetails(DETAILS);
        assertEquals(DETAILS, notebook.getDeathDetails(HUMAN_1));
        notebook.writeName(HUMAN_2);
        Thread.sleep(6100L);
        assertFalse(notebook.writeDetails(DETAILS));
        assertEquals(DETAILS, notebook.getDeathDetails(HUMAN_2));
    }
}