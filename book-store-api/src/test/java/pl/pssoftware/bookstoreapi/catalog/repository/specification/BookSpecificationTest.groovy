package pl.pssoftware.bookstoreapi.catalog.repository.specification

import jakarta.persistence.criteria.*
import pl.pssoftware.bookstoreapi.catalog.dto.BookFilterDTO
import pl.pssoftware.bookstoreapi.catalog.entity.BookCategory
import spock.lang.Specification

class BookSpecificationTest extends Specification {

    def root = Mock(Root)
    def query = Mock(CriteriaQuery)
    def cb = Mock(CriteriaBuilder)


    def "should not create predicates when filter is empty"() {
        given:
        def filter = new BookFilterDTO()
        def andPredicate = Mock(Predicate)

        when:
        def result = BookSpecification.filter(filter)
                .toPredicate(root, query, cb)

        then:
        1 * query.distinct(true)
        1 * cb.and(_ as Predicate[]) >> andPredicate

        result == andPredicate
        0 * root._
        0 * cb.like(_, _)
        0 * cb.equal(_, _)
        0 * cb.greaterThanOrEqualTo(_, _)
    }

    def "should filter by title ignoring case"() {
        given:
        def filter = new BookFilterDTO(title: "Harry")
        def titlePath = Mock(Path)
        def lowerTitle = Mock(Expression)
        def titlePredicate = Mock(Predicate)
        def andPredicate = Mock(Predicate)

        when:
        def result = BookSpecification.filter(filter)
                .toPredicate(root, query, cb)

        then:
        1 * query.distinct(true)

        1 * root.get("title") >> titlePath
        1 * cb.lower(titlePath) >> lowerTitle
        1 * cb.like(lowerTitle, "%harry%") >> titlePredicate

        1 * cb.and([titlePredicate] as Predicate[]) >> andPredicate

        result == andPredicate
    }

    def "should filter by category"() {
        given:
        def filter = new BookFilterDTO(category: BookCategory.PROGRAMMING)
        def categoryPath = Mock(Path)
        def categoryPredicate = Mock(Predicate)
        def andPredicate = Mock(Predicate)

        when:
        def result = BookSpecification.filter(filter)
                .toPredicate(root, query, cb)

        then:
        1 * query.distinct(true)

        1 * root.get("category") >> categoryPath
        1 * cb.equal(categoryPath, BookCategory.PROGRAMMING) >> categoryPredicate

        1 * cb.and([categoryPredicate] as Predicate[]) >> andPredicate

        result == andPredicate
    }

    def "should filter by minimum price"() {
        given:
        def filter = new BookFilterDTO(minPrice: new BigDecimal("20.00"))
        def pricePath = Mock(Path)
        def pricePredicate = Mock(Predicate)
        def andPredicate = Mock(Predicate)

        when:
        def result = BookSpecification.filter(filter)
                .toPredicate(root, query, cb)

        then:
        1 * query.distinct(true)

        1 * root.get("price") >> pricePath
        1 * cb.greaterThanOrEqualTo(pricePath, new BigDecimal("20.00")) >> pricePredicate

        1 * cb.and([pricePredicate] as Predicate[]) >> andPredicate

        result == andPredicate
    }

    def "should filter by maximum price"() {
        given:
        def filter = new BookFilterDTO(maxPrice: new BigDecimal("100.00"))
        def pricePath = Mock(Path)
        def pricePredicate = Mock(Predicate)
        def andPredicate = Mock(Predicate)

        when:
        def result = BookSpecification.filter(filter)
                .toPredicate(root, query, cb)

        then:
        1 * query.distinct(true)

        1 * root.get("price") >> pricePath
        1 * cb.lessThanOrEqualTo(pricePath, new BigDecimal("100.00")) >> pricePredicate

        1 * cb.and([pricePredicate] as Predicate[]) >> andPredicate

        result == andPredicate
    }
}
