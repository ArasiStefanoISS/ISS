%====================================================================================
% cleaner description   
%====================================================================================
%====================================================================================
context(ctxclean, "localhost",  "TCP", "8090").
 qactor( cleaner, ctxclean, "it.unibo.cleaner.Cleaner").
 static(cleaner).