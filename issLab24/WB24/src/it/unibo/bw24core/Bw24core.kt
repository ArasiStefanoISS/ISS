/* Generated by AN DISI Unibo */ 
package it.unibo.bw24core

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
import main.resources.robotvirtual.VrobotLLMoves24
import main.resources.gui.*

class Bw24core ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 val vr = VrobotLLMoves24.create("localhost",myself)
		 var N = 0  
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outblue("$name STARTS")
						 GuiUtils.showSystemInfo()  
						 vr.setTrace(true)   
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="doboundary", cond=doswitch() )
				}	 
				state("doboundary") { //this:State
					action { //it:State
						delay(200) 
						 vr.forward(2400) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t00",targetState="handleVrinfo",cond=whenDispatch("vrinfo"))
					transition(edgeName="t01",targetState="pausetherobot",cond=whenDispatch("pause"))
					transition(edgeName="t02",targetState="stoptherobot",cond=whenEvent("obstacle"))
				}	 
				state("handleVrinfo") { //this:State
					action { //it:State
						 N = N + 1  
						if( checkMsgContent( Term.createTerm("vrinfo(A,B)"), Term.createTerm("vrinfo(TIME,collision)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								CommUtils.outmagenta("$name | ${payloadArg(0)} ${payloadArg(1)} N=$N")
								 vr.turnLeft()  
								if(  N == 4  
								 ){ System.exit(0)  
								}
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="doboundary", cond=doswitch() )
				}	 
				state("stoptherobot") { //this:State
					action { //it:State
						CommUtils.outred("$name in ${currentState.stateName} | $currentMsg | ${Thread.currentThread().getName()} n=${Thread.activeCount()}")
						 	   
						 vr.halt()    
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
				state("pausetherobot") { //this:State
					action { //it:State
						 vr.halt()    
						CommUtils.outred("$name in ${currentState.stateName} | $currentMsg | ${Thread.currentThread().getName()} n=${Thread.activeCount()}")
						 	   
						delay(2000) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="doboundary", cond=doswitch() )
				}	 
			}
		}
} 
