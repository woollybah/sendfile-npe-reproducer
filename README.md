Steps to reproduce :

* Point your Chrome browser at the server (eg. http://127.0.0.1:8080/pdf )

* Refresh the page - holding ctrl-R down tends to hit the issue pretty quickly.

Example exception :
```
SEVERE: Unhandled exception
java.lang.NullPointerException
	at io.vertx.core.http.impl.HttpServerResponseImpl.doSendFile(HttpServerResponseImpl.java:491)
	at io.vertx.core.http.impl.HttpServerResponseImpl.sendFile(HttpServerResponseImpl.java:342)
	at io.vertx.core.http.HttpServerResponse.sendFile(HttpServerResponse.java:317)
	at io.vertx.core.http.HttpServerResponse.sendFile(HttpServerResponse.java:303)
	at io.vertx.reproducer.SendFileNPE.lambda$servePDF$1(SendFileNPE.java:36)
	at io.vertx.reproducer.SendFileNPE.lambda$doSomeAsync$3(SendFileNPE.java:65)
	at io.vertx.core.impl.FutureImpl.checkCallHandler(FutureImpl.java:158)
	at io.vertx.core.impl.FutureImpl.setHandler(FutureImpl.java:100)
	at io.vertx.core.impl.ContextImpl.lambda$null$0(ContextImpl.java:271)
	at io.vertx.core.impl.ContextImpl.lambda$wrapTask$2(ContextImpl.java:316)
	at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:163)
	at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:418)
	at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:440)
	at io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:873)
	at java.lang.Thread.run(Thread.java:745)
```
