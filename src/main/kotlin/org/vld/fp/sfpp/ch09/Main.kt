package org.vld.fp.sfpp.ch09

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
        else listMonad(transform, source.drop(1), transform(source.first()).fold(destination) { destinationAccumulator, element ->
            destinationAccumulator + element
        })

/**
 * Given an [authorToTitleList] builds an title word to author inverted index for searching author by a title word
 */
fun buildTitleWordToAuthorInvertedIndex(authorToTitleList: List<Pair<String, String>>): Map<String, Set<String>> =
        authorToTitleList
                // flatten list of pairs title word to author
                .flatMap { (author, title) ->
                    // split title into separate words
                    Regex("""\W""").split(title)
                            // filter not semantic title words
                            .filter { titleWord -> titleWord !in setOf("in", "for", "with") }
                            // create list of pairs title word to author
                            .map { titleWord -> titleWord to author }
                }
                // group authors by title word
                .groupBy(keySelector = { it.first }, valueTransform = { it.second })
                // remove author duplicates by converting authors from list to set
                .map { (titleWord, authors) -> titleWord to authors.toSet() }.toMap()

fun main(args: Array<String>) {
//    val source = listOf(1, 2, 3, 4)
//    val res1 = listFunctor({ element: Int -> element * 10 }, source)
//    println(res1)
//    val res2 = listMonad({ element: Int -> listOf(element * 10) }, source)
//    println(res2)

    val authorToTitleList = listOf(
            Pair("Dmitry Jemerov", "Kotlin in Action"),
            Pair("Dmitry Jemerov", "Kotlin in Action"),
            Pair("Svetlana Isakova", "Kotlin in Action"),
            Pair("Antonio Leiva", "Kotlin for Android Developers"),
            Pair("Denis Kalinin", "Modern Web Development with Kotlin"),
            Pair("Stephen Samuel", "Programming Kotlin"),
            Pair("Stefan Bocutiu", "Programming Kotlin"),
            Pair("Milos Vasic", "Fundamental Kotlin"),
            Pair("Marcin Moskala", "Android Development with Kotlin"),
            Pair("Igor Wojda", "Android Development with Kotlin")
    )
    val res = buildTitleWordToAuthorInvertedIndex(authorToTitleList)
    println(res["Kotlin"])
    println(res["Android"])
}
