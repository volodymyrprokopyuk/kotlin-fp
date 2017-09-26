package org.vld.sfpp.ch09

/**
 * Applies a [transform] operation to every element in the [source] list and returns [destination] list
 *
 * List Functor: given `(T) -> R` operation produces `List<T> -> List<R>` operation
 * Functor = Map operation
 */
tailrec
fun <T, R> listFunctor(transform: (T) -> R, source: List<T>, destination: List<R> = listOf<R>()): List<R> =
        // base case: return the destination list if the source list is empty
        if (source.isEmpty()) destination
        // recursive case: recursively apply transform operation to the first element of the source list,
        // place the result into the destination list and forward the tail of the source list to the listFunctor
        else listFunctor(transform, source.drop(1), destination + transform(source.first()))

fun main(args: Array<String>) {
    val source = listOf(1, 2, 3, 4)
    val res = listFunctor({ it * 10 }, source)
    println(res)
}
