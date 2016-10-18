package io.vertx.reproducer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * Throws an NPE from within io.vertx.core.http.impl.HttpServerResponseImpl.doSendFile
 * when you quickly refresh the page in Chrome. Tested on Windows Chrome Version 53.0.2785.143 m
 *
 */
public class SendFileNPE extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        router.get("/pdf").handler(this::servePDF);
        server.requestHandler(router::accept).listen(8080);
    }

    private void servePDF(RoutingContext context) {
        doSomeAsync(context, res -> {
            if (res.succeeded()) {
                String filename = "file.pdf";

                context.response().putHeader(HttpHeaders.CONTENT_TYPE, "application/pdf");

                context.response().sendFile(filename, event -> {
                    if (event.failed()) {
                        context.fail(400);
                    } else {
                        // success!
                    }
                });
            }
        });
    }

    /**
     * Simulate an async call with small delay - for example retrieving some stuff from a database.
     *
     * @param context
     * @param handler
     */
    private void doSomeAsync(RoutingContext context, Handler<AsyncResult<Void>> handler) {
        vertx.executeBlocking(future -> {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }

            future.complete();

        }, false, res -> {
            handler.handle(Future.succeededFuture());
        });
    }

}
