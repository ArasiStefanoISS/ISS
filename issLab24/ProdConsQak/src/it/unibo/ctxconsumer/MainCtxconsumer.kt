/* Generated by AN DISI Unibo */ 
package it.unibo.ctxconsumer
import it.unibo.kactor.QakContext
import it.unibo.kactor.sysUtil
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
	//System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "ERROR");
	QakContext.createContexts(
	        "130.136.113.149", this, "prodcons24_producer.pl", "sysRules.pl", "ctxconsumer"
	)
	//JAN Facade
	//JAN24 Display
}

