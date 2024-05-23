%====================================================================================
% coldstorageservice description   
%====================================================================================
request( store, store(N) ).
reply( store_accepted, store_accepted(TICKET) ).  %%for store
reply( store_rejected, store_rejected(X) ).  %%for store
%====================================================================================
context(ctxcss, "localhost",  "TCP", "8088").
 qactor( soldstorageservice, ctxcss, "it.unibo.soldstorageservice.Soldstorageservice").
 static(soldstorageservice).
  qactor( drivermock, ctxcss, "it.unibo.drivermock.Drivermock").
 static(drivermock).
