/* Generated by AN DISI Unibo */ 
package it.unibo.ctxWB
import it.unibo.kactor.QakContext
import it.unibo.kactor.sysUtil
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
	//System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "ERROR");
	QakContext.createContexts(
	        "localhost", this, "bw.pl", "sysRules.pl", "ctxWB"
	)
	//JAN Facade
	//JAN24 Display
}
