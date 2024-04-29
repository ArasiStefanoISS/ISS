/* Generated by AN DISI Unibo */ 
package it.unibo.facademock

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

class Facademock ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 val applutil = DisplayObj.create()
		 var N = 0  
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						CommUtils.outblue("$name | STARTS ")
						delay(1000) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="getusercmd", cond=doswitch() )
				}	 
				state("getusercmd") { //this:State
					action { //it:State
						 applutil.write("OPEN INPUT")  
						  N =  applutil.waitUserCmd( "Nfibo>>>" )  
						forward("usercmd", "usercmd($N)" ,name ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t03",targetState="handleusercmd",cond=whenDispatchGuarded("usercmd",{ N>0   
					}))
					transition(edgeName="t04",targetState="endofwork",cond=whenDispatchGuarded("usercmd",{ N==0  
					}))
				}	 
				state("handleusercmd") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("usercmd(CMD)"), Term.createTerm("usercmd(CMD)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								CommUtils.outblue("$name usercmd:${payloadArg(0)}")
								request("dofibo", "dofibo($N)" ,"smathasynchfacade" )  
						}
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t05",targetState="showResult",cond=whenReply("fibodone"))
				}	 
				state("showResult") { //this:State
					action { //it:State
						CommUtils.outblue("$name in ${currentState.stateName} | $currentMsg | ${Thread.currentThread().getName()} n=${Thread.activeCount()}")
						 	   
						 applutil.write("$currentMsg")  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="getusercmd", cond=doswitch() )
				}	 
				state("endofwork") { //this:State
					action { //it:State
						 applutil.write("$name | SORRY: input not working (... in gradle) ")  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
				}	 
			}
		}
} 
