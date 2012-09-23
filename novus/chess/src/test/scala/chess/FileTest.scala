package chess

import org.scalatest.FunSuite

class FileTest extends FunSuite {

  test("file++") {
    assert(Some(B) === A.++)
    assert(Some(C) === B.++)
    assert(None === H.++)
  }

  test("file--") {
    assert(None === A.--)
    assert(Some(A) === B.--)
    assert(Some(G) === H.--)
  }

}