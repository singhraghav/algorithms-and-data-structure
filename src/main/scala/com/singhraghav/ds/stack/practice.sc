import com.singhraghav.ds.stack.ArrayStack

val array = Array.ofDim[Int](10)

println(s"Array: ${array.toList}")

array(0) = 1

array(5) = 30

println(s"Array: ${array.toList}")

