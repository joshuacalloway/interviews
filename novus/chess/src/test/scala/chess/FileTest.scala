package chess

import org.scalatest.FunSuite

class FileTest extends FunSuite {

  test("file plusOne") {
    assert(A.plusOne == Some(B))
    assert(B.plusOne == Some(C))
    assert(H.plusOne == None)
  }

  test("file minusOne") {
    assert(A.minusOne == None)
    assert(B.minusOne == Some(A))
    assert(H.minusOne == Some(G))
  }

}