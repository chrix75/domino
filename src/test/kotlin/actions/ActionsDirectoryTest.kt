package actions

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by batman on 19/04/2016.
 */
class ActionsDirectoryTest {

    @Test
    fun findAction() {
        val action = ActionsDirectory.actions["Sort"]
        assertNotNull(action)
        assertTrue(action is SortAction)
    }
}