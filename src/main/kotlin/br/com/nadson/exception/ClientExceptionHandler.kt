package br.com.nadson.exception

import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.server.exceptions.ExceptionHandler
import io.micronaut.http.HttpResponse
import jakarta.inject.Singleton

@Singleton
@Requires(classes = [RegisterNotFoundException::class])
class ClientExceptionHandler: ExceptionHandler<RegisterNotFoundException, HttpResponse<*>> {
    override fun handle(request: HttpRequest<*>?, exception: RegisterNotFoundException?): HttpResponse<*> {
        var msgErro = ErrorMessage(
                msg = exception?.message
        )

        return HttpResponse.notFound<ErrorMessage>().body(msgErro)
    }

}