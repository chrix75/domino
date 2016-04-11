package neo4j.utils

import org.neo4j.ogm.session.Session
import org.neo4j.ogm.transaction.Transaction

/**
 * Manages the mono-transaction system per thread of Neo4J.
 *
 * Created by Christian Sperandio on 10/04/2016.
 *
 */
class TransactionManager(val session: Session) {
    private var tx: Transaction? = null
    private var createdTransaction = false

    fun beginTransaction() {
        tx = session.transaction

        if (tx == null) {
            createdTransaction = true
            tx = session.beginTransaction()
        }
    }

    fun rollback() {
        if (createdTransaction)
            tx?.rollback()
    }

    fun commit() {
        if (createdTransaction)
            tx?.commit()
    }

    fun close() {
        if (createdTransaction)
            tx?.close()
    }
}
