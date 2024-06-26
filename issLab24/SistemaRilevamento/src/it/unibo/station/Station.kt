/* Generated by AN DISI Unibo */ 
package it.unibo.station

import it.unibo.kactor.*
import alice.tuprolog.*
import unibo.basicomm23.*
import unibo.basicomm23.interfaces.*
import unibo.basicomm23.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import it.unibo.kactor.sysUtil.createActor   //Sept2023

//User imports JAN2024

class Station ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "start"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		return { //this:ActionBasciFsm
				state("start") { //this:State
					action { //it:State
						CommUtils.outmagenta("Station START")
						delay(500) 
						forward("start", "start(0)" ,"rasp" ) 
						delay(5000) 
						forward("stop", "stop(0)" ,"rasp" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t00",targetState="onHandle",cond=whenDispatch("on"))
					transition(edgeName="t01",targetState="offHandle",cond=whenDispatch("off"))
				}	 
				state("onHandle") { //this:State
					action { //it:State
						forward("on", "on(0)" ,"redled" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t02",targetState="onHandle",cond=whenDispatch("on"))
					transition(edgeName="t03",targetState="offHandle",cond=whenDispatch("off"))
				}	 
				state("offHandle") { //this:State
					action { //it:State
						forward("off", "off(0)" ,"redled" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t04",targetState="onHandle",cond=whenDispatch("on"))
					transition(edgeName="t05",targetState="offHandle",cond=whenDispatch("off"))
				}	 
			}
		}
} 
