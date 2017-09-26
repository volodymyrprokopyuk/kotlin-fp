package org.vld.sfpp.ch09

/**
 * Applies the [transform] operation to every element in the [source] list and returns [destination] list
 *
 * List Functor: given `(T) -> R` operation, produces `List<T> -> List<R>` operation
 * Functor = Map operation
 */
tailrec
fun <T, R> listFunctor(transform: (T) -> R, source: List<T>, destination: List<R> = listOf()): List<R> =
        // base case: return the destination list if the source list is empty
        if (source.isEmpty()) destination
        // recursive case: recursively apply transform operation to the first element of the source list,
        // place the result element into the destination list and forward the tail of the source list to the listFunctor
        else listFunctor(transform, source.drop(1), destination + transform(source.first()))

/**
 * Applies the [transform] operation to every element in the [source] list and returns flattened [destination] list
 *
 * List Monad: given `(T) -> List<R>` operation, produces `List<T> -> List<R>` operation
 * Monad = flatMap operation
 */
tailrec
fun <T, R> listMonad(transform: (T) -> List<R>, source: List<T>, destination: List<R> = listOf()): List<R> =
        // base case: return the destination list if the source list is empty
        if (source.isEmpty()) destination
        // recursive case: recursively apply transform operation to the first element of the source list,
        // place the result list into the destination list and forward the tail of the source list to the listMonad

        // Option 1: the flatten operation of the destination list is not required
        // due to the overloaded implementation of the + operation
//        else listMonad(transform, source.drop(1), destination + transform(source.first()))

        // Option 2: explicit flatten operation of the destination list
        else listMonad(transform, source.drop(1), transform(source.first()).fold(destination) {
            destinationAccumulator, element -> destinationAccumulator + element
        })

fun main(args: Array<String>) {
    val source = listOf(1, 2, 3, 4)
    val res1 = listFunctor({ element: Int -> element * 10 }, source)
    println(res1)
    val res2 = listMonad({ element: Int -> listOf(element * 10) }, source)
    println(res2)
}
