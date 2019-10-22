package com.mario.core.domain

sealed class Either<out E, out V> {
    data class Error<out E>(val error: E) : Either<E, Nothing>()
    data class Value<out V>(val value: V) : Either<Nothing, V>()
}

fun <V> either(action: () -> V): Either<Exception, V> =
    try {
        value(action())
    } catch (e: Exception) {
        error(e)
    }

private fun <V> value(value: V): Either<Nothing, V> = Either.Value(value)

private fun <E> error(error: E): Either<E, Nothing> = Either.Error(error)

inline infix fun <E, E2, V> Either<E, V>.mapError(f: (E) -> E2): Either<E2, V> = when (this) {
    is Either.Error -> Either.Error(f(error))
    is Either.Value -> this
}

inline infix fun <E, V, V2> Either<E, V>.flatMap(f: (V) -> Either<E, V2>): Either<E, V2> = when (this) {
    is Either.Error -> this
    is Either.Value -> f(value)
}

inline fun <E, V, A> Either<E, V>.fold(e: (E) -> A, v: (V) -> A): A = when (this) {
    is Either.Error -> e(this.error)
    is Either.Value -> v(this.value)
}