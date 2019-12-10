/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quest;

import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import quests.quest.Quest;

/**
 *
 * @author cmarino
 */
public class QuestTest {
    
    public QuestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void getIDTest(){
        Map<EnumItem ,Boolean> itemMap = new HashMap<>();
        SubjectEnum subj = SubjectEnum.MATH;
        Quest q = new Quest(SubjectEnum.MATH, itemMap);
        String subjQ = q.getID();
        assert(subjQ == null ? subj.toString() != null : !subjQ.equals(subj.toString()) );
    }
    
    @Test
    public void toStringTest(){
        Map<EnumItem ,Boolean> itemMap = new HashMap<>();
        SubjectEnum subj = SubjectEnum.MATH;
        Quest q = new Quest(SubjectEnum.MATH, itemMap);
        String desc = q.toString();
        assertNotNull(desc);
        assertEquals(desc,"NOT TESTABLE YET");
    }
    
    
    
    @Test 
    public void isAvailableTest(){
        Map<EnumItem ,Boolean> itemMap = new HashMap<>();
        itemMap.put(EnumItem.MN1, true);
        SubjectEnum subj = SubjectEnum.MATH;
        Quest q = new Quest(subj, itemMap);
        assertFalse(q.isAvailable());
        q.receive(new Message(EnumItem.MN1.toString(),true));
        assertTtrue(q.isAvailable());
    }
    
    @Test
    public void isDoneTest(){

        Map<EnumItem ,Boolean> itemMap = new HashMap<>();
        Quest q = new Quest(SubjectEnum.MATH, itemMap);
        assertFalse(q.isFinished());
        q.finish();
        assertTrue(q.isFinished());

    }
    
    @Test
    public void finishTest(){
            Quest q = new Quest(SubjectEnum.MATH, itemMap);
            q.finish();
            assertTrue(q.isDone());
            
        
    }
}